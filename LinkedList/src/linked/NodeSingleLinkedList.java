package linked;

public class NodeSingleLinkedList<T> {
	
	protected T data; 
	protected NodeSingleLinkedList next;
	
	//NIL
	public NodeSingleLinkedList(){}
	
	public NodeSingleLinkedList(T data, NodeSingleLinkedList next){
		this.data = data; 
		this.next = next;
	}
	
	public boolean isNil(){
		return data == null;
	}
	

}
