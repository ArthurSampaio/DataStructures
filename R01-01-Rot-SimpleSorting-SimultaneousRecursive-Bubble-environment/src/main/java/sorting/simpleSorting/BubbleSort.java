package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
	
		  if(array.length>0){
			  if(leftIndex > rightIndex){
				  throw new RuntimeException();
			  }
		  }
		  
		  if(leftIndex < 0){
			   leftIndex = 0;
			   
		   }if(rightIndex > array.length -1){
			   rightIndex = array.length -1;
		   }  
		
		   if(array != null){
				for(int i = leftIndex; i<= rightIndex; i++){
					
		
					 //Se o array contiver elementos nulos eles são mandados para o fim do array
					if(array[i] == null){
						Util.mandaNullParaOFim(array, i, rightIndex);
						 rightIndex--;
					 } 
					for(int j = leftIndex; j<= rightIndex -1; j++){
						if(array[j].compareTo(array[j+1]) > 0){
							Util.swap(array, j+1, j);
							
						}
					}
				}
		   }	
	}

}