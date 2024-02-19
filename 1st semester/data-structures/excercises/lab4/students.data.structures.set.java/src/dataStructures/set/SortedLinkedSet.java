/* @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Sets implemented using a sorted linked structure
 */

package dataStructures.set;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SortedLinkedSet<T extends Comparable<? super T>> implements SortedSet<T> {
    // A node in the linked structure
    static private class Node<E> {
        E elem;
        Node<E> next;

        Node(E x, Node<E> node) {
            elem = x;
            next = node;
        }
    }

    // INVARIANTS: Nodes for elements included in the set are kept in a sorted
    //             linked structure (sorted in ascending order with respect to
    //             their elements). In addition, there should be no repetitions
    //             of elements in the linked structure.
    private Node<T> first; // Reference to first (with the smallest element) node
    private int size;      // Number of elements in this set

    // Constructs an empty set
    public SortedLinkedSet() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class Finder {
        boolean found;
        Node<T> previous, current;

        Finder(T elem) {
            previous = null;
            current = first;

            while (current != null && current.elem.compareTo(elem) < 0) {
                previous = current;
                current = current.next;
            }
            found = current != null && current.elem.equals(elem);
        }
    }

    public void insert(T elem) {
        Finder finder = new Finder(elem);
        if (!finder.found) {
            if (finder.previous == null)
                first = new Node<>(elem, first);
        } else {
            finder.previous.next = new Node<>(elem, finder.current);
        }
        size++;
    }

    public boolean isElem(T elem) {
        Finder finder = new Finder(elem);
        return finder.found;
    }

    public void delete(T elem) {
        Finder finder = new Finder(elem);
        if (finder.found) {
            if (finder.previous == null) {
                first = first.next;
            } else {
                finder.previous.next = finder.current.next;
            }
            size--;
        }
    }

    public String toString() {
        String className = getClass().getSimpleName();
        StringJoiner sj = new StringJoiner(", ", className + "(", ")");
        for (Node<T> node = first; node != null; node = node.next)
            sj.add(node.elem.toString());
        return sj.toString();
    }

    /**
     * Iterator over elements in this set.
     * Note that {@code remove} method is not supported. Note also
     * that linked structure should not be modified during iteration as
     * iterator state may become inconsistent.
     *
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<T> iterator() {
        return new LinkedSetIterator();
    }

    private class LinkedSetIterator implements Iterator<T> {
        Node<T> current; // A reference to node with value that will be iterated next

        public LinkedSetIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T x = current.elem;
            current = current.next;
            return x;
        }
    }

    // private constructor for building a SortedLinkedSet
    // by providing a reference to first node and size
    private SortedLinkedSet(Node<T> first, int size) {
        this.first = first;
        this.size = size;
    }

    // a buffer can be used to construct a SortedLinkedSet
    // efficiently in an incremental way by appending elements
    // in ascending order
    private static class SortedLinkedSetBuffer<T extends Comparable<? super T>> {
        Node<T> first, last; // references to first and last nodes in buffer
        int size;            // number of elements in buffer

        // Builds an empty buffer
        SortedLinkedSetBuffer() {
            first = null;
            last = null;
            size = 0;
        }

        // Adds a new element at the end of buffer.
        // precondition: elem should be larger than any element
        // currently in buffer
        void append(T elem) {
            assert first == null || elem.compareTo(last.elem) > 0 : "SortedLinkedSetBuffer.append: precondition failed";
            Node<T> node = new Node<>(elem, null);
            if (first == null) {
                first = node;
            } else {
                last.next = node;
            }
            last = node;
            size++;
        }

        // Builds a SortedLinkedSet using this buffer.
        SortedLinkedSet<T> toSortedLinkedSet() {
            return new SortedLinkedSet<>(first, size);
        }
    }

    // Copy constructor: builds a new SortedLinkedSet with the same
    // elements as parameter sortedSet.
    public SortedLinkedSet(SortedSet<T> sortedSet) {
        // TODO
        //  Implement this copy constructor using a SortedLinkedSetBuffer
    }

    public static <T extends Comparable<? super T>>
    SortedLinkedSet<T> union(SortedLinkedSet<T> set1, SortedLinkedSet<T> set2) {
        SortedLinkedSetBuffer<T> buffer = new SortedLinkedSetBuffer<T>();
        Node<T> node1 = set1.first;
        Node<T> node2 = set2.first;

        while (node1 != null || node2 != null) {
            T elem;
            if (node1 == null) {
                elem = node2.elem;
                node2 = node2.next;
            } else if (node2 == null) {
                elem = node1.elem;
                node2 = node1.next;
            } else if (node1.elem.equals(node2.elem)) {
                elem = node1.elem;
                node1 = node1.next;
                node2 = node2.next;
            } else if (node1.elem.compareTo(node2.elem) < 0) {
                elem = node1.elem;
                node1 = node1.next;
            } else {
                elem = node2.elem;
                node2 = node2.next;
            }
            buffer.append(elem);
        }
        return buffer.toSortedLinkedSet();
    }

    public static <T extends Comparable<? super T>>
    SortedLinkedSet<T> intersection(SortedLinkedSet<T> set1, SortedLinkedSet<T> set2) {
        SortedLinkedSetBuffer buffer = new SortedLinkedSetBuffer<>();
        Node<T> node1 = set1.first;
        Node<T> node2 = set2.first;

        while(node1 != null && node2 != null){
            if(node1.elem.equals(node2.elem)){
                buffer.append(node1.elem);
                node1 = node1.next;
                node2 = node2.next;
            } else if (node1.elem.compareTo(node2.elem) < 0){
                node1 = node1.next;
            } else {
                node2 = node2.next;
            }
        }
        return buffer.toSortedLinkedSet();
    }

    public static <T extends Comparable<? super T>>
    SortedLinkedSet<T> difference(SortedLinkedSet<T> set1, SortedLinkedSet<T> set2) {
        SortedLinkedSetBuffer buffer = new SortedLinkedSetBuffer<>();
        Node<T> node1 = set1.first;
        Node<T> node2 = set2.first;

        while(node1 != null){
            if(node1.elem.equals(node2.elem)){
                node2 = node2.next;
            } else if (node1.elem.compareTo(node2.elem) < 0) {
                buffer.append(node1.elem);
                node1 = node1.next;
            } else if (node1.elem.compareTo(node2.elem) > 0){
                node2 = node2.next;
            } else if (node2 == null) {
                buffer.append(node1.elem);
                node1 = node1.next;
            }
        }
        return buffer.toSortedLinkedSet();
    }
    public void union(SortedSet<T> sortedSet) {
        // TODO Should modify this set so that it becomes the union of
        //  this set and parameter sortedSet.
        //  Parameter sortedSet should not be modified.
        //  Should reuse current nodes in this set and should create new nodes for
        //  elements copied from sortedSet.
    }
}


