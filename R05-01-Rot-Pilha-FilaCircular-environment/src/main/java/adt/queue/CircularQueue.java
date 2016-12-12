	package adt.queue;

import java.util.Arrays;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) throw new QueueOverflowException();
		
		if(isEmpty()){
			this.head = 0;
			this.tail = 0;
			elements++;
			array[tail] = element;
			this.elements++;
		}else{
			tail = (tail+1) % array.length;
			array[tail] = element;
			this.elements++; 

		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) throw new QueueUnderflowException();
		
		T value = array[head];
		if(this.tail == this.head){
			this.head = -1;
			this.tail = -1;
			this.elements--;
		}else {
			this.head = (head+1) % array.length;
			this.elements--;
		}
		return value;
	}

	@Override
	public T head() {
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return (head == -1 && tail == -1);
	}

	@Override
	public boolean isFull() {
		 return ((this.tail + 1) % array.length) == this.head;
	}
	
	public String toString(){
		return Arrays.toString(array);
	}

}
