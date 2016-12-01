package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(array == null)
			return;
		
		//se o array tiver tamanho 0 ou 1
		if(array.length == 0 || array.length == 1){
			return;
		}
		
		//se o apontador da esquerda for maior que o da direita, o vetor n é ordenado
		if(leftIndex > rightIndex)
			return;
		
		//correção de indices
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		//correção de indices
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}
		
		//se houver null's, manda-os para o fim e decrementa o rightIndex
		rightIndex = sendNullToEnd(array, leftIndex, rightIndex);
		
		//para ordenar o vetor entre leftIndex e rightIndex, crio um aux[] e copio para dentro
		//apenas os elementos que estão no intervalo e os ordeno pelo countingSort
		Integer[] aux = new Integer[(rightIndex - leftIndex) + 1];
		for(int i = leftIndex; i<=rightIndex; i++){
			aux[i - leftIndex] = array[i];
		}
		
		countingSort(aux, 0, aux.length -1);
		
		//depois adiciono os elementos ordenados pelo couting sort e substituo no array original
		for(int i = leftIndex;i<=rightIndex;i++){
			array[i] = aux[i - leftIndex];
		}
	}

	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex){
		
		if(array.length == 0 || array.length == 1){
			return;
		}
		
		
		//maior e menor elemento será - momentameneamente - o primeiro elemento do array
		Integer max = array[leftIndex];
		Integer min = array[leftIndex];
		
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if(array[i] > max)
				max = array[i];
			if(array[i] < min)
				min = array[i];
		}
		
		
		Integer[] c = new Integer[max - min + 1];
		
		//preencho o array com zeros
		for(int i =0; i < c.length; i++){
			c[i] = 0;
		}
		
		//frequency
		for(int e : array){
			c[e - min]++;
			
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
			array[c[b[i]-min]-1] = b[i];
			c[b[i]-min]--;			
		}
			
	}

	private static int sendNullToEnd(Object[] v, int left, int rightIndex){
		boolean find = false;
	   for(int i = left; i <= rightIndex; i ++){
		   for(int j = left; j<=rightIndex; j++){
			   if(v[j]== null){
				   find = true;
				   if(j <rightIndex){
					   Util.swap(v, j, j+1);
				   }
			   }
		   }if(find){
			   rightIndex--;
		   }
	   }
	   return rightIndex;
	}

}

