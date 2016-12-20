package tst;

import java.util.Scanner;

class NotDouble {
	
	
	public static int menorFrequencia(int[]v, int maior){
		
		int[] c = new int[maior+1];
		
		for(int i = 0; i < v.length; i++){
			c[v[i]] += 1;
		}
		
		for(int i = 0; i< c.length; i++){
			if(c[i] == 1){
				return i;
			}
						
		}
		return -1;
		
	}
	
	
	

}
