package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

	   
	   
	  if(leftIndex < 0){
		  leftIndex = 0;
		   
	  }if(rightIndex > array.length -1){
		  rightIndex = array.length -1;
	  }
	   
      if (array.length > 0) {
         if (leftIndex > rightIndex) {
            throw new RuntimeException();
         }
      }

      
      if(array != null){
   	   
    	  //Se houver elementos null, eles serão mandados para o fim do array e o rightIndex é decrescido

          rightIndex = Util.mandaNullParaOFim(array, leftIndex, rightIndex);
    	  T atual;
	      int j;
	
	      for (int i = leftIndex + 1; i <= rightIndex; i++) {
	
	        
	
	         atual = array[i];
	         j = i;
	
	         while (j > 0 && (array[j - 1].compareTo(atual) > 0)) {
	            array[j] = array[j - 1];
	            j--;
	         }
	         array[j] = atual;

	      }
      }

   }



}
