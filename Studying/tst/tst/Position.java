package tst;

import java.util.Scanner;

public class Position {

	private static Scanner sc = new Scanner(System.in);

	 
	public static void main(String[] args){
		
		String a = sc.nextLine();
		int n = Integer.parseInt(a);
		
		String[] data = sc.nextLine().split(" ");
		String find = "";
		for(int i = 0; i < data.length; i++)
			if(n == Integer.parseInt(data[i]))
				if(i != data.length - 1){
					find += i + " ";
				}else{
					find += i;
				}
		
		if(find.equals("")){
			System.out.println("-1");
		}else
			System.out.println(find);
		
	}
	
}
