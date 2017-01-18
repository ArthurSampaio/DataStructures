package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl(){
		//sentinel
		head = last = new DoubleLinkedListNode<T>();
	
	}

	@Override
	public void insertFirst(T element) {
		
		if(element == null) return;
		
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
		newNode.setData(element); 
		newNode.setNext(getHead());		
		newNode.setPrevious(getHead().getPrevious());
		getHead().setPrevious(newNode);
		setHead(newNode);
		
		
	}

	@Override
	public void removeFirst() {
		
		if(!isEmpty()){
			getHead().setPrevious(null);
			getHead().setData(null);
			setHead(getHead().getNext());
			
			if(this.getHead().getNext() == null){
				getHead().setNext(new DoubleLinkedListNode<>(null, null, getHead()));
				setLast(getHead());
			}
		}
		
	}

	@Override
	public void removeLast() {

		if(!isEmpty()){
	
			last.getPrevious().setData(null);
			last.getPrevious().setNext(null);
			setLast(this.getLast().getPrevious());
		}
		
	}
	
	public void reverse() {
		DoubleLinkedListNode<T> temp = new DoubleLinkedListNode<>();
		DoubleLinkedListNode<T> current = this.getHead();
		
		while(!current.isNIL()){
			temp = current.getPrevious();
			current.setPrevious((DoubleLinkedListNode<T>) current.getNext());
			current.setNext(temp);
			current = current.getPrevious();
		}
		
		if(!temp.isNIL()) setHead(temp.getPrevious());
	}
	
	

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
	
	@Override
	public DoubleLinkedListNode<T> getHead() {
		return (DoubleLinkedListNode<T>) super.getHead();
	}
	
	
	@Override
	public void insert(T element) {
		if (element == null) return;
		if (isEmpty()) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode(element, last, null);
			newNode.setPrevious(new DoubleLinkedListNode(null, newNode, null));
			setHead(newNode);
			last.setPrevious(newNode);
		} else {
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode(null, null, last);
			last.setData(element);
			last.setNext(newLast);
			last = newLast;
		}
		
	}
	
	@Override
	public void remove(T element){
		
		if(isEmpty() || element==null) return;
		DoubleLinkedListNode<T> aux = getHead(); 
		if(search(element) == null) return;
		
		while(!aux.getData().equals(element) && !aux.isNIL() )
			//cast
			aux =  (DoubleLinkedListNode<T>)aux.getNext();
		
		if(!aux.isNIL() && aux.getData().equals(element)) {
			DoubleLinkedListNode<T> newNode = (DoubleLinkedListNode<T>)aux.getNext().getNext();
			aux.setData(aux.getNext().getData());
			aux.setNext(newNode);
			if(newNode!=null)
				newNode.setPrevious(aux);
			
		}
		
		
		
	}
	

}