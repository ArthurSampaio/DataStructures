package adt.bst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
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
      int result = -1;

      if (!node.isEmpty()) {
         result = Math.max(height(getLeft(node)), height(getRight(node))) + 1;
      }
      return result;

   }

   @Override
   public BSTNode<T> search(T element) {

      return search(this.root, element);

   }

   private BSTNode<T> search(BSTNode<T> node, T element) {

      if (element != null) {

         if (node.isEmpty() || node.getData().equals(element))
            return node;
         else if (element.compareTo(node.getData()) < 0)
            return search(getLeft(node), element);
         else if (element.compareTo(node.getData()) > 0)
            return search(getRight(node), element);

      }
      return buildNode();

   }

   @Override
   public void insert(T element) {
      if (element != null)
         insert(root, element, null);
   }

   protected BSTNode<T> insert(BSTNode<T> node, T element, BSTNode<T> prev) {

	  BSTNode<T> add = buildNode();
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(buildNode());
         node.setRight(buildNode());
         node.setParent(prev);
         node.getRight().setParent(node);
         node.getLeft().setParent(node);

      } else {
         if (element.compareTo(node.getData()) < 0)
        	 add =  insert(getLeft(node), element, node);
         else if (element.compareTo(node.getData()) > 0)
        	 add= insert(getRight(node), element, node);
      }
      return add;
   }

   @Override
   public BSTNode<T> maximum() {
      return maximum(root);
   }

   private BSTNode<T> maximum(BSTNode<T> node) {
      if (node.isEmpty())
         return null;

      else if (node.getRight().isEmpty())
         return node;
      else
         return maximum(getRight(node));

   }

   @Override
   public BSTNode<T> minimum() {
      return minimum(root);
   }

   private BSTNode<T> minimum(BSTNode<T> node) {
      if (node.isEmpty())
         return null;
      else if (node.getLeft().isEmpty())
         return node;
      else
         return minimum(getLeft(node));
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      if (getRoot().isEmpty() || element == null) {
         return null;
      }
      BSTNode<T> node = search(element);

      if (node.isEmpty())
         return null;

      if (node.getRight().getData() != null) {
         return minimum(getRight(node));
      } else {

         return sucessor(node);
      }
   }

   private BSTNode<T> sucessor(BTNode<T> atual) {
      if (atual.getParent() == null) {
         return null;
      } else {
         if (atual.equals(atual.getParent().getLeft())) {
            return (BSTNode<T>) atual.getParent();
         } else {
            return sucessor(atual.getParent());
         }
      }
   }

   @Override
   public BSTNode<T> predecessor(T element) {

      if (this.getRoot().isEmpty() || element == null)
         return null;
      BSTNode<T> aux = this.search(element);
      if (aux.isEmpty())
         return null;

      if (aux.getLeft().getData() != null)
         return this.maximum(getLeft(aux));
      else
         return predecessor(aux);

   }

   private BSTNode<T> predecessor(BSTNode<T> aux) {
      if (aux.getParent() == null)
         return null;

      else {
         if (aux.getParent().getRight().equals(aux))
            return getParent(aux);
         else
            return predecessor(getParent(aux));
      }
   }

   @Override
   public void remove(T element) {

      BSTNode<T> node = search(element);

      if (!node.isEmpty()) {
         if (node.isLeaf())
            removeLeaf(node);
         else if (node.getLeft().isEmpty() || node.getRight().isEmpty())
            removeOneDegree(node);
         else
            removeTwoDegree(node);
      }

   }

   protected void remove(BSTNode node) {
      if (!node.isEmpty()) {
         if (node.isLeaf())
            removeLeaf(node);
         else if (node.getLeft().isEmpty() || node.getRight().isEmpty())
            removeOneDegree(node);
         else
            removeTwoDegree(node);
      }
   }

   private void removeTwoDegree(BSTNode<T> node) {
      // TODO Auto-generated method stub
      BSTNode<T> sucessor = sucessor(node.getData());
      T eleSucessor = sucessor.getData();
      removeOneDegree(sucessor);
      node.setData(eleSucessor);
   }

   private void removeOneDegree(BSTNode<T> node) {

      if (node.equals(this.root)) {

         if (node.getLeft().isEmpty()) {
            this.root = getRight(node);
         } else
            this.root = getLeft(node);
         this.root.setParent(buildNode());

      } else {

         if (node.equals(node.getParent().getRight())) {
            //node esta a direita de node.getParent()
            if (node.getRight().isEmpty()) {
               node.getParent().setRight(node.getLeft());
               node.getLeft().setParent(node.getParent());
            } else {
               node.getParent().setRight(node.getRight());
               node.getRight().setParent(node.getParent());
            }
         } else {
            //node está a esquerda de node.getParent()
            if (node.getLeft().isEmpty()) {
               node.getParent().setLeft(node.getRight());
               node.getRight().setParent(node.getParent());
            } else {
               node.getParent().setLeft(node.getLeft());
               node.getLeft().setParent(node.getParent());
            }

         }

      }
   }

   private void removeLeaf(BSTNode<T> node) {
      node.setData(null);
   }

   @Override
   public T[] preOrder() {

      T[] out = (T[]) new Comparable[size()];
      ArrayList<T> array = new ArrayList<>();
      preOrder(array, root);
      return array.toArray(out);

   }

   private void preOrder(ArrayList<T> array, BSTNode<T> node) {

      if (node.isEmpty())
         return;
      array.add(node.getData());
      preOrder(array, getLeft(node));
      preOrder(array, getRight(node));

   }

   @Override
   public T[] order() {

      T[] out = (T[]) new Comparable[size()];
      ArrayList<T> array = new ArrayList<>();
      order(array, root);
      return array.toArray(out);

   }

   private void order(ArrayList<T> array, BSTNode<T> node) {

      if (node.isEmpty())
         return;
      order(array, getLeft(node));
      array.add(node.getData());
      order(array, getRight(node));

   }

   @Override
   public T[] postOrder() {

      T[] out = (T[]) new Comparable[size()];
      ArrayList<T> array = new ArrayList<>();
      postOrder(array, root);
      return array.toArray(out);

   }

   private void postOrder(ArrayList<T> array, BSTNode<T> node) {

      if (node.isEmpty())
         return;
      postOrder(array, getLeft(node));
      postOrder(array, getRight(node));
      array.add(node.getData());

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
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

   protected BSTNode<T> getLeft(BTNode<T> node) {
      return (BSTNode<T>) node.getLeft();

   }

   protected BSTNode<T> getRight(BTNode<T> node) {
      return (BSTNode<T>) node.getRight();
   }

   protected BSTNode<T> getParent(BTNode<T> node) {
      return (BSTNode<T>) node.getParent();
   }

   protected BSTNode<T> buildNode() {
      return (BSTNode<T>) new BSTNode.Builder<T>().build();
   }


}