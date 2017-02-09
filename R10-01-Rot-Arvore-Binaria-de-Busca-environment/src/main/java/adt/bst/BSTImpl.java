package adt.bst;

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
		
		insert(root, element);
		
	}

	private void insert(BSTNode<T> node, T element) {
		
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(buildBSTNode());
				node.setRight(buildBSTNode());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (element.compareTo(node.getData()) > 0) {
					insert(getRight(node), element);
				} else if (element.compareTo(node.getData()) < 0) {
					insert(getLeft(node), element);
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

		BSTNode<T> find = search(element);
		if(find.isEmpty())
			return null;
		return sucessor(find);
	
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		
		if(!node.getRight().isEmpty())
			return minimum(getRight(node));
		else{
			BSTNode<T> out = getParent(node);
			if((!out.isEmpty() && node.equals(getRight(out)))){
				return sucessor(getParent(out));
			}else{
				return out;
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		
		BSTNode<T> find = search(element);
		if(find.isEmpty()) return null;
		else
			return predecessor(find);
		
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {

		if(!node.isEmpty()) 
			return maximum(getLeft(node));
		else{
			BSTNode<T> out = getParent(node);
			if((!out.isEmpty() && node.equals(getLeft(out)))){
				return sucessor(getParent(out));
			}else{
				return out;
			}
		}
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		
		T[] array = (T[]) new Comparable[size()];
		
		if(!root.isEmpty()){
			preOrder(array, root, 0);	
		}
		
		return array;

	}

	

	private void preOrder(T[] array, BSTNode<T> node, int ind) {
		
		if(!node.isEmpty()){
			array[ind] = node.getData();
			ind++;
			preOrder(array, getLeft(node),ind);
			preOrder(array, getRight(node), ind);
			
		}
	}

	@Override
	public T[] order() {
		
		T[] array = (T[]) new Comparable[size()];
		
		if(!root.isEmpty()){
			order(array, root, 0);	
		}
		return array;
	}

	private void order(T[] array, BSTNode<T> root2, int i) {
		
		
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
