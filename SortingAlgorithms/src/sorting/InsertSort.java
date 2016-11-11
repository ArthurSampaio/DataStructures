package sorting;

import static org.junit.Assert.assertArrayEquals;

public class InsertSort {
	
	public static void sort(int[] v){
		
		if(!(checksOrdered(v))){
			
			for(int i = 0; i <v.length; i++){
			
	
				int j = i;
			
				while (j > 0 && v[j] < v[j - 1]){
					//swap
					int aux = v[j];
					v[j] = v[j-1];
					v[j-1] =  aux;
				
					j--;
				}
				
				
			}	
		}	
	}
	
	public static void main (String[] args){
		
		
		
		int v[] = {0,3,-6,3,4,8};
		sort(v);
		assertArrayEquals(v, new int[]{-6,0,3,3,4,8});
	}
	
	public static boolean checksOrdered(int[] v){
		
		for(int i = 0; i < v.length-1; i++){
			if(!(v[i] < v[i+1]))
				return false;
		}
		return true;
	}
	
	
	
	

}
