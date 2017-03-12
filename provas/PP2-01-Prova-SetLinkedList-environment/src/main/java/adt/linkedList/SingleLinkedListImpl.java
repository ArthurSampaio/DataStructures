package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();

	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0; 
		SingleLinkedListNode<T> aux = this.getHead(); 
		while(!aux.isNIL()){
			size++;
			aux = aux.getNext();
		}return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.getHead();
	
		while(!aux.isNIL() && !aux.getData().equals(element))
			aux = aux.getNext();
		
		if(aux == null) return null;
		else return aux.getData();
	}

	@Override
	public void insert(T element) {
		
		if (element != null) {
			SingleLinkedListNode<T> aux = this.getHead();
			while (!aux.isNIL()){
				aux = aux.getNext();
			}
			
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<T>());
		}

	}

	@Override
	public void remove(T element) {
		
		if(element == null) return;
		
		if(!isEmpty()){

			SingleLinkedListNode<T> aux = this.getHead(); 
			while(!aux.isNIL() && !aux.getData().equals(element)){
				aux = aux.getNext();
			}
			if(!aux.isNIL()){
				aux.setData(aux.getNext().getData());
				aux.setNext(aux.getNext().getNext());
			}

		}
	}

	@Override
	public T[] toArray() {
		
		
		T[] out = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux = this.getHead(); 
		int cont = 0;
		while(!aux.isNIL()){
			out[cont] = aux.getData();
			aux = aux.getNext();
			cont++;
		}return out;
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}