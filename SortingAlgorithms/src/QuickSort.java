import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int[] v, int ini, int fim){
				
		if(ini<fim){
			int ind_pivot = particao(v, ini, fim);
			quickSort(v, ini, ind_pivot -1 );
			quickSort(v, ind_pivot + 1, fim);
						
		}
			
	}

	private static int particao(int[] v, int ini, int fim) {
	   		
		int pivot = v[ini];
		int i = ini;
		
		for(int j = ini + 1; j <=fim; j++){
			if(v[j] < pivot ){
				i++;
				swap(v, i, j);
			}
		}
		swap(v, ini, i);
		return i;
	}
	
	private static void swap(int[] v, int i, int j){
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
				
	}
	
	
	public static void main(String[] args){
		
		int[] v = {4, 9, 12, -3, 4, 6, 9};
		System.out.println(Arrays.toString(v));
		quickSort(v, 0, v.length-1);
		System.out.println(Arrays.toString(v));
		int[] a = {-3, 4, 4, 6, 9, 9, 12};
		
		System.out.println(Arrays.equals(a, v));
				
	}
	
	
	
}
