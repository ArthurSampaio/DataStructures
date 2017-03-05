package adt.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class HeapQueue<T> extends HeapImpl<Integer>{

	
	public Map<Integer, T> mapa; 
	public int countElements; 
	
	public HeapQueue() {
		super((o1,o2) -> o2.compareTo(o1));
		// TODO Auto-generated constructor stub
		mapa = new HashMap<Integer, T>();
		countElements = 0;
	}
	

		
	/**
	 * Inserts a new element in the queue or returns an exception if the queue
	 * is full. Null elements are not allowed (the queue remains unchanged).
	 * 
	 * @param element
	 * @throws QueueOverflowException
	 */
	public void enqueue(T element){
		
		if(element != null){
			Integer key = generateScore();
			mapa.put(key, element);
			super.insert(key);
		}

	}

	private Integer generateScore() {
		countElements++;
		return countElements;
	}



	/**
	 * If the queue has elements, it removes the oldest of the queue and returns
	 * it; otherwise, it returns an exception.
	 * 
	 * @return
	 * @throws QueueUnderflowException
	 */
	public T dequeue() {
		
		Integer value = extractRootElement();
		T object = mapa.get(value);
		mapa.remove(value);
		return object;

	}

	/**
	 * Returns (without removing) the oldest element of the stack or null if the
	 * queue is empty.
	 * 
	 * @return
	 */
	public T head(){
		Integer value = (Integer) super.rootElement();
		T object = mapa.get(value);
		return object;
		
	}

	/**
	 * Returns true if the queue is empty or false, otherwise.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return super.isEmpty();
		
	}

	/**
	 * Returns true if the queue is full or false, otherwise.
	 * 
	 * @return
	 */
	public boolean isFull(){
		return false;
	}
}
