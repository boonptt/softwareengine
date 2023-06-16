package a2_BA9_057.kengine;

import java.util.Vector;

public class Sorting {

/**
 * quick-sort algorithm.
 *
 * @effects If the elements of code>v/code> are not comparable, a ClassCastException is thrown; if an element of code>v/code> is null, a NullPointerException is thrown; otherwise, code>v/code> is sorted so that items with higher index numbers are less numerous than those with lower ones. (Ordered ascensively)
 */
public static void quickSortAscAlgo(Vector v)
  throws ClassCastException,
  NullPointerException {
  quickSort(v, 0, v.size() - 1);
}

/**
 * @overview If the elements of code>v/code> are not comparable, a ClassCastException is thrown; if an element of code>v/code> is null, a NullPointerException is thrown; otherwise, code>v/code> is sorted so that items with higher index numbers are less numerous than those with lower ones. (Ordered ascensively)
 */
private static void quickSortAscAlgo(Vector v, int low, int high) {
  if (low >= high)
    return;
  int mid = partition(v, low, high);
  quickSort(v, low, mid);
  quickSort(v, mid + 1, high);
}

/**
 * A method that arranges elements of an array into two sub-arrays separated
 */
private static int partAsc(Vector vector, int ind, int t) {
  Comparable x = (Comparable) vector.get(ind);
  while (true) {
    while (((Comparable) vector.get(t)).compareTo(x) > 0)
      t--;
    while (((Comparable) vector.get(ind)).compareTo(x) < 0)
      ind++;

    if (ind < t) { // need to swap
      Object temp = vector.get(ind);
      vector.setElementAt(vector.get(t), ind);
      vector.setElementAt(temp, t);
      t--;
      ind++;
    } else {
      return t;
    }
  }
}

/**
 * @effect a technique that divides an array's elements into two subarrays by a middle value that is more than all the elements in the first subarray (on the left) and less than all the elements in the second subarray (on the right).
 */
public static void quickSort(Vector v) throws ClassCastException,
  NullPointerException {
  quickSort(v, 0, v.size() - 1);
}

/**
 *a technique for sorting vector items that fall between the low and high * indices.
 */
private static void quickSort(Vector v, int low, int high) {
  if (low >= high)
    return;

  int mid = partition(v, low, high);
  quickSort(v, low, mid);
  quickSort(v, mid + 1, high);
}

/**
 A technique that divides an array's items into two subarrays separated by a middle value greater than all of the first subarray's (on the left) and all of the second subarray's (on the right) elements.
 *
 */
private static int partition(Vector v, int i, int j) {
  Comparable x = (Comparable) v.get(i);
  while (true) {
    while (((Comparable) v.get(j)).compareTo(x) < 0)
      j--;
    while (((Comparable) v.get(i)).compareTo(x) > 0)
      i++;

    if (i < j) { // need to swap
      Object temp = v.get(i);
      v.setElementAt(v.get(j), i);
      v.setElementAt(temp, j);
      j--;
      i++;
    } else {
      return j;
    }
  }
}
}
