package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	private static final int NOT_FOUND = -1;

	
	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
		this.initializedTable();

	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(!super.isFull()){
				int index = this.findUniqueHash(element); 
				if(index == NOT_FOUND) return;
				this.table[index] = element; 
				this.elements++;
			}else{
				throw new HashtableOverflowException();
			}
		}
	}

	
	@Override
	public void remove(T element) {
		if(element != null || !isEmpty()){
			int index = this.indexOf(element); 
			if(index != NOT_FOUND){
				this.table[index] = this.deletedElement;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		if (element != null || isEmpty()) {
			int i = 0;
			int ind = getHashFunction().hash(element, i);
			while (i < super.capacity() && table[ind] != null && !deletedElement.equals(table[ind])) {
				if (table[ind].equals(element)) {
					return element;
				} else {
					i++;
					ind = getHashFunction().hash(element, i);
				}
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		//if element is null or empty
		if(element != null || !this.isEmpty()){
			int index = NOT_FOUND; 
			int probe = 0; 
			index = this.getHashFunction().hash(element, probe);

			while(probe < capacity() && table[index]!= null && !table[index].equals(deletedElement)){
				
				if(table[index].equals(element))
					return index;
				else{
					probe++; 
					index = this.getHashFunction().hash(element, probe);
				}
			}
		}
		return NOT_FOUND; 
	}
	
   @Override
	public HashFunctionOpenAddress<T> getHashFunction() {
		return (HashFunctionOpenAddress<T>) super.getHashFunction();
	}
	
	private int findUniqueHash(T element){
		
		int probe = 0; 
		int ind = this.getHashFunction().hash(element, probe);
		int aux_colisions = 0; 
		
		while(this.table[ind] != null && !this.table[ind].equals(this.deletedElement)){
			
			//checks if the element already be in this.table
			//duplicated elements
			if(this.table[ind].equals(element)){
				ind = NOT_FOUND; 
				break;
			}else{
				aux_colisions++;
				probe++; 
				ind = this.getHashFunction().hash(element, probe);
			}
			
		}
		
		//if element not in the table
		//update COLLISIONS with aux_colisions
		if(ind != -1){
			this.COLLISIONS += aux_colisions; 
		}
		return ind;
		
	}
	
	private void initializedTable(){
		for(int i = 0; i < this.capacity(); i++)
			this.table[i] = null;
	}

}
