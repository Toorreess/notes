
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

		if (l < r) {

			int mid = A.size() / 2;

			List<E> B = new ArrayList<E>();
			for (int i = 0; i < mid; i++) {
				B.add(i, A.get(i));
			}

			List<E> C = new ArrayList<E>();
			for (int i = mid; i < A.size(); i++) {
				C.add(i - mid, A.get(i));
			}
			_sort(B, 0, B.size() - 1);
			_sort(C, 0, C.size() - 1);
			merge(A, B, C, l);
		}
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
		// Initial indexes of B and C
		int i = 0, j = 0;
		
		while (i < B.size() && j < C.size()) {
			if (B.get(i).compareTo(C.get(j)) <= 0) {
				A.set(l, B.get(i));
				i++;
			} else {
				A.set(l, C.get(j));
				j++;
			}
			l++;
		}
		
		while(i < B.size()) {
			A.set(l, B.get(i));
			i++;
			l++;
		}
		while(i < B.size()) {
			A.set(l, C.get(j));
			j++;
			l++;
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
