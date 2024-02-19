/* @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Sets implemented using a sorted array
 */

package dataStructures.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SortedArraySet<T extends Comparable<? super T>> implements SortedSet<T> {
  private T[] elements;  // Array storing elements in this set
  private int size;      // Number of elements in this set

  // INVARIANT: Elements are sorted in ascending order within the
  //            array and there are no repetitions in the
  //            structure

  private final static int INITIAL_CAPACITY = 10;

  @SuppressWarnings("unchecked")
  public SortedArraySet() {
    elements = (T[]) new Comparable[INITIAL_CAPACITY];
    size = 0;
  }

  @SuppressWarnings("unchecked")
  public SortedArraySet(int initialCapacity) {
    elements = (T[]) new Comparable[initialCapacity];
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private void ensureCapacity() {
    if (size == elements.length)
      elements = Arrays.copyOf(elements, elements.length * 2);
  }

  private class Finder {
    boolean found;
    int index;

    // Uses binary search to search for elem in array.
    // If elem is found:
    //   * found is set to true and index is set to index of cell in array containing elem.
    // If elem is not found:
    //   * found is set to false and index is set to index of cell in array
    //   where elem should be stored.
    Finder(T elem) {
        found = false;
        int l = 0, r = size-1, mid = 0;

        while(!found && l<=r){
            mid = (l+r)/2;
            if(elements[mid] == elem){
                found = true;
            } else if(elements[mid].compareTo(elem) < 0){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        index = found ? mid : l;
    }
  }

  public void insert(T elem) {
    Finder f = new Finder(elem);
    if(!f.found){
        ensureCapacity();
        for(int i = size; i > f.index; i--){
            elements[i] = elements[i-1];
        }
        elements[f.index] = elem;
        size++;
    }

  }

  public boolean isElem(T elem) {
    Finder f = new Finder(elem);
    return f.found;
  }

  public void delete(T elem) {
    Finder f = new Finder(elem);
    if(f.found){
        for(int i = f.index; i < size-1; i++){
            elements[i] = elements[i+1];
        }
        size--;
    }
  }

  // An iterator for this class
  private class SortedArraySetIterator implements Iterator<T> {
    private int index;

    public SortedArraySetIterator() {
      index = 0;
    }

    public boolean hasNext() {
      return index < size;
    }

    public T next() {
      return elements[index++];
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new SortedArraySetIterator();
  }

  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    for (int i = 0; i < size; i++)
      sj.add(elements[i].toString());
    return sj.toString();
  }

  // Adds a new element at the end of SortedArraySet.
  // precondition: elem should be larger than any element in set
  private void append(T elem) {
    assert size == 0 || elem.compareTo(elements[size - 1]) > 0 : "append: precondition failed";
    ensureCapacity();
    elements[size] = elem;
    size++; 
  }

  // Copy constructor: builds a new SortedLinkedSet with the same
  // elements as parameter sortedSet
  public SortedArraySet(SortedSet<T> sortedSet) {
    // todo
  }

  public static <T extends Comparable<? super T>>
  SortedArraySet<T> union(SortedArraySet<T> set1, SortedArraySet<T> set2) {
    // todo Should compute a new SortedArraySet including all elements which are
    //      in set1 or in set2.
    //      Neither set1 nor set2 should be modified.

    SortedArraySet<T> union = new SortedArraySet<>();
    int index1=0, index2=0;
    while(index1 < set1.size || index2 < set2.size){

    }
    
    return null;
  }

  public static <T extends Comparable<? super T>>
  SortedArraySet<T> intersection(SortedArraySet<T> set1, SortedArraySet<T> set2) {
    // todo Should compute a new SortedArraySet including only common elements in
    //      set1 and in set2.
    //      Neither set1 nor set2 should be modified.

    // todo
    return null;
  }

  public static <T extends Comparable<? super T>>
  SortedArraySet<T> difference(SortedArraySet<T> set1, SortedArraySet<T> set2) {
    // todo Should compute a new SortedArraySet including all elements in
    //      set1 which are not in set2.
    //      Neither set1 nor set2 should be modified.

    // todo
    return null;
  }
}
