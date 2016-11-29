package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}

		//Caso haja elementos null, eles são enviados para o fim do array e é
		//decrementado o rightIndex	
		rightIndex = Util.sendNullToEnd(array, leftIndex, rightIndex);
		
		mergeSort(array, leftIndex, rightIndex);
		
						
	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex){
		
		if(leftIndex < rightIndex){
					
			int med = (leftIndex + rightIndex) / 2;
			
			sort(array, leftIndex, med);
			sort(array, med+1, rightIndex);
			
			merge(array, leftIndex, med, rightIndex);
			
		}
		
	}
	
	private void merge(T[] array, int leftIndex, int med, int rightIndex) {
		T[] aux = Util.makeArray(rightIndex +1);
		for(int i = leftIndex; i <= rightIndex; i++){
			aux[i] = array[i];
		}
		
		int i = leftIndex;
		int j = med + 1;
		int k = leftIndex;
		
		while(i <= med && j<=rightIndex){	
			
			if(aux[i].compareTo(aux[j])<0){
				array[k] = aux[i];
				i++;		
			}else{
				array[k] =  aux[j];
				j++;
			}
			k++;
						
		}
		
		 while(i<=med){
			 array[k++] =  aux[i++];
		 }
		 while(j<=rightIndex){
			 array[k++] = aux[j++];
		 }
		
	}
}
