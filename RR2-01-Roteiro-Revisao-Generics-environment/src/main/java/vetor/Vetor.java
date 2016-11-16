package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int index;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		if(tamanho > -1){
			this.tamanho = tamanho;
			this.index = -1;
			this.arrayInterno = (T[]) new Object[tamanho];
		}else{
			throw new RuntimeException();
		}
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		if(index + 1 <= tamanho-1){
			index += 1; 
			arrayInterno[index] = o;
		}
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		// TODO Remove the exception and implement your code
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		for(int i = 0; i < index; i ++){
			if(arrayInterno[i].equals(o)){
				return arrayInterno[i];
			}
		}return null;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		// TODO Remove the exception and implement your code
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		// TODO Remove the exception and implement your code
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
