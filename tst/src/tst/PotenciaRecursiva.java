package tst;

import java.util.Scanner;

public class PotenciaRecursiva {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		int j = sc.nextInt();
		
		System.out.println(String.format("%d", potencia(i,j)));
		
	}
	
	public static int potencia(int base, int expoente){
		
		if(expoente == 0)
			return 1;
		else
			return base*potencia(base, expoente-1);
		
	}
	

}
