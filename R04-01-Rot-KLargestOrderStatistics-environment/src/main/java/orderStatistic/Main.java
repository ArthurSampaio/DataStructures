package orderStatistic;

import java.util.Arrays;

public class Main {
	
	public Main() {
		Integer[] vetorTamPar;
		Integer[] vetorTamImpar;
		KLargest<Integer> klarg;
		
		vetorTamPar = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,31 };
		vetorTamImpar = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23};
		klarg = new KLargestOrderStatisticsImpl<Integer>();
		
		Integer[] out = klarg.getKLargest(vetorTamPar, 4);
		Integer[] test = new Integer[]{30, 28, 29, 26, 23, 31};
		Arrays.equals(out, test);
		
		
	
	}

}
