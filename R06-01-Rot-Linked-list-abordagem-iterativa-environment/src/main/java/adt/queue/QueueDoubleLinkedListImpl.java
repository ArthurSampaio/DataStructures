package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		if(size < 0) size = 0; 
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()) throw new QueueOverflowException();
		this.list.insert(element);
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()) throw new QueueUnderflowException();
		T value = this.head();
		((DoubleLinkedListImpl<T>) list).removeFirst();
		return value;
		
	}

	@Override
	public T head() {
		T element = null;
		
		if (!isEmpty()) {
			element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
			
		}
		
		return element;
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public boolean isFull() {
		return list.size() == this.size;
	}

	
	
}
