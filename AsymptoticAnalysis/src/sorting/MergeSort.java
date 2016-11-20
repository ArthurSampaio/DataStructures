package sorting;

public class MergeSort<T extends Comparable<T>> implements Sorting<T> {

	@Override
	public void sort(T[] v) {
		
		mergeSort(v, 0, v.length-1);
		
		
	}
	
	public void mergeSort(T[] v, int ini, int fim){
		
		if(ini < fim){
			
			int med = (ini + fim)/2;
			mergeSort(v, ini, med);
			mergeSort(v, med+1, fim);
			
			merge(v, ini, med, fim);
		}
	}

	@SuppressWarnings("unchecked")
	private void merge(T[] v, int ini, int med, int fim) {
		T[] helper = (T[]) new Comparable[v.length]; 
		for(int i = 0; i < v.length; i ++){
			helper[i] = v[i];
		}
		
		int i = ini; 
		int j = med + 1; 
		int k = ini; 
		
		
		while(i <= med && j<=fim){
			
			if( (helper[i].compareTo(helper[j])>0) ){
				v[k] = helper[i];
				i++;		
			}else{
				v[k] =  helper[j];
				j++;
			}
			k++;
						
		}
		
		 while(i<=med){
			 v[k++] =  helper[i++];
		 }
		 while(j<=fim){
			 v[k++] = helper[j++];
		 }
	}

	public String toString(){
		return "MergeSort";
	}
	
	
}
