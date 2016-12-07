package orderStatistic;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		
		Integer[] vetorTamPar;
		Integer[] vetorTamImpar;
		KLargest<Integer> klarg;
		
		vetorTamPar = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,31 };
		vetorTamImpar = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23};
		klarg = new KLargestOrderStatisticsImpl<Integer>();
		
		
		Integer[] out = klarg.getKLargest(vetorTamPar, 3);
		System.out.println(Arrays.toString(vetorTamPar));

		Integer[] test = new Integer[]{30, 28, 29, 26, 23, 31};
		System.out.println(Arrays.toString(out));
		
		
	
	}

}
