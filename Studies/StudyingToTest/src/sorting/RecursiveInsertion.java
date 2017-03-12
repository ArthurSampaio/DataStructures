package sorting;

import util.Util;

public class RecursiveInsertion<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(array == null) return;
		   
		if(leftIndex < 0) leftIndex = 0;
		  	   
		if(rightIndex > array.length -1)
		   rightIndex = array.length -1;
		
		rightIndex = Util.mandaNullParaOFim(array, leftIndex, rightIndex);
		recursiveSort(array, leftIndex, rightIndex);
				
	}
	
	private void recursiveSort(T[] array, int leftIndex, int rightIndex){
		if(rightIndex > leftIndex){
			recursiveSort(array, leftIndex, rightIndex-1);
			insertInOrder(array[rightIndex], array, leftIndex, rightIndex-1);
		}	
	}
	
	private void insertInOrder(T element, T[] array, int leftIndex, int rightIndex){
		
		if(element.compareTo(array[rightIndex])>=0)
			array[rightIndex+1] = element;
		else if (leftIndex < rightIndex){
			array[rightIndex+1] = array[rightIndex];
			insertInOrder(element, array, leftIndex, rightIndex-1);
		}else{
			array[rightIndex+1] = array[rightIndex];
			array[rightIndex] =  element;
		}
		
	}
	
	

}
