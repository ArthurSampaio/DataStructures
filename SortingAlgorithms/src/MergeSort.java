import java.util.Arrays;

public class MergeSort {
	
	public static void mergeSort(int[] v, int ini, int fim){
		
		if(ini < fim){
			int med = (ini + fim) / 2;
			
			mergeSort(v, ini, med);
			mergeSort(v, med+1, fim);
			
			merge(v, ini, med, fim);
			
		}
		
	}

	public static void merge(int[] v, int ini, int med, int fim) {
		
		int[] aux = new int[v.length];
		
		for(int i = 0; i < v.length; i++){
			aux[i] = v[i];
		}
		
		int i = ini;
		int j = med + 1;
		int k = ini;
		
		while(i <= med && j<=fim){
			
			if(aux[i] < aux[j]){
				v[k] = aux[i];
				i++;		
			}else{
				v[k] =  aux[j];
				j++;
			}
			k++;
						
		}
		
		 while(i<=med){
			 v[k++] =  aux[i++];
		 }
		 while(j<=fim){
			 v[k++] = aux[j++];
		 }
		
		
	}
	
	public static void main(String[] args){
		
		int[] v = {4, 9, 12, -3, 4, 6, 9};
		System.out.println(Arrays.toString(v));
		mergeSort(v, 0, v.length-1);
		System.out.println(Arrays.toString(v));
		int[] a = {-3, 4, 4, 6, 9, 9, 12};
		
		System.out.println(Arrays.equals(a, v));
				
	}
	

}
