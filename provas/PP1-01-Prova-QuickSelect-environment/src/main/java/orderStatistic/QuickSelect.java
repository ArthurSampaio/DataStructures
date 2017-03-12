package orderStatistic;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso redua a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if(array == null) return null;
		int right = array.length-1 - sendNullToEnd(array, 0, array.length-1);
		if( k <= 0 || k > right) return null; 
	
		quickSortModificated(array, 0, right, k);
				
		return array[k-1];
	}
	
	private void swap(T[] array, int i, int j){
		T aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}
	

	
	private void quickSortModificated(T[] array, int leftIndex, int rightIndex, int k){
		if(leftIndex<rightIndex){
			int ind_pivot = partition(array, leftIndex, rightIndex);
			if(k == ind_pivot) return;
			if( k < ind_pivot)
				quickSortModificated(array, leftIndex, ind_pivot-1,k );
			else
				quickSortModificated(array, ind_pivot + 1, rightIndex,k);
						
		}
	}

	private int partition(T[] array, int left, int right) {
		T pivot = array[left];
		int i = left;
		
		for(int j = i+1; j <=right; j++){
			if(array[j].compareTo(pivot)<0 ){
				i++;
				this.swap(array, i, j);
			}
		}
		this.swap(array, left, i);
		return i;
	}
	
	private int sendNullToEnd(T[] a, int left, int right){
		
		int count = 0;
		for(int i = 0; i <=right;i++){
			if(a[i] == null){
				count ++;
				for(int j = i+1; j < right;j++){
					swap(a, i, j);
				}
			}
		}
		return count;
		
	}
	
}


	

