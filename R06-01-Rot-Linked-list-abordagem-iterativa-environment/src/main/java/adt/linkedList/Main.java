package adt.linkedList;

import java.util.Arrays;

import org.junit.Assert;

public class Main {

	public static void main(String[] args) {
		
		DoubleLinkedList<Integer> lista1= new DoubleLinkedListImpl<>();
		System.out.println(Arrays.toString(lista1.toArray()));

		lista1.insert(3);
		System.out.println(Arrays.toString(lista1.toArray()));

		lista1.insert(2);
		System.out.println(Arrays.toString(lista1.toArray()));

		lista1.insert(1);
		
		System.out.println(lista1.search(1));
		System.out.println(Arrays.toString(lista1.toArray()));
		System.out.println(Arrays.toString(new Integer[] { 3, 2, 1 }));
		
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
		
		lista1.remove(9);
		Assert.assertFalse(lista1.isEmpty());
		
		lista1.insertFirst(4);

		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		
		System.out.println(Arrays.toString(lista1.toArray()));

		lista1.removeFirst();
		System.out.println(Arrays.toString(lista1.toArray()));


	}
	

	
	
}
