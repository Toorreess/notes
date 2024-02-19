/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Informática. UMA.
******************************************************************************/

package dataStructures.vector;

import dataStructures.list.ArrayList;
import dataStructures.list.List;

public class TreeVector<T> {

    private final int size;
    private final Tree<T> root;

    private interface Tree<E> {
        E get(int index);

        void set(int index, E x);

        List<E> toList();
    }

    private static class Leaf<E> implements Tree<E> {
        private E value;

        private Leaf(E x) {
            value = x;
        }

        @Override
        public E get(int index) {
            return value;
        }

        @Override
        public void set(int i, E x) {
        	value = x;
        }

        @Override
        public List<E> toList() {
            List<E> l = new ArrayList<>();
            l.append(value);
            return l;
        }
    }

    private static class Node<E> implements Tree<E> {
        private Tree<E> left;
        private Tree<E> right;

        private Node(Tree<E> l, Tree<E> r) {
            left = l;
            right = r;
        }

        @Override
        public E get(int index) {
        	if (index % 2 == 0) return left.get(index);
            else return right.get(index);
        }

        @Override
        public void set(int index, E x) {
            if (index % 2 == 0) left.set(index, x);
            else right.set(index, x);
        }

        @Override
        public List<E> toList() {
        	List<E> l = new ArrayList<>();
            l = intercalate(left.toList(), right.toList());
            return l;
        }
    }

    public TreeVector(int n, T value) {
    	if(n < 0) throw new VectorException("TreeVector: negative exponent");
        size = (int) Math.pow(2, n);
        root = mkTree(n, value);
    }

    private Tree<T> mkTree(int n, T value) {
        if(n==0) return new Leaf<>(value);
        else return new Node<>(mkTree(n-1, value), mkTree(n-1,value));
    }

    public int size() {
    	if(size == 0) throw new VectorException("size == 0");
        return size;
    }

    public T get(int i) {
    	if(i < 0 ||i >= size) throw new VectorException("wrong index");
        return this.root.get(i);
    }

    public void set(int i, T x) {
    	//to do
    }

    public List<T> toList() {
    	//to do
        return null;
    }

    protected static <E> List<E> intercalate(List<E> xs, List<E> ys) {
    	//to do
        return null;
    }

    
    // Only for students not taking continuous assessment

    static protected boolean isPowerOfTwo(int n) {
    	//to do
        return false;
    }

    public static <E> TreeVector<E> fromList(List<E> l) {
    	//to do
        return null;
    }
}
