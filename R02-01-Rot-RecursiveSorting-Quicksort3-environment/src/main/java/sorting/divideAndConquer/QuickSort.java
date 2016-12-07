package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		//Correção dos indices. 
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}

		//Caso haja elementos null, eles são enviados para o fim do array e é
		//decrementado o rightIndex	
		rightIndex = Util.sendNullToEnd(array, leftIndex, rightIndex);
		
		if(array.length != 1 && (array instanceof Comparable[]) )
			quickSort(array, leftIndex, rightIndex);
	}
	
	private void quickSort(T[] array, int leftIndex, int rightIndex){
		if(leftIndex<rightIndex){
			int ind_pivot = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, ind_pivot -1 );
			quickSort(array, ind_pivot + 1, rightIndex);
						
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;
		
		for(int j = i+1; j <=rightIndex; j++){
			if(array[j].compareTo(pivot)<0 ){
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex, i);
		return i;
	}
	
}
