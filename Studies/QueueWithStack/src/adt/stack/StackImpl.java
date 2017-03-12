package adt.stack;

import java.util.Arrays;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(isEmpty()) return null;
		else return array[top];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top+1 == array.length;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(!isFull()){
			top++; 
			array[top]=element;
		}else
			throw new StackOverflowException();
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		else{
			return array[top--];
		}
	}
	
	public void reverseStack(int index) throws StackUnderflowException, StackOverflowException{

		if(this.top != -1){
			T bottom = popBottom();
			reverseStack(index);
			this.push(bottom);
		}
	}
	
	private T popBottom() throws StackUnderflowException, StackOverflowException {
		
		T top = this.pop();
		if(this.top ==-1) return top;
		else{
			T bottom = popBottom();
			this.push(top);
			return bottom;
		}
		
	}
	
	public String toString(){
		
		return Arrays.toString(array);
	}

}
