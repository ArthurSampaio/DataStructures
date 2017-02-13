package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

   private Comparator<T> comparator;

   public SortComparatorBSTImpl(Comparator<T> comparator) {
      super();
      this.comparator = comparator;
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
            node.setLeft(new BSTNode<>());
            node.setRight(new BSTNode<>());
            node.getLeft().setParent(node);
            node.getRight().setParent(node);
         } else {
            if (this.comparator.compare(element, node.getData()) > 0) {
               insert(getRight(node), element, node);
            } else if (this.comparator.compare(element, node.getData()) < 0) {
               insert(getLeft(node), element, node);
            }
         }
      }

   }

   @Override
   public BSTNode<T> search(T element) {
      return search(this.root, element);
   }

   private BSTNode<T> search(BSTNode<T> node, T element) {

      if (element != null) {

         if (node.isEmpty() || node.getData().equals(element))
            return node;
         else if (this.comparator.compare(element, node.getData()) < 0)
            return search(getLeft(node), element);
         else if (this.comparator.compare(element, node.getData()) > 0)
            return search(getRight(node), element);

      }
      return new BSTNode<>();
   }

   /**
    * Metodo que recebe um array de itens e retorna o array ordenado segundo o comparator da BST.
    * @param array
    * @return
    */
   @Override
   public T[] sort(T[] array) {

      //can i do this? 
      //Arrays.sort(array,comparator); 
      // return array
      //????
      BSTImpl<T> bst = new BSTImpl<>();
      for (int i = 0; i < array.length; i++) {
         bst.insert(array[i]);
      }
      return bst.order();

   }

   /**
    * Novo metodo de percurso na BST que percorre a arvore de forma contraria ao percurso
    * em ordem. Dessa forma a arvore eh percorrida: DIR, RAIZ, ESQ.
    * Obs: voce nao pode usar nenhum metodo de percurso implementado. Voce precisa implementar
    * essa nova forma de percurso.
    * 
    * @return
    */
   @Override
   public T[] reverseOrder() {

      T[] array = (T[]) new Comparable[super.size()];
      ArrayList<T> aux = new ArrayList<>();
      if (!super.root.isEmpty()) {
         reverseOrder(aux, root);
         aux.toArray(array);

      }
      return array;

   }

   private void reverseOrder(ArrayList<T> array, BSTNode<T> node) {
      if (!node.isEmpty()) {
         reverseOrder(array, this.getRight(node));
         array.add(node.getData());
         reverseOrder(array, this.getLeft(node));

      }

   }

   //make the cast to node.getLeft()
   private BSTNode<T> getLeft(BSTNode<T> node) {
      return (BSTNode<T>) node.getLeft();
   }

   //make the cast to node.getRight();
   private BSTNode<T> getRight(BSTNode<T> node) {
      return (BSTNode<T>) node.getRight();
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

}
