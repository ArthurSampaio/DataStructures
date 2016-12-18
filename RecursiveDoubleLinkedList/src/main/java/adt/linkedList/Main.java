package adt.linkedList;

import java.util.Arrays;

import org.junit.Assert;

public class Main {

	public static void main(String[] args) {
		
		SingleLinkedListImpl<Integer> lista3 = new SingleLinkedListImpl<>();

		// Lista com 5 elementos
		Assert.assertTrue(lista3.size() == 0);
		lista3.insert(1);
		Assert.assertTrue(lista3.size() == 1);
		lista3.insert(2);
		Assert.assertTrue(lista3.size() == 2);
		lista3.insert(3);
		Assert.assertTrue(lista3.size() == 3);
		lista3.insert(4);
		Assert.assertTrue(lista3.size() == 4);
		lista3.insert(5);
		Assert.assertTrue(lista3.size() == 5);
		
		lista3.remove(1);

		System.out.println(Arrays.toString(lista3.toArray()));
		lista3.remove(4);
		System.out.println(Arrays.toString(lista3.toArray()));


	}
	

	
	
}
