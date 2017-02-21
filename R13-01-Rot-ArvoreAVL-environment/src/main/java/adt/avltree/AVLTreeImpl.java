package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void insert(T element){
		super.insert(element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);
	}
	
	@Override
	public void remove(T element){
		BSTNode<T> node = search(element);
		super.remove(node);
		rebalanceUp(getParent(node));
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if(!node.isEmpty())
			return super.height(getRight(node)) - super.height(getLeft(node));
		return 0;	
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		
		if (balance < -1) {
			//rotação dupla a direita
			//dupla a direita: esquerda + direita

			
			if (calculateBalance(getLeft(node)) > 0) {
				leftRotation(getLeft(node));
			}
			rightRotation(node);
		} else if (balance > 1) {
			//rotação dupla a esquerda
			//dupla a esquerda : rotação a direita + esquerda
			if (calculateBalance(getRight(node)) < 0) {
				rightRotation(getRight(node));
			}
			leftRotation(node);
		}

	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if(node != null && !node.isEmpty()){
			rebalance(node);
			rebalanceUp(getParent(node));
		}
			
	}
	
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}
}
