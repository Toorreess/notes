
import java.util.ArrayList;
import java.util.List;

import es.uma.ada.problem.sorting.SortingAlgorithm;

/**
 * Mergesort
 * 
 * @author ccottap
 * @version 1.0
 *
 */
public class MergeSort extends SortingAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sortingAlgorithms.SortingAlgorithm#sort(int[],int,int)
	 */
	@Override
	protected <E extends Comparable<? super E>> void _sort(List<E> A, int l, int r) {

		if (A.size() < 2)
			return;
		
		int mid = A.size()/2;
		
		List<E> B = new ArrayList<E>();
		for (int i = 0; i < mid; i++) {
			B.add(i, A.get(i));
		}

		List<E> C = new ArrayList<E>();
		for (int i = mid; i < A.size(); i++) {
			C.add(i-mid, A.get(i));
		}
		_sort(B, 0, B.size()-1);
		_sort(C, 0, C.size()-1);
		merge(A, B, C, l);
		

	}

	/**
	 * Merges two sorted lists
	 * 
	 * @param <E> the class of the elements in the list
	 * @param A   the merged list
	 * @param B   a list to be merged
	 * @param C   another list to be merged
	 * @param l   first index in A where the merged elements will be placed
	 */
	private <E extends Comparable<? super E>> void merge(List<E> A, List<E> B, List<E> C, int l) {
		int i = 1, j = 1, k = 1;
		
		while(j<B.size() && k<C.size()) {
			if(C.get(k).compareTo(B.get(j))==1) {
				A.add(i, B.get(j));
				j++;
			} else {
				A.add(i, C.get(k));
				k++;
			}
			i++;
		}
		if(j==B.size()) {
			for(; i<A.size(); i++) {
				A.set(i, C.get(k));
				k++;
			}
		} else {
			for(; i< A.size(); i++) {
				A.set(i, B.get(j));
				j++;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sortingAlgorithms.SortingAlgorithm#getName()
	 */
	@Override
	public String getName() {
		return "MergeSort";
	}

}
