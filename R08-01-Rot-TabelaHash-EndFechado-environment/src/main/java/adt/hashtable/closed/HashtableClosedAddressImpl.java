package adt.hashtable.closed;

import java.util.LinkedList;
import java.util.List;

import adt.hashtable.hashfunction.*;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size. For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction<T> function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
      this.initializesCell();
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number. You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {
       number++; 
       if(number % 2 == 0) number++;
	   while(!Util.isPrime(number))
		   number++;
	   return number; 
   }

   @Override
   public void insert(T element) {
	  
	   if(element!=null){
		   int hash = ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element); 
		   List<T> aux = this.getCell(hash);
		   boolean hasElement = aux.contains(element);
		   if(!hasElement){
			   if(!aux.isEmpty())
				   this.COLLISIONS++;
			   aux.add(element);
			   this.elements++;
		   }
	   }
	   
   }

   @Override
   public void remove(T element) {
	   
	   if(element != null){
		   int hash = ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element); 
		   LinkedList<T> aux = (LinkedList) this.table[hash];
		   
		   if(!aux.isEmpty()){
			   aux.remove(element);
			   this.elements--;
		   }
	   }
   }

   @Override
   public T search(T element) {
	   T result = null;
	   int hash = ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element); 
	   LinkedList<T> aux = (LinkedList) this.table[hash];
	   for(int i = 0; i < aux.size(); i++){
		   if(aux.get(i).equals(element))
			   result = aux.get(i);
	   }
	   
	   return result; 
	   
   }

   @Override
   public int indexOf(T element) {
      
	   int index = -1;
	   if(element != null && !this.isEmpty()){
		  if(this.searchElementInCell(element) != -1)
			   index = this.getHashFunction().hash(element);
	   }
	   return index; 
	   
   }
   

   @Override
	public HashFunctionClosedAddress<T> getHashFunction() {
		return (HashFunctionClosedAddress<T>) super.getHashFunction();
	}

   private void initializesCell(){
	   
	   for(int i = 0; i < this.capacity(); i++)
		   this.table[i] = new LinkedList<T>();

   }
   
   private LinkedList<T> getCell(int index){
	   return (LinkedList<T>) this.table[index];
   }
   
   private int searchElementInCell(T element){
	   
	   int hash = this.getHashFunction().hash(element); 
	   List<T> aux = this.getCell(hash);
	   return aux.indexOf(element);

   }
   
 

}
