package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
		
	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return isNull(); 
	}

	protected boolean isNull() {
		return this.getData()==null;
	}

	@Override
	public int size() {
		if(this.isNull()) return 0; 
		else 
			return 1 + this.getNext().size();
	}

	@Override
	public T search(T element) {
		if(!this.isNull() && this.getData().equals(element)){
			return element;
		}else{
			if(this.isNull()) return null;
			else
				return this.getNext().search(element);
		}
	}

	@Override
	public void insert(T element) {
		if(isValid(element)){
			if(this.isNull()){
				this.setData(element);
				this.setNext( new RecursiveSingleLinkedListImpl<>());
			}else{
				this.getNext().insert(element);
			}
		}
	}

	protected boolean isValid(T element){
		return element != null;
	}
	
	@Override
	public void remove(T element) {
		if(isValid(element)){
			
			if(this.isNull()) return;
			if(!this.isNull() && this.getData().equals(element)){
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			}else
				this.getNext().remove(element);
			
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] out = (T[]) new Object[this.size()];
		
		if(isValid(getData())){
			int cont = 0; 
			out[cont] = this.getData();
			toArray(out, cont, this.getNext());
		}
		
		return out;
	}

	private void toArray(T[] out, int cont, RecursiveSingleLinkedListImpl<T> node) {
		if(node.isNull()) return; 
		else{
			out[++cont] = node.getData();
			toArray(out, cont, node.getNext());
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
