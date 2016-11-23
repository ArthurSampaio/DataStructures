package tst;

import java.util.Arrays;
import java.util.Scanner;

public class Sorting {
	
	public static void main(String[] args){
			
			Scanner sc = new Scanner(System.in);
			String[] data = sc.nextLine().split(" ");
			int[] array = new int[data.length];
			
			int maior = Integer.parseInt(data[0]);
			array[0] = Integer.parseInt(data[0]);
			for(int i = 1; i < data.length; i++){
				array[i] = Integer.parseInt(data[i]);
				if(array[i] > maior)
					maior = array[i];
			}
			
			String out = "";
			int[] b = countSorting(array, maior);
			for(int i = 0; i <b.length; i++){
				if( i != b.length -1){
					out += String.format("%d ", b[i]);
				}else{
					out += String.format("%d", b[i]);
				}
			}
			System.out.println(out);
			
			
			
			
	}

	private static int[] countSorting(int[] array, int maior) {
		int[] c = new int[maior];
		//frequencia 
		for(int i = 0; i <array.length; i++){
			c[array[i] - 1] +=1;
		}
		//cumulada
		for(int i = 1; i <c.length; i++){
			c[i] += c[i-1];
		}
		//vetor que sera organizado 
		int[] b = new int[array.length];
		
		for(int i = array.length -1 ; i >=0;i--){
			b[c[array[i]-1]-1] = array[i];
			c[array[i]-1]--;
		}
		return b;
		
		
	}
	

}
