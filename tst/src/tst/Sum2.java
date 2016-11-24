package tst;

import java.util.Scanner;

public class Sum2 {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		String[] linha = sc.nextLine().split(" ");
		int number = sc.nextInt();
		int n1 = -1;
		int n2 = -1;
		
		int[] array = new int[linha.length];
		for(int i = 0; i<linha.length; i++) {
			array[i] = Integer.parseInt(linha[i]);
			
		}
		
		
		
		for(int i = 0; i<array.length;i++) {
			for(int j = 1; j < array.length; j++) {

				if((array[i] + array[j] == number) && (i!=j)){
					n1 = array[i];
					n2 = array[j];
					break;
					
				}
				
			}if(n1 != -1 && n2!= -1)
				break;
			
			
		}
		
		if(n1 != -1 && n2!= -1)
			System.out.println(n1 + " " + n2);
		else
			System.out.println("-1");
		
		
	}
	
	
}
