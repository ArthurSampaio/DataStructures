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
		if(element!=null){
			if(isEmpty()){
				insertFirstElement(element);
			}
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
			newNode.setData(element);
			//ta errado essa porra
			
			newNode.setNext(this.getHead());
			newNode.setPrevious(this.getHead().getPrevious());
			this.getHead().setPrevious(newNode);
			this.setHead(newNode);
			this.setLast(newNode);
		}
		
		
	}
	
	private void insertFirstElement(T element){
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
		newNode.setData(element);
		newNode.setNext(this.getLast());
		newNode.setPrevious(this.getHead());
		this.getHead().setNext(newNode);
		this.getHead().setPrevious(newNode);
		this.setHead(newNode);
		this.setLast(newNode);
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){
			this.getHead().setData(null);
			this.getHead().setPrevious(getLast());
			this.getLast().setNext(getHead());
			this.setHead(getHead().getNext());
			
		}
		
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			DoubleLinkedListNode<T> prelast = getLast();
			getLast().setNext(getHead());
			getHead().setPrevious(getLast());
			setLast(prelast);
			if(size() == 1) setHead(prelast);
			
		}
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
	public void insert(T element){
		if(isEmpty())
			insertFirstElement(element);
		else{
			insertAtEnd(this.getHead(), element);
		}
	}

	private void insertAtEnd(DoubleLinkedListNode<T> node, T element) {
		if(node.isNIL()){
			node.setData(element);
			node.setNext(new DoubleLinkedListNode<>());
			node.getNext().setNext(this.getHead());
			this.getHead().setPrevious((DoubleLinkedListNode<T>) node.getNext());
			this.setLast(node);
			
		}else{
			insertAtEnd((DoubleLinkedListNode<T>) node.getNext(),element);
		}
		
	}
	
	public void remove(T element){
		if(element!=null && !isEmpty()){
			
			removeEndElement(getHead(), element);
			
		}
	}

	private void removeEndElement(SingleLinkedListNode<T> node, T element) {
		if(!node.isNIL() && node.getData().equals(element)){
			node.setData(node.getNext().getData());
			((DoubleLinkedListNode<T>) node.getNext().getNext()).setPrevious((DoubleLinkedListNode<T>) node);

			node.setNext(node.getNext().getNext());
		

		}else{
			if(node.isNIL()) return;
			removeEndElement(node.getNext(), element);
		}
		
	}
	
	
	

}
