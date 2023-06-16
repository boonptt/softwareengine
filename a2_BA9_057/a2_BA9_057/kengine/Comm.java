package a2_BA9_057.kengine;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;

import utils.NotPossibleException;

/**
 * @version 1.0
 * @overview a communication library class that carries out the process of obtaining online pages from a specified remote website.
 */
public class Comm {
/**
 * @effects In the event that documents cannot be retrieved from the website, use code u.
 * throws a "NotPossibleException," but if not, it returns a document generator
 * @version The URL of a local directory where the website documents are stored is assumed to be code>u/code>
 * in @version 1.0. To put it another way,
 * if u does not start with the file:// protocol, a NotPossibleException is thrown.
 */
public static Iterator takeDocuments(String u) throws NotPossibleException {
  // if u is a local folder, reads its files as Doc objects
  // and returns a generator for them.

  int pIdx = u.indexOf("/");
  String p = u.substring(0, pIdx + 2); // the protocol part of u

  if (!p.equals("file://")) {
    throw new NotPossibleException(
      "Comm function takeDocuments => URL = file://: " + u);
  }

  String path = u.substring(pIdx + 2); // folder path of u
  File directory = new File(path);
  if (!directory.exists() || !directory.isDirectory()) {
    // path is not a valid directory
    throw new NotPossibleException("Comm F:takeDocuments: path not valid: " + u);
  }
  File[] listFiles = directory.listFiles();
  return new DocumentGenerateInterator(listFiles);
}
}

class DocumentGenerateInterator implements Iterator {
Doc[] docs;
int cIdx = -1;

public DocumentGenerateInterator(File[] f) throws NotPossibleException {
  docs = new Doc[f.length];

  File f;
  BufferedReader bufferedReader;
  String dContent, line;
  Doc doc;
  for (int i = 0; i < f.length; i++) {
    f = f[i];
    try {
      bufferedReader = new BufferedReader(new FileReader(f));
      dContent = "";
      while ((line = bufferedReader.readLine()) != null) {
        dContent += line + "\n";
      }
    } catch (IOException error) {
      throw new NotPossibleException(
        "DocumentGenerateInterator: File cannot read, die" + f);
    }
    doc = new Doc(dContent);
    docs[i] = doc;
  }
}

public boolean isNext() {
  return cIdx < docs.length - 1;
}

public Object takeNext() {
  cIdx++;
  return docs[cIdx];
}

public void kill() {
  // kill doc
}
}
