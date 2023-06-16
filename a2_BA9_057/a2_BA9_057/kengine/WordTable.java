package a2_BA9_057.kengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;


public class WordTable {
private Hashtable table;

private static final String NK_FILE = "nk.dat";


public WordTable() throws NotPossibleException {
  table = new Hashtable();

  BufferedReader bf = new BufferedReader(new InputStreamReader(this
    .getClass().getResourceAsStream(NK_FILE)));

  try {
    if (!bf.ready())
      throw new NotPossibleException("Failed to read non-key file "
        + NK_FILE);
  } catch (IOException ex) {
    throw new NotPossibleException(
      "Failed to read non-key file "
        + NK_FILE
        + "due to " + ex);
  }

  String nw;
  boolean eof = false;
  while (!eof) {
    try {
      nw = bf.readLine();
      if (nw != null) {
        nw = nw.trim();
        nw = Helpers.canon(nw);

        table.put(nw, null + "");
      } else {
        eof = true;
      }
    } catch (IOException ex) {
    }
  }
}


public Hashtable addDoc(Doc docu) {
  Hashtable kmap = new Hashtable();

  Iterator words = docu.takeWords();
  String w;
  while (words.hasNext()) {
    w = (String) words.next();

    w = Helpers.canon(w);

    if (isInteresting(w)) {
      wfreq = (Integer) kmap.get(w);
      if (wfreq == null) {
        wfreq = new Integer(1);
      } else {
        wfreq = new Integer(wfreq.intValue() + 1);
      }
      kmap.put(w, wfreq);
    }
  }

  if (!kmap.isEmpty()) {
    Vector docVector;
    DocumentCount dc;
    for (Enumeration e = kmap.keys(); e.hasMoreElements(); ) {
      w = (String) e.nextElement();
      dc = new DocumentCount(docu, ((Integer) kmap.get(w)).intValue());
      docVector = (Vector) table.get(w);
      if (docVector == null)
        docVector = new Vector();
      docVector.add(dc);
      table.put(w, docVector);
    }

    return kmap;
  } else {
    return null;
  }
}

boolean isInteresting(String w) {
  if (w != null) {
    Object v = table.get(w);
    if (v != null && !(v instanceof Vector)) {
    } else {
      return true;
    }
  } else {
    return false;
  }
}


public Vector lookup(String k) {
  Object dv = table.get(k);
  if (dv != null && !dv.equals("null")) {
    return (Vector) dv;
  } else {
    return null;
  }
}


public String getNonkeys() {
  StringBuffer sb = new StringBuffer();
  for (Enumeration e = table.keys(); e.hasMoreElements(); ) {
    String w = (String) e.nextElement();
    Object v = lookup(w);
    if (v == null) {
      sb.append(w).append(" ");
    }
  }

  if (sb.length() > 0) {
    sb.delete(sb.length() - 1, sb.length());

    return sb.toString();
  } else
    return null;
}

/**
 * <code>DocCnt</code> objects
 */
public String toString() {
  StringBuffer sb = new StringBuffer();
  StringBuffer sbk = new StringBuffer();
  for (Enumeration e = table.keys(); e.hasMoreElements(); ) {
    String w = (String) e.nextElement();
    Vector dv = lookup(w);
    if (dv == null) {
      sb.append(w).append("\n");
    } else {
      sbk.append(w).append("->[").append(dv.size()).append("]").append(dv.toString()).append("\n");
    }
  }

  if (sb.length() > 0) {
    if (sbk.length() > 0) {
      sbk.delete(sbk.length() - 1, sbk.length());
      return sb.append(sbk).toString();
    } else {
      return sb.toString();
    }
  } else {
    return null;
  }
}
}
