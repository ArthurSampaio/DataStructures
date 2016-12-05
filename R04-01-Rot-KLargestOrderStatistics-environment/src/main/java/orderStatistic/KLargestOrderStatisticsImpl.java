package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		
		if(array == null)
			return null;
		if( k > array.length+1)
			return null;
		
		T[] aux =  (T[]) new Integer[array.length - k];
		T estatistic = this.orderStatistics(array, k);
		int cont = 0;
		
		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo(estatistic)>0){
				aux[cont] = array[i];
				cont++;
			}
			
		}
				
		return aux;
		
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		
		if(array == null)
			return null;
		
		if((k > array.length+1) && (k<=0))
			return null;
				
		//lógica do selectionsort
		int min; 
		int n = array.length;
					
		for(int i = 0; i < k; i++){
			min = i;
			for(int j = i+1; j< n; j++ ){
				if ((array[min].compareTo(array[j])) > 0) {
		               min = j;
		            }
		         }
		         
			
			if (min != i) {
	            Util.swap(array, min, i);
	         }
			 
		}	
		return array[k-1];
		
		
		
	}
}
