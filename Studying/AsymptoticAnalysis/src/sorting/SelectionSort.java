package sorting;


public class SelectionSort<T extends Comparable<T>> implements Sorting<T> {
	
	public SelectionSort(){}
	
	@Override
	public void sort(T[] v) {
		int index_menor;
		int n = v.length;
		
		for(int i = 0; i < n -1; i++){
			index_menor = i; 
			for(int j = i+1; j < n;j++){
				if((v[index_menor].compareTo(v[j])) > 0){
					index_menor = j;
				}
			}if(index_menor != i){
				swap(v, index_menor, i);
			}
		}
		
		
	}

	private void swap(T[] v, int i, int j){
		T aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
	
	public String toString(){
		return "SelectonSort";
	}
}
