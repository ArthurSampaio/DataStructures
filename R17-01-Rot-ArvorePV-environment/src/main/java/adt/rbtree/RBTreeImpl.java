package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {



	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) this.root);
	}

	private int blackHeight(RBNode<T> node) {
		if (node.isEmpty())
			//is nil
			return 1;

		int height;

		if (isBlack(node))
			height = 1;
		else
			height = 0;

		return height + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}


	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return getRoot().getColour() == Colour.BLACK; 
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true;
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes(getRoot());
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				if (!isBlack(this.getLeft(node)) || !isBlack(this.getRight(node))) {
					return false;
				}
			}
			return this.verifyChildrenOfRedNodes(this.getLeft(node))
					&& this.verifyChildrenOfRedNodes(this.getRight(node));
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		int leftHeight = verifyBlackHeight(getLeft(getRoot()), 0);
		int rightHeight = verifyBlackHeight(getRight(getRoot()), 0);

		if (leftHeight == rightHeight)
			return true;

		return false;
	}

	private int verifyBlackHeight(RBNode<T> node, int height) {
		if (node != null && !node.isEmpty()) {
			if (isBlack(node))
				height++;

			return Math.max(verifyBlackHeight(getLeft(node), height),
					verifyBlackHeight(getRight(node), height));
		}

		height++;
		return height;
	}

	@Override
	public void insert(T value) {
		if (value != null)
			this.insert(this.getRoot(), value);
	}

	/**
	 * Insert recursively a node in tree
	 * 
	 * @param node
	 * @param parent
	 * @param element
	 */
	private RBNode<T> insert(RBNode<T> node, T element) {
		RBNode<T> auxNode = node;
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else if (element.compareTo(node.getData()) < 0) {
			this.insert(getLeft(node), element);
		} else if (element.compareTo(node.getData()) > 0) {
			this.insert(getRight(node), element);
		}
		return auxNode;

	}

	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] array = new RBNode[size()];
		rbPreOrder(array, 0, (RBNode<T>) this.getRoot());

		return array;
	}

	private int rbPreOrder(RBNode<T>[] array, int index, RBNode<T> node) {
		if (!node.isEmpty()) {
			array[index++] = node;
			index = rbPreOrder(array, index, getLeft(node));
			index = rbPreOrder(array, index, getRight(node));
		}
		return index;
	}

	
	
	// se ligar nas parada do material de EDA
	
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(root))
			node.setColour(Colour.BLACK);
		else
			fixUpCase2(node);
	}
	
	protected void fixUpCase2(RBNode<T> node) {

		if (!isBlack(getParent(node)))
			fixUpCase3(node);

	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> father = getParent(node);
		RBNode<T> grandFather = getParent(father);
		RBNode<T> uncle;

		if (isLeftChild(father))
			uncle = getRight(grandFather);
		else
			uncle = getLeft(grandFather);

		if (!isBlack(uncle)) {
			father.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandFather.setColour(Colour.RED);
			fixUpCase1(grandFather);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		//aqui é só para alinhar os nós
		RBNode<T> next = node;
		RBNode<T> parent = getParent(node);

		if (!((isLeftChild(node) && isLeftChild(parent)) || (!isLeftChild(node) && !isLeftChild(parent)))) {
			
			if (isLeftChild(node))
				Util.rightRotation(parent);
			else
				Util.leftRotation(parent);
			
			fixUpCase5(parent);

		} else {
			fixUpCase5(next);
		}
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = getParent(node);
		RBNode<T> grandParent = getParent(parent);

		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED);

		if (isLeftChild(node))
			Util.rightRotation(grandParent);
		else
			Util.leftRotation(grandParent);
	}

	/**
	 * Verifies if the node's color is black
	 * 
	 * @param node
	 * @return true if black,false otherwise
	 */
	private boolean isBlack(RBNode<T> node) {
		if (node != null) {
			return node.getColour() == Colour.BLACK;
		}

		return true;
	}
	
	private boolean isLeftChild(RBNode<T> node) {
		return node.equals(node.getParent().getLeft());
	}
	
	private RBNode<T> getLeft(BSTNode<T> node){
		return (RBNode<T>) node.getLeft();
	}
	
	private RBNode<T> getRight(BSTNode<T> node){
		return (RBNode<T>) node.getRight();
	}
	
	private RBNode<T> getParent(BSTNode<T> node){
		return (RBNode<T>) node.getParent();
	}
	
	@Override
	public RBNode<T> getRoot(){
		return (RBNode<T>) this.root;
	}
}