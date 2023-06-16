package a2_BA9_057.kengine;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;


/**
 * @version 4.0 implements full code
 */
public class Query {
  private WordTable table;
  private Vector vector;
  private Vector vector1;

  public Query() {
    vector = new Vector();
    vector1 = new Vector();
  }

  public Query(WordTable table, String w) {
    this();
    this.table = table;
    Vector lookup = table.lookup(w);
    if (lookup != null) {
      Vector cl = new Vector();
      for (Iterator it = lookup.iterator(); it.hasNext();) {
        DocumentCount copy = (DocumentCount) ((DocumentCount) it.next()).copy();
        cl.add(copy);
      }
      vector.addAll(cl);
      vector1.add(w);
      
      Sorting.quickSort(vector);
    }
  }

  /**
   * A method to add a new keyword
   * @effects If "code>this" is empty or "code>w" is a keyword already in "code>this" (throws "NotPossibleException"); else, "code>this" is modified to be a query for "code>w" and all of the keywords already in "code>this"
   */
  public void addKey(String string) throws NotPossibleException {
    if (vector.isEmpty() || vector1.contains(string))
      throw new NotPossibleException("query is empty OR keyword already exists in query: "
              + string);
    vector1.add(string);
    Vector newDocs = table.lookup(string);
    DocumentCount mat, newDoc;
    boolean f = false;
    for (int i = 0; i < vector.size(); i++) {
      mat = (DocumentCount) vector.get(i);
      INNER: for (Iterator dit = newDocs.iterator(); dit.hasNext();) {
        newDoc = (DocumentCount) dit.next();
        if (mat.getdoc().equals(newDoc.getdoc())) {
          mat.add(newDoc.takeCount());
          containsKeyword = true;
          if (!f)
            f = true;
          break INNER;
        }
      }
      
      if (!containsKeyword) {
        vector.removeElementAt(i);
        i--;
      }
    }

    if (f) {
      Sorting.quickSort(vector);
    }
  }

  /**
   * A method to add a new Doc
   * @param hashtable
    maps interesting words in frequencies in <code>d</code>.
   * @effects Adds "code>d" and its keyword entries in "code>h" to "code>matches" as a query result if "code>this" is not empty and "code>d" includes all of "code>this's" keywords; otherwise, does nothing.
   * @version 4.0
   */
  public void addDoc(Doc content, Hashtable hashtable) {
    if (!vector1.isEmpty()) {
      String string;
      Integer integer;
      int sumof = 0;
      for (Iterator kit = vector1.iterator(); kit.hasNext();) {
        string = (String) kit.next();
        integer = (Integer) hashtable.get(string);
        if (integer == null) {
          return;
        } else {
          sumof += integer.intValue();
        }
      }
      DocumentCount documentCount = new DocumentCount(content, sumof);
      vector.add(documentCount);
      DocumentCount count;
      for (int i = 0; i < vector.size(); i++) {
        count = (DocumentCount) vector.get(i);
        if (count.takeCount() < documentCount.takeCount()) {
          vector.insertElementAt(documentCount, i);
          break;
        }
      }
    }
  }

  public String[] keys() {
    return (String[]) vector1.toArray(new String[vector1.size()]);
  }

  public int size() {
    return vector.size();
  }

  /**
   * A method to  matching document
   */
  public Doc fetch(int docu) throws IndexOutOfBoundsException {
    if (0 <= docu && docu < size()) {
      return ((DocumentCount) vector.get(docu)).getdoc();
    } else
      throw new IndexOutOfBoundsException(" document index is invalid " +
        docu
      );
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if (vector1 != null && !vector1.isEmpty()) {
      sb.append("Query: ");
      sb.append(vector1.toString());
    }
    
    if (vector != null && !vector.isEmpty()) {
      sb.append("\nMatches [").append(vector.size()).append("]:\n");
      sb.append(vector.toString());
    }
    
    if (sb.length() > 0) 
      return sb.toString();
    else
      return null;
  } 
  
  public Iterator match() {
	  if(size() == 0) 
		  return null;
	  return vector.iterator();;
  }
}
