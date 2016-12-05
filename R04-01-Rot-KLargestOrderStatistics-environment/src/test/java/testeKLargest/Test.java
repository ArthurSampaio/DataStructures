package testeKLargest;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;

import orderStatistic.KLargest;
import orderStatistic.KLargestOrderStatisticsImpl;

public class Test {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	public KLargest<Integer> klarg;
	
	@Before
	public void setUp(){
		vetorTamPar = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,31 };
		vetorTamImpar = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23};
		klarg = new KLargestOrderStatisticsImpl<Integer>();
	}
	
	public void teste01(){
		
		Integer[] out = klarg.getKLargest(vetorTamPar, 4);
		Integer[] test = new Integer[]{30, 28, 29, 26, 23, 31};
		Arrays.equals(out, test);
		
	}

}
