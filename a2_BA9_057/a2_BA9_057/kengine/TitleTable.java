package a2_BA9_057.kengine;

import java.util.Hashtable;
import java.util.Iterator;

import utils.DuplicateException;
import utils.NotPossibleException;

/**
 * @overview  Keeps track of documents with their titles.
 * 
 * @see "Program development in Java", pgs 320, 365
 * 
 * @version 
 * - 2.0: provides a full implementation <br>
 * - 5.0: improved to support generics 
 *
 *
 */
public class TitleTable {
  
  // the rep of this class
  private Hashtable<String,Doc> stringDocHashtable;

  public TitleTable() {
    stringDocHashtable = new Hashtable<>();
  }
  

  public void addDoc(Doc d) throws DuplicateException {
    String t = d.takeTitle();
    // canonical form
    t = Helpers.canon(t);
    
    if (stringDocHashtable.containsKey(t)) {
      throw new DuplicateException("TitleTable.addDoc: a document with same title already exists: " + t);
    }
    
    stringDocHashtable.put(t, d);
  }
  

  public Doc lookup(String t) throws NotPossibleException {
    Doc d = null;
    if (t != null) {
      // canonical form
      String ct = Helpers.canon(t);

      d = stringDocHashtable.get(ct);
    }
    
    if (d == null)
      throw new NotPossibleException("TitleTable.lookup: could not look up document with title " + t);
    else 
      return d;
  }

  /**

   * @version 5.0
   */
  public Iterator<Doc> docIterator() {
    if (isEmpty())
      return null;
    else
      return stringDocHashtable.values().iterator();
  }

  /**

   * @version 5.0 (for use in the assignment)
   */
  public boolean isEmpty() {
    return stringDocHashtable.isEmpty();
  }
}
