package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		
		countingSort(array, leftIndex, rightIndex);
		
		
	}
	
	
	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex){
		
		if(array.length == 0 || array.length == 1){
			return;
		}
		
		
		Integer max = array[leftIndex];
		
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if(array[i] > max)
				max = array[i];
		}
		
		Integer[] c = new Integer[max + 1];
		
		for(int i =0; i < c.length; i++){
			c[i] = 0;
		}
		
		//frequency
		for(int i = 0; i < array.length ; i++){
			c[array[i]] += 1;
		}
		//relative frequency
		for(int i = 1; i < c.length; i++){
			c[i] += c[i-1];
		}
		
		Integer[] b = new Integer[array.length];
		
		for(int i = 0; i < array.length; i++){
			b[i] = array[i];
		}
		
		for(int i = array.length -1; i >= 0; i--){
			array[c[b[i]]-1] = b[i];
			c[b[i]]--;			
		}

		
		
		
	}
	

}
