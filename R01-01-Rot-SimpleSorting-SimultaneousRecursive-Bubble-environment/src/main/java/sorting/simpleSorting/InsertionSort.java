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

      if (array.length > 0) {
         if (leftIndex > rightIndex) {
            throw new RuntimeException();
         }
      }

      T atual;
      int j;

      for (int i = leftIndex + 1; i <= rightIndex; i++) {

         if (array[i] == null) {
            mandaNull(array, i, rightIndex);
            rightIndex--;
         }

         atual = array[i];
         j = i;

         while (j > 0 && (array[j - 1].compareTo(atual) > 0)) {
            array[j] = array[j - 1];
            j--;
         }
         array[j] = atual;

      }

   }

   public void mandaNull(T[] v, int ind, int rightIndex) {

      for (int i = ind; i < rightIndex; i++) {
         Util.swap(v, ind, i);
      }

   }

}
