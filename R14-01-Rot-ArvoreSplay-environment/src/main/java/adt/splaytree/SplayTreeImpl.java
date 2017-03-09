package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

   @Override
   public void insert(T element) {
      if (element != null) {
         super.insert(element);
         BSTNode<T> node = super.search(element);
         splay(node);
      }
   }

   @Override
   public BSTNode<T> search(T element) {
      BSTNode<T> node = super.search(element);
      if (node.isEmpty()) {
         //se o node for vazio chama o splay para o ultimo elemento valido
         splay(getParent(node));
      } else
         splay(node);
      return node;
   }

   @Override
   public void remove(T element) {
      if (element != null) {
         BSTNode<T> removeNode = super.search(element);
         if (!removeNode.isEmpty()){
            super.remove(removeNode);
            splay(getParent(removeNode));

         }
      }
   }

   private void splay(BSTNode<T> node) {
      if (node != null && node.getParent() == null)
         this.root = node;
      else {
         if (node != null) {
            boolean avo = getParent(getParent(node)) != null;
            //if I am is a leftsoon
            if (node.equals(node.getParent().getLeft())) {
               //se o filho, pai e avo sao do msm lado
               boolean sameSide = avo && getParent(node).equals(getParent(getParent(node)).getLeft());
               if (sameSide)
                  //rotaciona o avó
                  Util.rightRotation(getParent(getParent(node)));
               //rotaciona o pai a direita
               Util.rightRotation(getParent(node));
               //se eu tenho avó mas, n estamos do mesmo lado, rotaciono meu pai para a esquerda
               if (avo && !sameSide)
                  Util.leftRotation(getParent(node));
            } else { //se eu sou filho a direita
               boolean sameSide = avo && getParent(node).equals(getParent(getParent(node)).getRight());
               if (sameSide)
                  Util.leftRotation(getParent(getParent(node)));
               //rotaciona o pai para o mesmo lado
               Util.leftRotation(getParent(node));
               if (avo && !sameSide)
                  Util.rightRotation(getParent(node));

            }
            splay(node);

         }
      }
   }

}
