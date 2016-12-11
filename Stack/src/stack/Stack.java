package stack;

public class Stack<T extends Comparable<T>> {
	
		
	private int top; 
	private T[] stack;
	private int size;
	
	public Stack(int size){
		if(size < 0)
			throw new RuntimeException();
		this.stack = (T[]) new Comparable[size]; 
		this.top = -1;
		this.size = size;
		
	}
	
	public boolean isFull(){
		return top+1 == size;
	}
	
	public boolean isEmpty(){
		return top == -1;
	}
	
	public void push(T item){
		if(!isFull()){
			top++; 
			stack[top]=item;
		}else
			throw new RuntimeException("FullStack");
	}
	
	public T pop(){
		if(isEmpty())
			throw new RuntimeException("EmptyStack");
		else{
			return stack[top--];
		}
	}
	
	public T peek(){
		if(isEmpty()) return null;
		else return stack[top];
	}
	
	public int getTop(){
		return this.top;
	}
	
	public int getIndexBigElement(){
		
		//if is empty
		if(isEmpty()) throw new RuntimeException("EmptyStack");
		
		//auxiliar stack
		Stack<T> aux = new Stack(size);
		//the heighest element is the top
		int ind_max = this.getTop();
		T max = this.pop();
		//auxiliar variables
		int size = this.getTop();
		int ind_aux; 
		T temp;

		//insert the max in aux
		aux.push(max);
		
		for(int i = 0; i <= size; i++){
			ind_aux = this.getTop(); 
			temp = this.pop();
			
			if(temp.compareTo(max)>0){
				max = temp;
				ind_max = ind_aux;
			}
			aux.push(temp);
		}
		
		//put the elements in the original stack
		while(!aux.isEmpty()){
			this.push(aux.pop());
		}
		//return the ind_max
		return ind_max;
	}
	
	public void reverseStack(int index){

		if(this.top == index) return;
		T bottom = popBottom(index);
		reverseStack(index);
		this.push(bottom);
	
	}
	
	private T popBottom(int index) {
		T top = this.pop();
		if(this.top ==index) return top;
		T bottom = popBottom(index);
		this.push(top);
		return bottom;
	}

	public String toString(){
		
		String to = "";
		for(int i = top; i <-1; i--){
			to += stack[i].toString() + '\n';
		}
		return to;
		
	}
	
}
