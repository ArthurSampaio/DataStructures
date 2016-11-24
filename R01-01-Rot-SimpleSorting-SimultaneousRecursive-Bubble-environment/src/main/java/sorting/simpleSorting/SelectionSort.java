package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts  it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

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
		//Se houver elementos null, eles serão mandados para o fim do array e o rightIndex é decrescido

		   rightIndex = Util.mandaNullParaOFim(array, leftIndex, rightIndex);
		  int index_menor;
	      int n = rightIndex + 1;
	
	      for (int i = leftIndex; i < n - 1; i++) {
	    	//Se o array contiver elementos nulos eles são mandados para o fim do array
	    	 
	         index_menor = i;
	         for (int j = i + 1; j < n; j++) {
	            if ((array[index_menor].compareTo(array[j])) > 0) {
	               index_menor = j;
	            }
	         }
	         if (index_menor != i) {
	            Util.swap(array, index_menor, i);
	         }
	      }
	  }

   }
   
   

}
