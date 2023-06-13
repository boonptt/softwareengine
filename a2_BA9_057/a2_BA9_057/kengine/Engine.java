package a2_BA9_057.kengine;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;

/**
 * @overview An engine has a state as described in the search engine data model. 
 *           The methods throw the NotPossibleException when there is a problem; the exception
 *           contains a string explaining the problem. All instance methods modify the state of
 *           <code>this</code>.
 *           
 * 
 * @see "Program Development in Java", pgs: 313, 316-323, 365
 * 
 * @version 4.0 implement the full logic
 *
 */
public class Engine {
  private TitleTable titleTable;
  private WordTable wordTable;
  private Query query;
  
  //dmle: use Vector instead of array to ease maintenance  
  // private String[] urls;
  private Vector urls;

  /**
   * Constructor method 
   * 
   * @effects if uninteresting words cannot be retrieved from the persistent state
   *          throw <code>NotPossibleException</code> else creates NK and initialises
   *          the application state appropriately
   */
  public Engine() throws NotPossibleException {
    titleTable = new TitleTable();
    // the exception is thrown by this line
    wordTable = new WordTable();
    urls = new Vector();
  }

  /**
   * A method to create a <code>Query</code> object containing the matching documents 
   * of a given keyword <code>w</code>
   * 
   * @param w   a keyword to search
   * @effects   if <code>w</code> is not a word or <code>w</code> is an uninteresting word 
   *            then throws <code>NotPossibleException</code>, else returns 
   *            a <code>Query</code> object containing the documents matching the keyword
   * @version 4.0
   */
  public Query queryFirst(String w) throws NotPossibleException {
    if (w != null) 
      w = Helpers.canon(w);
    
    // check w
    if (wordTable.lookup(w) == null) {
      throw new NotPossibleException("Engine.queryFirst: the specified word is either not found in any documents or uninteresting: " + w);
    }
    
    query = new Query(wordTable, w);
    return query;
  }
  
  /**
   * A method to query the matching documents of an existing <code>Query</code> object for  
   * those that contains an additional keyword <code>w</code>
   * 
   * @param w   a keyword to search
   * @effects   if <code>w</code> is not a word or <code>w</code> is an uninteresting word 
   *            then throws <code>NotPossibleException</code>, else returns 
   *            an updated <code>Query</code> object containing the documents matching all keywords
   * @version 4.0
   */
  public Query queryMore(String w) throws NotPossibleException {
    if (w != null) 
      w = Helpers.canon(w);

    // check w
    if (wordTable.lookup(w) == null) {
      throw new NotPossibleException("Engine.queryMore: the specified word is either not found in any documents or uninteresting: " + w);
    }

    query.addKey(w);
    
    return query;
  }
  
  /**
   * A method to retrieve a <code>Doc</code> given its title.
   * 
   * @param t   the title of the document to retrieve
   * @effects   if <code>t</code> is not in <code>TitleTable</code>  
   *            then throw <code>NotPossibleException</code>, 
   *            else return the <code>Doc</code> object with title <code>t</code>
   */
  public Doc findDoc(String t) throws NotPossibleException {
    Doc d = titleTable.lookup(t);
    
    if (d == null) {
      throw new NotPossibleException("Engine.findDoc: the specified title could not be found: " + t);
    }
    
    return d;
  }
  
  /**
   * A method to retrieve documents from remote web site <code>u</code> and store 
   * them for query processing.
   * 
   * @param u   the URL of a remote web site
   * @effects   if <code>u</code> is not a URL for a web site containing documents or 
   *            <code>u</code> is one of the existing URLs throws 
   *            <code>NotPossibleException</code>, else adds each new document to 
   *            <code>TitleTable</code> and <code>WordTable</code> using their 
   *            respective methods. If no query was in progress then return an empty
   *            <code>Query</code> object, else returns an updated object that contains 
   *            any matching new documents.
   * @version 4.0  add each new document to the current query (if one exists)
   */
  public Query addDocs(String u) throws NotPossibleException {
    if (urls.contains(u)) 
      throw new NotPossibleException("Engine.addDocs: URL has been used: " + u);
        
    // use Comm.getDocs to obtain documents
    // this method will throw exception if u is not a valid URL
    Iterator docs = Comm.getDocs(u);
    Doc d;
    Hashtable h;
    while (docs.hasNext()) {
      d = (Doc) docs.next();
      //addDoc(d);
      titleTable.addDoc(d);
      h = wordTable.addDoc(d);
      
      if (query != null) {
        query.addDoc(d, h);
      }
    }
    
    if (query == null) {
      query = new Query();
    }
    
    // stores URL to urls 
    urls.add(u);
    
    return query;
  }  

  /**
   * @effects 
   *  if tt is empty
   *    return null
   *  else
   *    return Iterator(Doc) for documents in tt
   *     
   * @version 5.0 (for use in the assignment)
   */
  public Iterator<Doc> docIterator() {
    if (titleTable.isEmpty()) {
      return null;
    } else {
      return titleTable.docIterator();
    }
  }
  
  /**
   * A method to return all none-keywords in as a string for display.
   * 
   * @effects return a string containing all none-keywords
   * @note this method is not in the original design of this class
   */
  public String getNonkeys() {   
    return wordTable.getNonkeys();
  }
  
  /**
   * A method to return the displayable content of the word table as string.
   *  
   * @effects return a string containing all the words and their <code>DocCnt</code> objects
   * @note this method is not in the original design of this class
   */
  public String getWordTableAsString() {
    return wordTable.toString();
  }
  
  /**
   * @effects
   * if d is null
   * throws NullPointerException
   * else
   * add d to this.tt and this.wt using their respective methods. 
   * If this.q is not null
   * update this.q to contain any new matching documents. 
   * Return this.q
   */
  public Query addDoc(Doc d) throws NullPointerException {
	  if( d == null ) throw new NullPointerException();
	  titleTable.addDoc(d);
	  Hashtable htble = wordTable.addDoc(d);
	  if (query != null) {
		 query.addDoc(d, htble);
	  }else {
		 query = new Query();
	  }
	  return this.query;
  }
}