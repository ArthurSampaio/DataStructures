package sorting;

import static org.junit.Assert.assertArrayEquals;

	
public class SelectSort {
	
	
	
	public static void sort(int[] v){
		
		if(!checksOrdered(v)){
		
			for (int j = 0; j < v.length; j++){
				int index_menor = j; 
				
				for(int i = j; i < v.length; i++){
					if (v[index_menor] > v[i]){
						//swap
						int aux = v[index_menor];
						v[index_menor] = v[i];
						v[i] = aux;
					}
					
				}
				
			}
		}	
		
	}
	
	public static void main(String[] args){
		
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
