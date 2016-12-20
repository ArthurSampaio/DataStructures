package sorting;
public class QuickSort<T extends Comparable<T>> implements Sorting<T> {

	@Override
	public void sort(T[] v) {
		quickSort(v, 0, v.length -1);
		
		
	}

	public void quickSort(T[] v, int ini, int fim){
		
		if(ini < fim){
			
			int ind_pivot = particao(v, ini, fim);
			quickSort(v, ini, ind_pivot -1);
			quickSort(v, ind_pivot + 1, fim);
			
		}
		
	}

	public int particao(T[] v, int ini, int fim) {
		int i = ini; 
		int j = i + 1;
		T pivot = v[ini];
		
		while(j < v.length){
			
			if((v[j].compareTo(pivot)) < 0){
				i++;
				swap(v,i,j);
				
			}j++;
						
		}
		swap(v, ini, i);
		return i;
		
	}
	
	private void swap(T[] v, int i, int j){
		T aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
	
	public String toString(){
		return "QuickSort";
	}
	
	
}