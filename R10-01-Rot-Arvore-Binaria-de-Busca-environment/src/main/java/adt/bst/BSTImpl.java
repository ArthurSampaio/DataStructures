package adt.bst;

import java.util.ArrayList;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	
	protected BSTNode<T> root;

	public BSTImpl() {
		root = buildBSTNode();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		if(node.isEmpty()) return -1; 
		else{
			return Math.max(this.height(getRight(node)), this.height(getLeft(node))) + 1;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		
		if(element != null){
			
			if(node.isEmpty() || node.getData().equals(element))
				return node;
			else if (element.compareTo(node.getData()) < 0 )
				return search(getLeft(node), element);
			else if (element.compareTo(node.getData()) > 0 )
				return search(getRight(node), element);

		}
		return new BSTNode<>();
	}

	@Override
	public void insert(T element) {
		
		insert(root, element, null);
		
	}

	private void insert(BSTNode<T> node, T element, BSTNode<T> node2) {
		
		if (element != null) {
			if (node.isEmpty()) {
				node.setParent(node2);
				node.setData(element);
				node.setLeft(buildBSTNode());
				node.setRight(buildBSTNode());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (element.compareTo(node.getData()) > 0) {
					insert(getRight(node), element, node);
				} else if (element.compareTo(node.getData()) < 0) {
					insert(getLeft(node), element, node);
				}
			}
		}
		
	}


	@Override
	public BSTNode<T> maximum() {

		return maximum(this.root);
		
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		
		if(node.isEmpty()) 
			return null;
		else if(node.getRight().isEmpty()) 
			return node;
		else 
			return maximum(getRight(node));
	}

	@Override
	public BSTNode<T> minimum() {

		return minimum(this.root);
	}


	private BSTNode<T> minimum(BSTNode<T> node) {
		if(node.isEmpty()) 
			return null;
		else if(node.getLeft().isEmpty()) 
			return node;
		else 
			return minimum((BSTNode<T>) node.getLeft());
		
	}

	@Override
	public BSTNode<T> sucessor(T element) {

		BTNode<T> result = null;
		BTNode<T> auxNode = search(element);

		if (auxNode.isEmpty()) {
			return null;
		}
		if (!auxNode.getRight().isEmpty()) {
			result = minimum((BSTNode<T>) auxNode.getRight());
		} else {
			result = auxNode.getParent();
			while (result != null && auxNode.equals(result.getRight())) {
				auxNode = result;
				result = result.getParent();
			}
		}
		return (BSTNode<T>) result;
	
	}


	@Override
	public BSTNode<T> predecessor(T element) {
		BTNode<T> result = null;
		BTNode<T> auxNode = search(element);

		if (auxNode.isEmpty()) {
			return null;
		}
		if (!auxNode.getLeft().isEmpty()) {
			result = maximum((BSTNode<T>) auxNode.getLeft());
		} else {
			result = auxNode.getParent();
			while (result != null && auxNode.equals(result.getLeft())) {
				auxNode = result;
				result = result.getParent();
			}
		}

		return (BSTNode<T>) result;
		
	}

	

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		
		ArrayList<T> array = new ArrayList<>();
		T[] out = (T[]) new Comparable[size()];
		
		if(!root.isEmpty()){
			preOrder(array, root);	
			array.toArray(out);
		}
		
		return out;

	}

	private void preOrder(ArrayList array, BSTNode<T> node) {
		
		if(!node.isEmpty()){
			array.add(node.getData());
			
			preOrder(array, getLeft(node));
			preOrder(array, getRight(node));
			
		}
	}

	@Override
	public T[] order() {
		
		T[] array = (T[]) new Comparable[size()];
		ArrayList<T> aux = new ArrayList<>();
		if(!root.isEmpty()){
			order(aux, root);	
			aux.toArray(array);
		}
		return array;
	}

	private void order(ArrayList array, BSTNode<T> node) {
		
		if(!node.isEmpty()){
			order(array, getLeft(node));
			array.add(node.getData());
			order(array, getRight(node));
		}
		
	}

	@Override
	public T[] postOrder() {
		
		T[] array = (T[]) new Comparable[size()];
		ArrayList<T> out = new ArrayList<>();
		
		if(!root.isEmpty()){
			postOrder(out, root);	
			out.toArray(array);
		}
		return array;
	}

	private void postOrder(ArrayList array, BSTNode<T> node) {
	
		if(!node.isEmpty()){
			
			postOrder(array, getLeft(node));
			postOrder(array, getRight(node));
			array.add(node.getData());
		}
		
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
	
	//make the cast to node.getLeft()
	private BSTNode<T> getLeft(BSTNode<T> node){
		return (BSTNode<T>) node.getLeft();
	}
	
	//make the cast to node.getRight();
	private BSTNode<T> getRight(BSTNode<T> node){
		return (BSTNode<T>) node.getRight();
	}
	
	private BSTNode<T> getParent(BSTNode<T> node){
		return (BSTNode<T>) node.getParent();
	}
	
	private BSTNode<T> buildBSTNode(){
		return (BSTNode<T>) new BSTNode.Builder<T>().build();
	}
	

}
