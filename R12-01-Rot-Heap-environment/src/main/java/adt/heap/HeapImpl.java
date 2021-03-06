package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = Util.makeArray(index + 1);
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if (position < this.index){
			int max = position;
			int right = right(position);
			int left = left(position);
			
			if(left <= index) max = left;
			if(right <= index)  max = maxIndex(left, right);
			
			max = maxIndex(max, position);
			if(max != position){
				
				Util.swap(heap, position, max);
				heapify(max);
			}
		}
	}
	
	private int maxIndex(int left, int right) {
		if(comparator.compare(heap[left], heap[right]) > 0)
			return left; 
		else
			return right;
	}

	@Override
	public void insert(T element) {

		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		if(element != null){
			index++; 
			this.heap[index] = element; //add
			//keep the heap
			int aux = index;
			while(maxIndex(aux, parent(aux)) == aux && this.parent(aux) != aux){
				Util.swap(heap, aux, parent(aux));
				aux = parent(aux);
			}
		}

	}

	@Override
	public void buildHeap(T[] array) {
		
		this.heap = array; 
		this.index = array.length-1;
		for(int i = parent(index); i >=0; i--){
			heapify(i);
		}

	}

	@Override
	public T extractRootElement() {
		T value = this.rootElement(); 
		this.remove();
		return value;
	}

	private void remove() {
		if(!isEmpty()){
			//troca a raiz pelo ultimo elemento inserido
			Util.swap(heap, 0, index);
			index--;
			heapify((0));
		}
		
	}

	@Override
	public T rootElement() {
		if(!isEmpty())
			return this.heap[0];
		else
			return null;
	}

	@Override
	public T[] heapsort(T[] array) {
		
		Comparator<T> actual = this.comparator;

		//zera o index
		this.index = -1;

		//update the this.comparator
		this.comparator = (a, b) -> b.compareTo(a);

		
		buildHeap(array);

		T[] newArray = (T[]) (new Comparable[this.size()]);

		for (int index = 0; index < newArray.length; index++) {
			newArray[index] = this.extractRootElement();
		}
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);

		this.comparator = actual;

		return newArray;
	}

	@Override
	public int size() {
		return index+1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
