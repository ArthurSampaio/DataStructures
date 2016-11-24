package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * Este algoritmo eh RECURSIVO e aplica o bubblesort na entrada empurrando os 
 * elementos grandes apra o final e trazendo os elementos menores para o início.
 * Dessa forma, ao final da primeira iteração, o menor elemento está na primeira
 * posição e o maioe elemento está na ultima. 
 * Nas proximas iterações as posicoes dos elementos das iterações anteriores
 * são excluidas do espaço de varredura do array.
 */
public class SimultaneousRecursiveBubblesort<T extends Comparable<T>> extends AbstractSorting<T> {

   public void sort(T[] array, int leftIndex, int rightIndex) {
	   
	   if(leftIndex < 0){
		   leftIndex = 0;
		   
	   }if(rightIndex > array.length -1){
		   rightIndex = array.length -1;
	   }
	 
	   
	   if (rightIndex > leftIndex && rightIndex > 0) {

    	  if(array.length>0){
    		  if(leftIndex > rightIndex){
    			  throw new RuntimeException();
    		  }
    	  }

    	  if(array != null){
	         for (int i = leftIndex; i < rightIndex; i++) {
	
	        	 //Se o array contiver elementos nulos eles são mandados para o fim do array
				 if(array[i] == null){
					 Util.mandaNullParaOFim(array, i, rightIndex);
					 //decresce o right
					 rightIndex--;
				 } 
	        	 
	            if (array[i].compareTo(array[i + 1]) > 0) {
	               Util.swap(array, i, i + 1);
	
	            }
	            if ((rightIndex - i > 0) && (array[rightIndex - i].compareTo(array[rightIndex - i - 1]) < 0)) {
	               Util.swap(array, rightIndex - i, rightIndex - i - 1);
	            }
	
	         }
	         sort(array, leftIndex + 1, rightIndex - 1);
         
    	 }
	   }
   }
   

}
