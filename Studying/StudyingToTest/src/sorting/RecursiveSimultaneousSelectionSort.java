package sorting;

import util.Util;

public class RecursiveSimultaneousSelectionSort<T extends Comparable<T>> extends AbstractSorting<T>  {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(array == null || array.length == 0 || array.length == 1) return; 
		
		if (leftIndex < 0)
	         leftIndex = 0;
		if (rightIndex > array.length - 1)
			rightIndex = array.length - 1;
		if (leftIndex > rightIndex)
			return;
			
			if(leftIndex <rightIndex){
			int indMin = leftIndex; 
			int indMax = rightIndex;
			
			for(int i = leftIndex; i <= rightIndex; i++){
				
				if(array[i].compareTo(array[indMin])<0){
					indMin = i;
				}
				if(array[i].compareTo(array[indMax])>0){
					indMax = i;
				}
	
			}
			if (indMax == leftIndex){
				  int aux = indMin;
				  Util.swap(array, leftIndex, indMin);
				  Util.swap(array, aux, rightIndex);
		  }else{
			  Util.swap(array, leftIndex, indMin);
			  Util.swap(array, indMax, rightIndex);
		  }
			sort(array, leftIndex+1, rightIndex-1);
		
	}

}
}
