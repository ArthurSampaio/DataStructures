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
		return search(this.head, element);
		
	}

	private T search(SingleLinkedListNode<T> node, T element) {
		if(node.isNIL() || node.getData().equals(element)){
			return node.getData();
		}else{
			return search(node.getNext(), element);
		}
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(isEmpty()){
				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<>());
			}
			else{
				this.insertEnd(this.head, element);
			}
		}

	}

	private void insertEnd(SingleLinkedListNode<T> node, T element) {
		if(node.isNIL()){
			node.setData(element);
			node.setNext(new SingleLinkedListNode<>());
			return;
		}else
			insertEnd(node.getNext(), element);
		
	}

	@Override
	public void remove(T element) {
		if(element != null){
			
			if(isEmpty()) return;
			else{
				removeElement(this.head, element);
			}
			
			
		}
		

	}

	private void removeElement(SingleLinkedListNode<T> node, T element) {
		if(!node.isNIL() && node.getData().equals(element)){
			if(!node.isNIL()){
				node.setData(node.getNext().getData());
				node.setNext(node.getNext().getNext());
			}
			
		}else{
			if(node.isNIL()) return; 
			removeElement(node.getNext(), element);
		}
		
	}

	@Override
	public T[] toArray() {
		T[] out = (T[]) new Object[this.size()];
		int cont = 0; 
		toArrayElement(this.head,cont, out);
		return out;
	}

	private void toArrayElement(SingleLinkedListNode<T> node, int cont, T[] out) {
		if(!node.isNIL()){
			out[cont] = node.getData();
			toArrayElement(node.getNext(), cont+1, out);
		}
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
	