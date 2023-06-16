package a2_BA9_057.kengine;

import java.util.Iterator;
import java.util.Vector;

import utils.NotImplementedException;
import utils.NotPossibleException;


/**
 * @version 1.0
 * @overview A document class store title, text body, etc, ...
 */
public class Doc {
private String dContent;
private String title;
private String body;
private Vector vector; // the sequence of document words

/**
 * Constructor
 *
 * @param dContent A string that contains the document content
 * @effects If d cannot be handled as a document, a NotPossibleException is thrown. Otherwise, code>this/code> becomes the document that corresponds to code>d/code>.
 */
public Doc(String dContent) throws NotPossibleException {
  this.dContent = dContent;
}

/**
 * @effects get title
 */
public String takeTitle() {
  if (title == null) {
    int i = dContent.indexOf("<title>");
    int i1 = dContent.indexOf("</title>"); // must be well-formed
    if (i < 0) {
      i = dContent.indexOf("<TITLE>"); // possibly upper case
      i1 = dContent.indexOf("</TITLE>");
    }
    if (i >= 0 && i1 >= 0) {
      title = dContent.substring(i + 7, i1);
    }
  }
  return title;
}

/**
 * @effects getBody
 */
public String takeBody() {
  if (body == null) {
    int b1 = dContent.indexOf("<body");
    int b2 = dContent.indexOf("</body>");
    if (b1 < 0) {
      b1 = dContent.indexOf("<BODY");
      b2 = dContent.indexOf("</BODY>");
    }
    if (b2 >= 0 && b1 >= 0) {
      // we want to keep the <body</body> tag pairs in
      // the body text
      body = dContent.substring(b1, b2 + 7);
    }
  }
  return body;
}

/**
 * @overview a technique that iterates through each word in code>this/code> in the order that they appear.
 * @effects produces a generator that will produce each word in the text as a string in the order it appears in the text.
 * Additionally, this solution parses the embedded Javascript content.
 */
public Iterator takeWords() {
  takeBody();
  if (vector == null) {
    vector = new Vector();
    final char openTag = '<';
    final char closedtag = '>';

    final char c1 = '\n';
    final String[] strings = {c1 + "", "\t"};
    final boolean skiphtml = true;
    char[] chars = body.toCharArray();
    char cc;
    String o = null;
    String read = null;
    boolean s = false;
    for (int i = 0; i < chars.length; i++) {

      cc = chars[i];


      if (!s && cc == openTag) {

        s = true;
        read = "";
        continue;
      }

      if (s) {
        read += cc;

        if (cc == closedtag) {


          if (skiphtml) {

            if (!(read.startsWith("script") ||
              read.startsWith("SCRIPT") ||
              read.startsWith("style") ||
              read.startsWith("STYLE")
            )) {
              o = "";
            }
          } else {
            o = "";
          }
        } else if (cc == openTag) {

          if (o != null) {
            o.trim();
            for (int j = 0; j < strings.length; j++) {
              if (o.startsWith(strings[j]))
                o = o.substring(1);
              if (o.endsWith(strings[j]))
                o = o.substring(0, o.length() - 1)


              o = o.replaceAll(strings[j], " ");
            }

            if (!o.equals("")) {
              String[] witems = o.split(" ");
              for (int j = 0; j < witems.length; j++) {
                String witem = witems[j].trim();
                if (!witem.equals("")) {


                  vector.add(witem);
                }
              }
            }

            o = null;
          }
          read = null;

          s = false;

          i--;
        } else if (o != null) {


          if (cc != c1)
            o += cc;
        }

      }
    }
  }


  if (vector != null)
    return new WordGenerator(vector);
  else
    return null;
}

public String toString() {
  return takeTitle();
}

/**
 * @overview A generator implementation that is utilized by <code>Doc.words()</code> to
 * return a word iterator of a document.
 */
class WordGenerator implements Iterator {
  Vector words;
  int currWordIndex = -1;

  /**
   * @param words a word vector
   * @requires <code>words</code> should not be <code>null</code>
   */
  WordGenerator(Vector words) {
    this.words = words;
  }

  public boolean hasNext() {
    return (currWordIndex < words.size() - 1);
  }

  public Object next() {
    currWordIndex++;
    return words.get(currWordIndex);
  }

  /**
   * @effects throws
   */
  public void remove() throws NotImplementedException {
    throw new NotImplementedException("remove WordGenerator effect: Doc is immutable");
  }
}
}
