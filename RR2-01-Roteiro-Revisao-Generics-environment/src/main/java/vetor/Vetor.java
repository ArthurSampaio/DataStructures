package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T extends Comparable<T>> {

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
		
		for(int i = 0; i <= index; i++){
			if(arrayInterno[i].equals(o)){
				T obj = arrayInterno[i];
				arrayInterno[i] = arrayInterno[index];
				arrayInterno[index] = null;
				index -= 1;
				return obj;
			}
			
		}return null;
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		for(int i = 0; i <= index; i ++){
			if(arrayInterno[i].equals(o)){
				return arrayInterno[i];
			}
		}return null;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		if(index == -1)
			return true;
		else
			return false;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		if(index >= tamanho - 1){
			return true;
		}else
			return false;
	}
	
	public T maximo() {
		
		if(!isVazio()){
			T max = arrayInterno[0];
			for(int i = 1; i <= index; i++){
				if(comparadorMaximo.compare(max, arrayInterno[i]) < 0){
					max = arrayInterno[i];
				}
				
			}
			return max;
		}else{
			return null;
		}
		
		
	}

	public T minimo() {
		if(!isVazio()){
			T max = arrayInterno[0];
			for(int i = 1; i <= index; i++){
				if(comparadorMinimo.compare(max, arrayInterno[i]) < 0){
					max = arrayInterno[i];
				}
				
			}
			return max;
		}else{
			return null;
		}

	}
}
