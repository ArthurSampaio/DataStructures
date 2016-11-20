package sorting;

public class InsertionSort<T extends Comparable<T>> implements Sorting<T> {

	@Override
	public void sort(T[] v) {
		
		T atual;
		int j;
		
		for(int i = 1; i < v.length; i++ ){
			
			atual = v[i];
			j = i; 
			
			while(j > 0 && (v[j - 1].compareTo(atual) > 0)){
				v[j] = v[j - 1];
				j--;
			}v[j] = atual;
			
		}
		
	}
	
	public String toString(){
		return "InsertionSort";
	}
	
}
