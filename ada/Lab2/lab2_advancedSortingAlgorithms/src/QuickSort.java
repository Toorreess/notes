

import java.util.List;

import es.uma.ada.problem.sorting.SortingAlgorithm;

/**
 * Quicksort
 * 
 * @author ccottap
 * @version 1.0
 *
 */
public class QuickSort extends SortingAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sortingAlgorithms.SortingAlgorithm#sort(int[],int,int)
	 */
	@Override
	protected <E extends Comparable<? super E>> void _sort(List<E> A, int l, int r) {
		if(r > l) {
			int m = divide(A, l, r);
			_sort(A, l, m-1);
			_sort(A, m+1, r);
		}
	}

	/**
	 * Rearranges the elements in A so that all elements A[l...m-1] {@literal <=}
	 * A[m] {@literal<=} A[m+1...r]
	 * 
	 * @param <E> the class of the elements in the list
	 * @param A   the list
	 * @param l   left index
	 * @param r   right index
	 * @return the index m at which the list is divided
	 */
	protected <E extends Comparable<? super E>> int divide(List<E> A, int l, int r) {
		int p = l;
		int j = r;
		
		while(l<r) {
			while(l<=r && A.get(l).compareTo(A.get(p))<1) {
				l++;
			}
			while(l<=r && A.get(r).compareTo(A.get(p))==1) {
				r--;
			}
			if(l<r) {
				swap(A, l, r);
			}
		}
		swap(A, p, r);
		
		int m = r;
		
		return m;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see sortingAlgorithms.SortingAlgorithm#getName()
	 */
	@Override
	public String getName() {
		return "quicksort";
	}

}
