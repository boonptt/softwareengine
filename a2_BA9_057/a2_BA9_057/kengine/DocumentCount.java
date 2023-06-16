package a2_BA9_057.kengine;


/**
 * @version 2.0
 * @overview A record with the fields "Doc,cnt" is one where "Doc" refers to an object of a document and "cnt" refers to an integer count of keyword occurrences in "Doc."
 * @note A record with the fields "Doc,cnt" is one where "Doc" refers to an object of a document and "cnt" refers to an integer count of keyword occurrences in "Doc."
 */
public class DocumentCount implements Comparable {
private Doc doc;
private int counter;

public DocumentCount(Doc doc, int counter) {
  this.doc = doc;
  this.counter = counter;
}

public Doc getdoc() {
  return doc;
}

public int takeCount() {
  return counter;
}

public void add(int newCount) {
  counter += newCount;
}

public String toString() {
  return "<" + doc.takeTitle() + "," + counter + ">";
}

/**
 * @param x another <code>DocCnt</code> object
 * @effects If 'code>x' is 'code>null,' then If "code>x" is not an instance of "code>DocCnt" then throws a
 * NullPointerException. raises a ClassCastException unless the following conditions are met:If this.cnt = x.cnt
 * returns 0, else returns 1, then this.cnt x.cnt returns -1.
 */
public int compareTo(Object x) throws ClassCastException, NullPointerException {
  if (x == null) throw new NullPointerException("argument null");

  DocumentCount dc = (DocumentCount) x;
  if (this.counter < dc.counter)
    return -1;
  else if (this.counter == dc.counter)
    return 0;
  else
    return 1;
}

public Object copy() {
  // only clone the doc count, keep the reference to the doc object unchanged
  return new DocumentCount(this.doc, counter);
}
}
