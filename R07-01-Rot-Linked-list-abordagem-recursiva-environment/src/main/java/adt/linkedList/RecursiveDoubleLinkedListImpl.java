package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	
	@Override
	public void insert(T element){
		if(isValid(element)){
			if(isNull()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				this.getPrevious().setNext(this);
				
			}else{
				this.getNext().insert(element);
			}
		}
	}
	
	@Override
	public void remove (T element){
		if(!this.isEmpty()){
			if(this.getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
				
			}else{
				this.getNext().remove(element);
			}
		}
		
	}
	
	@Override
	public void insertFirst(T element) {
		if(isValid(element)){
			if(isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
				this.getPrevious().setNext(this);
			}else{
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>(); 
				
				//atualiza o novo nó com o valor atual
				newNode.setData(this.getData());
				//atualiza o nó atual com o novo valor
				this.setData(element);
				
				//mantém a relação de ordem com o no atual e o proximo nó
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(newNode);
				newNode.setNext(this.getNext());
				newNode.setPrevious(this);
				this.setNext(newNode);
								
			}			
		}
	}

	@Override
	public void removeFirst() {
		 if (!isEmpty()) {
	            this.setData(this.getNext().getData());

	            this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
	            if (!isNull()) {
	            	this.setNext(this.getNext().getNext());
	            	
	            } else {
	            	this.setNext(new RecursiveDoubleLinkedListImpl<T>());
	            }
	        }
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			//se o proximo for null 
			if(this.getNext().isNull()){
				this.setData(this.getNext().getData());
				this.setNext(null);
			}else{
				((DoubleLinkedList<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
