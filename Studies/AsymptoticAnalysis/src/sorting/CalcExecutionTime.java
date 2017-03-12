package sorting;

import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class CalcExecutionTime {

	private static final String xfile = "sort.data.csv";
	private static final String path = "data";
	private static Sorting<Integer> algorithm;
	
	private static Sorting insert = new InsertionSort<Integer>();
	private static Sorting merge = new MergeSort<Integer>();
	private static Sorting quick = new QuickSort<Integer>();
	private static Sorting select = new SelectionSort<Integer>();
	private static Sorting[] all = {insert, merge, select, quick};
	
	
	public static void main(String[] args) throws IOException{
		createCSV();
	}
	
	
	public static void createCSV() throws IOException{
		
		FileWriter file = new FileWriter(xfile);
			
		BufferedWriter br = new BufferedWriter(file);
		
		String header = "algorithm" + "," + "time"+ "," + "samples";
		br.write(header);
		
		System.out.println("Start the execution of all algorithms");
		
		for(int i = 10000; i<=20000; i+=1000){
			
			
			Integer[] v = generateArray(i);
			
			for(Sorting sort : all){
			
				System.out.println(sort.toString());
				String line = sort.toString() + "," + executeSortingAlgorithm(sort, v) + "," +
							i;
				br.write(line);
				br.newLine();
				
			}
					
		}
		
		br.close();
		System.out.println("all right");
	}
	
	public static long executeSortingAlgorithm(Sorting<Integer> algorithm, Integer[] v){
		
		long startTime = System.currentTimeMillis();
		
		algorithm.sort(v);
		
		long endTime = System.currentTimeMillis();
		
		long delTime = endTime - startTime;
		
		return delTime;
		
			
	}
	
	
	public static Integer[] generateArray(int size){
		
		Integer array[] = new Integer[size];
		
		Random randomGenerator = new Random();
		for(int i = 0; i<size; i++){
			array[i] =  randomGenerator.nextInt(size);
			
		}
		return array;
		
		
		
	}
	
}
