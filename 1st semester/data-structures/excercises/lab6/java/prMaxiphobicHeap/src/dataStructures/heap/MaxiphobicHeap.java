/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Maxiphobic Heaps
 */

package dataStructures.heap;

public class MaxiphobicHeap<T extends Comparable<? super T>> implements Heap<T> {

    // A node for an augmented binary tree
    private static class Node<E> {
        private E elem; // the element
        private int size; // the weight of tree rooted at this node
        private Node<E> left; // left child (null if no left child)
        private Node<E> right; // right child (null if no right child)
    }

    // Attribute for MaxiphobicHeap class
    private Node<T> root; // reference to root node of this Maxiphobic heap.
                          // null is heap is empty

    // Returns number of elements in tree stored at node
    private static int size(Node<?> node) {
        return node == null ? 0 : node.size;
    }

    // Merges two heaps. Returns merged heap.
    // Parameters are references to roots of heaps that should be merged.
    // Result should be a reference to root of resulting merged heap.
    private static <T extends Comparable<? super T>> Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }

        Node<T> root, key, child1, child2;
        if (h1.elem.compareTo(h2.elem) <= 0) {
            root = h1;
            key = h2;
            child1 = h1.left;
            child2 = h1.right;
        } else {
            root = h2;
            key = h1;
            child1 = h2.left;
            child2 = h2.right;
        }

        int child1Size = child1 == null ? 0 : child1.size;
        int child2Size = child2 == null ? 0 : child2.size;

        Node<T> n1, n2, n3;
        if (key.size > child1Size && key.size > child2Size) {
            n1 = key;
            n2 = child1;
            n3 = child2;
        } else if (child1Size > key.size && child1Size > child2Size) {
            n1 = child1;
            n2 = child2;
            n3 = key;
        } else {
            n1 = child2;
            n2 = child1;
            n3 = key;
        }
        root.left = n1;
        root.right = merge(n2, n3);

        return root;
    }

    // Constructor for MaxiphobicHeap class. Creates an empty Maxiphobic heap
    public MaxiphobicHeap() {
        root = null;
    }

    // Returns true if this Maxiphobic heap is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Returns total number of elements in this Maxiphobic heap
    public int size() {
        return root == null ? 0 : root.size;
    }

    // Returns minimum element in this Maxiphobic heap
    public T minElem() {
        return root.elem;
    }

    // Removes minimum element from this Maxiphobic heap
    public void delMin() {
        if (isEmpty()) {
            throw new EmptyHeapException("delMin on empty Heap");
        } else {
            root = merge(root.left, root.right);
        }
    }

    // insert new element in this Maxiphobic heap
    public void insert(T elem) {
        Node<T> newHeap = new Node<T>();
        newHeap.elem = elem;
        newHeap.size = 1;
        newHeap.left = null;
        newHeap.right = null;

        root = merge(root, newHeap);
    }

    /**
     * Returns representation of this Maxiphobic heap as a String.
     */
    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(className);
        sb.append("(");
        toStringRec(sb, root);
        sb.append(")");

        return sb.toString();
    }

    private static void toStringRec(StringBuilder sb, Node<?> node) {
        if (node == null) {
            sb.append("null");
        } else {
            sb.append("Node(");
            toStringRec(sb, node.left);
            sb.append(", ");
            sb.append(node.elem);
            sb.append(", ");
            toStringRec(sb, node.right);
            sb.append(")");
        }
    }
}