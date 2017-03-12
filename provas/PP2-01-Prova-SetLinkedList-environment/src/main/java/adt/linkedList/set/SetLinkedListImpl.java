package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	
	public SetLinkedListImpl() {
		super();
	}
	
	@Override
	public void removeDuplicates() {
		
		SingleLinkedListNode<T> aux = this.getHead();
		
		while(!aux.isNIL()){
			
			SingleLinkedListNode<T> temp = aux.getNext();
			
			while(!temp.isNIL()){
				
				if(temp.getData().equals(aux.getData())){
					
					if(temp.getNext().isNIL()){
						temp.setData(null);
					}else{
						temp.setData(temp.getNext().getData());
						temp.setNext(temp.getNext().getNext());
					}
				}

				temp = temp.getNext();

			}		
			aux = aux.getNext();
			
		}

	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		
		if(otherSet!= null){
			//conjunto n importa a ordem
			SingleLinkedListNode<T> aux = getHead();
			while(!aux.isNIL()){
				otherSet.insert(aux.getData());
				aux = aux.getNext();
			}
			otherSet.removeDuplicates();
			
			return otherSet; 
		}else 
			return null;
		
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		
		SetLinkedList<T> out = new SetLinkedListImpl<>();
		
		SetLinkedListImpl<T> other = (SetLinkedListImpl<T>) otherSet;
		other.removeDuplicates();
		this.removeDuplicates();
		
		SingleLinkedListNode<T> aux = this.getHead();
		
		while(!aux.isNIL()){
			
			SingleLinkedListNode<T> aux2 = other.getHead();
			
			while(!aux2.isNIL()){
				
				if(aux2.getData().equals(aux.getData())){
					out.insert(aux2.getData());
				}
				
				aux2 = aux2.getNext();
				
			}
			
			aux = aux.getNext(); 

			
		}
		
		out.removeDuplicates();
		return out; 

		
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {

		if(otherSet != null){
		
			SingleLinkedListImpl<T> aux = (SingleLinkedListImpl<T>) otherSet;
			
			SingleLinkedListNode<T> temp = this.getHead();
			
			while(!temp.isNIL()){
				temp = temp.getNext();
			}

			temp.setData(aux.getHead().getData());
			temp.setNext(aux.getHead().getNext());
		}
		
		this.removeDuplicates();
	}

}
