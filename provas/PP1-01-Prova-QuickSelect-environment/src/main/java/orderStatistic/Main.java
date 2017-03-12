package orderStatistic;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		QuickSelect<Integer> quick = new QuickSelect<Integer>();
		int k = 2;
		System.out.println(k);
		Integer[] a = new Integer[]{-2,3,-5,6,8};
		Integer out = quick.quickSelect(a, k);
		System.out.println(out);
		System.out.println(Arrays.toString(a));
		
		k = 4;
		System.out.println(k);

		a = new Integer[]{-2,3,-5,6,8, -1, 30, -98};
		
		out = quick.quickSelect(a, k);
		System.out.println(out);
		System.out.println(Arrays.toString(a));

		 k = 3;
		System.out.println(k);

		a = new Integer[]{-1,-10,2,-13,-9,-52, 0,2};
		out = quick.quickSelect(a, k);
		System.out.println(out);
		System.out.println(Arrays.toString(a));

		k = 3;
		System.out.println(k);
		a = new Integer[]{-1,-10,13,-9,-87};
		out = quick.quickSelect(a, k);
		System.out.println(out);
		System.out.println(Arrays.toString(a));
		
		
		
	}
	
	
}
