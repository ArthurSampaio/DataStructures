package sorting;

import util.Util;

public class RecursiveSelection<T extends Comparable<T>> extends AbstractSorting<T> {

	
	
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
		if(leftIndex+1 >=rightIndex) return;
		
		int min = leftIndex;
		for(int i = leftIndex +1; i<=rightIndex; i++){
			if(array[i].compareTo(array[min])<0)
				min = i;
		}Util.swap(array, leftIndex, min);
		recursiveSort(array, leftIndex+1, rightIndex);
	}
	
}
