

import java.util.List;

import es.uma.ada.problem.sorting.SortingAlgorithm;

/**
 * Selection sort
 * 
 * @author ccottap
 * @version 1.0
 *
 */
public class SelectionSort extends SortingAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sortingAlgorithms.SortingAlgorithm#getName()
	 */
	@Override
	public String getName() {
		return "SelectionSort";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sortingAlgorithms.SortingAlgorithm#sort(int[],int,int)
	 */
	@Override
	public <E extends Comparable<? super E>> void _sort(List<E> A, int l, int r) {
		
		for(int i=0; i< A.size()-1; i++) {
			int min = i;
			for(int j = i+1; j< A.size(); j++) {
				if(A.get(j).compareTo(A.get(min))==-1) {
					min = j;
				}
			}
			
			E temp = A.get(min);
			A.set(min, A.get(i));
			A.set(i, temp);
		}
	}

}
