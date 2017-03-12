package linked;

import javax.xml.soap.Node;

public class SingleLinkedList<T extends Comparable<T>> {

	protected NodeSingleLinkedList<T> head; 
	protected int size; 
	
	public SingleLinkedList(){
		head = new NodeSingleLinkedList<T>();
		size = 0;
	}
	
	public boolean isEmpty() {
		return head.isNil();
	}
	
	public void insert(T item){
		NodeSingleLinkedList<T> aux = head; 
		size++; 
				
		if(head.isNil()){
			NodeSingleLinkedList<T> newHead =new NodeSingleLinkedList(item,null);
			newHead.next = head;
			head = newHead;
		}else{
			
			while(aux.next!=null){
				aux = aux.next;
			}
			
			NodeSingleLinkedList<T> newNode = new NodeSingleLinkedList<>(item, null);
			newNode.next = aux.next;
			aux.next = newNode;
		}

	}
	
	public NodeSingleLinkedList search(T item){
	
		NodeSingleLinkedList<T> aux = this.head;
		
		while (aux != null && !aux.data.equals(item)){
			aux = aux.next;
			
		}
		
		return aux;
			
	}
		
} 


