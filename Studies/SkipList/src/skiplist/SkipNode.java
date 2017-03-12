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
	
	public void setForward(int i, SkipNode<T> newData){
		this.forward[i] = newData; 
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getKey() {
		return key;
	}

	public SkipNode<T> getNode(int i){
		return forward[i];
	}
	
	public void setKey(int key) {
		this.key = key;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
