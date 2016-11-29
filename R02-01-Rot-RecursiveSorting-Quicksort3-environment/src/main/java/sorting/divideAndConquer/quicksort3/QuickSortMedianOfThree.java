package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte: 1. Comparar o elemento mais a
 * esquerda, o central e o mais a direita do intervalo. 2. Ordenar os elemento,
 * tal que: A[left] < A[center] < A[right]. 3. Adotar o A[center] como pivô. 4.
 * Colocar o pivô na penúltima posição A[right-1]. 5. Aplicar o particionamento
 * considerando o vetor menor, de A[left+1] até A[right-1]. 6. Aplicar o
 * algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		//Correção dos indices. 
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}

		//Caso haja elementos null, eles são enviados para o fim do array e é
		//decrementado o rightIndex	
		rightIndex = Util.sendNullToEnd(array, leftIndex, rightIndex);
		
		if(array.length != 1 && (array instanceof Comparable[]) )
			quickSort(array, leftIndex, rightIndex);			
	}
		
	private void quickSort(T[] array, int leftIndex, int rightIndex){
		if(leftIndex<rightIndex){
			if((rightIndex - leftIndex) < 4){
				//caso o array tenha menos que 4 elementos (3 ou menos) 
				//o array será ordenado pelo insertionSort
				//Uma vez que não é possivel fazer o median para arrays
				//menores que 3
				insertionSort(array, leftIndex, rightIndex);
			}else{
				int med = median3(array, leftIndex, rightIndex);
				//Troca o elemento do meio(mediana) com o right-1 
				Util.swap(array, med, rightIndex -1);
				int ind_pivot = partition(array, leftIndex, rightIndex);
				quickSort(array, leftIndex, ind_pivot -1 );
				quickSort(array, ind_pivot + 1, rightIndex);
			}				
		}
	}
	
	
	private void insertionSort(T[] array, int leftIndex, int rightIndex) {

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
		
	private int partition(T[] array, int leftIndex, int rightIndex) {
		
		T pivot = array[leftIndex];
		
		int i = leftIndex;
		
		for(int j = leftIndex + 1; j < rightIndex; j++){
			if(array[j].compareTo(pivot)<0 ){
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex, i);
		return i;
	}
		
	
	/**
	 * Encontra o valor mediano do array (aquele que está equidistante de todos os outros pontos)
	 * 
	 * @param array
	 * 		O array a ser utilizado para calcular a media
	 * @param leftIndex
	 * 		O indice a esquerda do array
	 * @param rightIndex
	 * 		O indice a direta do array
	 * @return
	 */
	private int median3(T[] array, int leftIndex, int rightIndex){
		int med = (leftIndex + rightIndex) / 2;
		
		//para o elemento da esquerda e do meio
		if(array[leftIndex].compareTo(array[med]) > 0)
			Util.swap(array, leftIndex, med);
		
		//se o elemento da esquerda for maior que o da direita
		//troca
		if(array[leftIndex].compareTo(array[rightIndex]) >0)
			Util.swap(array, leftIndex, rightIndex);
		
			
		//para o elemento do meio e da direta
		if(array[med].compareTo(array[rightIndex]) > 0)
			Util.swap(array, rightIndex, med);

		/*
		 * 1. Comparar o elemento mais a
		 * esquerda, o central e o mais a direita do intervalo. 2. Ordenar os elemento,
		 * tal que: A[left] < A[center] < A[right]. 3. Adotar o A[center] como pivô.
		 */
		
		//o pivot está no elemento do meio
		//primeiro e ultimo elemento já estão ordenado, por isso A[left+1] até A[right-1]
		
		return med;
		
		
	}
	
	
}
