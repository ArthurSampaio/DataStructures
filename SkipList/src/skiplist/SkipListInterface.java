package skiplist;

public interface SkipListInterface <T extends Comparable> {
	
	void insert(int key, T newValue);
	void insert(int key, T newValue, int newHeight);
	void remove (int key);
	SkipNode<T> search(int key);
	public int getSize(); 
	SkipNode<T>[] toArray(); 
	

}
