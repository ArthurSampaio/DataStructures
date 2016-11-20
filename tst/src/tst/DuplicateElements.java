package tst;

import java.util.Scanner;

class DuplicateElements {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String[] data = sc.nextLine().split(" ");
		System.out.println();

		int[] array = new int[data.length];	
		
		for(int i = 0; i < data.length; i++){
			array[i] = Integer.parseInt(data[i]);
		}

		quickSort(array, 0, array.length);
		boolean duplicated = false; 
		
		for(int i = 0; i < array.length-1; i++){
			if(array[i] == array[i+1])
				duplicated = true;
			
		}
		System.out.println(duplicated + "\n");
		
	}

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
		
		for(int j = ini + 1; j <fim; j++){
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
	
}
