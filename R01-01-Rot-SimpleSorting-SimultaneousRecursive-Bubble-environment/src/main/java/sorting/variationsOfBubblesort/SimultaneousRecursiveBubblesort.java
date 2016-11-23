package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * Este algoritmo eh RECURSIVO e aplica o bubblesort na entrada empurrando os 
 * elementos grandes apra o final e trazendo os elementos menores para o início.
 * Dessa forma, ao final da primeira iteração, o menor elemento está na primeira
 * posição e o maioe elemento está na ultima. 
 * Nas proximas iterações as posicoes dos elementos das iterações anteriores
 * são excluidas do espaço de varredura do array.
 */
public class SimultaneousRecursiveBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
