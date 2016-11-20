package sorting;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args){
		
		Sorting<Integer> algorith = new QuickSort<Integer>();
		
		Integer[] v = {1, 2, 3, 6, 8, -6, -6, -1, 0};
		algorith.sort(v);
		System.out.println(Arrays.toString(v));
		
		
	}

}
