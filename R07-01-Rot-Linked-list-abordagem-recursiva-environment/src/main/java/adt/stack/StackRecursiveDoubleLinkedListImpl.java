package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		if(size<0) size = 0; 
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()) throw new StackOverflowException();
		this.top.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) throw new StackUnderflowException();
		T value = this.top.toArray()[0];
		this.top.removeFirst();
		return value; 
	}

	@Override
	public T top() {
		if(!isEmpty())
			return this.top.toArray()[0];
		else 
			return null;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size; 
	}

}
