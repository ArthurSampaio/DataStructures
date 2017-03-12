package tst;

import java.util.Scanner;

class FindElement {


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		String[] a = sc.nextLine().split(" ");
		int n = Integer.parseInt(a[0]);
		String[] data = sc.nextLine().split(" ");
		boolean find = false;
		for(int i = 0; i < data.length; i++)
			if(n == Integer.parseInt(data[i]))
				find = true;
		
		if(find)
			System.out.println("sim");
		else
			System.out.println("não");
		
	}
	
}
