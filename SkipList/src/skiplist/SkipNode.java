package skiplist;

public class SkipNode <T extends Comparable> {
	
	SkipNode<T>[] forward; 
	int height; 
	int key; 
	T data; 
	
	public SkipNode(int key, int height, T data){
		
		this.key = key;
		this.height = height; 
		this.data = data; 
		this.forward = new SkipNode[this.height];
		
	}

}
