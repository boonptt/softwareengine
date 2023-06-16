package a2_BA9_057.kengine;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;

/**
 * @version 2.0
 * @overview According to the search engine data model, an engine has a state.
 * When there is a problem, the methods throw the NotPossibleException, which provides a string describing the issue. The status of this is changed by all instance methods.
 */
public class Engine {
private TitleTable table;
private WordTable words;
private Query q;

//dmle: use Vector instead of array to ease maintenance
// private String[] urls;
private Vector vector;

/**
 * Constructor
 *
 * @effects Throw a NotPossibleException if the persistent state cannot be used to retrieve uninteresting words. Alternatively, NK is created and the application state is suitably initialized.
 */
public Engine() throws NotPossibleException {
  table = new TitleTable();
  // the exception is thrown by this line
  words = new WordTable();
  vector = new Vector();
}

/**
 * a process for producing a code>Query/code> object that contains the documents * that match a specified keyword code>w/code>.
 *
 * @param w search w
 * @effects If the keyword "w" is not a word or is an uninteresting word, then a "NotPossibleException" is thrown; otherwise, a "Query" object is returned that contains the documents that match the keyword.
 * @version 4.0
 */
public Query takeOne(String w) throws NotPossibleException {
  if (w != null)
    w = Helpers.canon(w);

  // check w
  if (words.lookup(w) == null) {
    throw new NotPossibleException("The selected term is either uninteresting or not present in any documents: \n" + w);
  }
  q = new Query(words, w);
  return q;
}

/**
 * a way to search for documents that match a specific codeQuery</code> object for * those that has the extra keyword "w" in it.
 *
 * @effects If "w" is not a word or "w" is a boring word, then "NotPossibleException" is thrown, but if "w" is a word, then "returns" * an updated "code>Query" object with all of the documents matching all keywords.
 * @version 4.0
 */
public Query takemore(String w) throws NotPossibleException {
  if (w != null)
    w = Helpers.canon(w);
  if (words.lookup(w) == null) {
    throw new NotPossibleException("The chosen term is either boring or absent from any papers." + w);
  }
  q.addKey(w);
  return q;
}

/**
 * @param t the title to retrieve
 * @effects Throw a "NotPossibleException" if "code>t" is not in the "TitleTable"; else, return the "Doc" object with "code>t" as the title.
 */
public Doc findDoc(String t) throws NotPossibleException {
  Doc d = table.lookup(t);
  if (d == null)
    throw new NotPossibleException("title could not be found: " + t);
  return d;
}

/**
 * store them for query processing.
 *
 * @param u the URL of a remote web site
 * @effects code>NotPossibleException/code> is thrown if code>u/code> is not a URL for a website holding documents or if code>u/code> is one of the existing URLs; otherwise, each new document is added using the appropriate method to the WordTable and TitleTable. Return an empty code>Query/code> object if there was no query running; otherwise, return an updated object with any matching new documents.
 * @version 4.0
 */
public Query addMoreD(String u) throws NotPossibleException {
  if (vector.contains(u))
    throw new NotPossibleException("URL has been used: " + u);
  Iterator docs = Comm.takeDocuments(u);
  Doc d;
  Hashtable h;
  while (docs.hasNext()) {
    d = (Doc) docs.next();
    table.addDoc(d);
    h = words.addDoc(d);

    if (q != null) {
      q.addDoc(d, h);
    }
  }

  if (q == null) {
    q = new Query();
  }
  vector.add(u);
  return q;
}

public Iterator<Doc> docIterator() {
  if (table.isEmpty()) {
    return null;
  } else {
    return table.docIterator();
  }
}

public String getFailkeys() {
  return words.getNonkeys();
}

public String getWordTableAsString() {
  return words.toString();
}

/**
 * @effects If d is null, NullPointerException is thrown; otherwise, use the appropriate method to add d to this.tt and this.wt.
 * Update this.q to include any additional documents that match if it is not null.
 * Send this back.q
 */
public Query plusDoc(Doc d) throws NullPointerException {
  if (d == null) throw new NullPointerException();
  table.addDoc(d);
  Hashtable htble = words.addDoc(d);
  if (q != null) {
    q.addDoc(d, htble);
  } else {
    q = new Query();
  }
  return this.q;
}
}
