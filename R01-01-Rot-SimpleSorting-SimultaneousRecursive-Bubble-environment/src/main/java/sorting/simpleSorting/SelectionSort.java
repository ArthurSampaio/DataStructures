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
      int index_menor;
      int n = rightIndex + 1;

      for (int i = leftIndex; i < n - 1; i++) {
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
