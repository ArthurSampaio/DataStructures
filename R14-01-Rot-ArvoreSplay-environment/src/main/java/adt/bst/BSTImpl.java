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


   protected BSTNode<T> remove(BSTNode<T> node) {
		BSTNode<T> removed = node;
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				//one leaf
				BSTNode<T> newThis = getLeft(node);
				if (newThis.isEmpty())
					newThis = getRight(node);
				
				node.setData(newThis.getData());
				node.setLeft(newThis.getLeft());
				node.setRight(newThis.getRight());
				if (getLeft(node) != null) {
					node.getLeft().setParent(node);
				}
				if (getRight(node) != null) {
					node.getRight().setParent(node);
				}
				
			//2 leafs
			} else {
				BSTNode<T> newThis = sucessor(node.getData());
				node.setData(newThis.getData());
				removed = remove(newThis);
			}
		}
		return removed;
	}

   private void removeTwoDegree(BSTNode<T> node) {
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


	//NEED FOR SPEED 
	
	
	/**
	 * Diz se uma BST tree1 eh igual a outra BST tree2. As duas BSTs devem ter 
	 * exatamente os mesmos nós e os mesmos formatos. Este metodo DEVE ser implementado
	 * usando recursão. Voce não pode colocar elementos em arrays e depois comparar os arrays. 
	 */
	public boolean equals(BST<T> tree2){
		
		if(tree2 != null)
			return equals(getRoot(), tree2.getRoot());
		else
			return false; 
		
	}

	private boolean equals(BTNode<T> root, BTNode<T> root2) {
		
		if(root == null && root2 == null)
			return true; 
		else if(root == null || root2 == null)
			return false; 
			
		boolean leftTree = equals(root.getLeft(), root2.getLeft());
		boolean sameElement = root.equals(root2);
		boolean rightTree = equals(root.getRight(), root2.getRight());
		
		return (leftTree && sameElement && rightTree );
		
	}
	
	/**
	 * Diz se uma BST tree1 eh similar a outra BST tree2. Duas BSTs sao similares 
	 * se elas possuem o mesmo formato (topologia). O conteudo de cada no é irrelevante.
	 * Este metodo DEVE ser implementado usando recursao. 
	 * Voce não pode colocar elementos em arrays e depois comparar os arrays.
	 */
	public boolean isSimilar(BST<T> tree2){
		
		return isSimilar(getRoot(), tree2.getRoot());
		
	}

	private boolean isSimilar(BTNode<T> root, BTNode<T> root2) {
		
		if(root == null && root2 == null)
			return true; 
		else if(root == null || root2 == null)
			return false; 
		
		boolean right = isSimilar(root.getRight(), root2.getRight());
		boolean left = isSimilar(root.getLeft(), root2.getLeft());
		
		return right && left;
		
		
	}
	
	/**
	 * A K-esima estatistica de ordem de um BST eh o k-esimo menor elemento que esta 
	 * na BST. Este metodo usa a BST para calcular a k-esima estatistica de ordem informada 
	 * no parametro k (variando de 1 ate N). Por exemplo, k = 1 pede para calcular a 1a estatistica de ordem, que eh o 
	 * elemento minimo da BST. k = n peda para calcular a ultima estatistica de ordem que eh
	 * o elemento maximo da BST e assim por diante. Considere o seguinte para implementar
	 * o metodo:
	 *  - Uso OBRIGATORIO de recursao 
	 *  - NÃO pode produzir array e depois selecionar elemento especifico do array
	 *  - retornar null e a k-esima estatistica de ordem nao esta presente na BST.
	 * @param k
	 * @return
	 */
	public T orderStatistic(int k){
		
		if(k <= 0 || k > this.size()) return null;
		
		BSTNode<T> min = minimum(root);
		if(min.isEmpty()) return null;
				
		for(int i = 1; i < k; i++){
			if(min.isEmpty())
				return null;
			min = sucessor(min.getData());
		}
		
		return min.getData();
		
		
	}
	
	/**
	 * It says if a BST tree1 contains another BST subtree. This method must 
	 * be implemented using recursion.  
	 */
	public boolean contains(BST<T> subtree){
		if(subtree != null){
			BSTNode<T> raiz = search(subtree.getRoot().getData());
			
			if(raiz != null){
				return contains(raiz, getRoot());
			}
		}
		return false;
	}

	private boolean contains(BSTNode<T> raiz, BSTNode<T> root2) {

		if(root2 == null)
			return true;
		if(raiz == null)
			return false;
		
		if(equals(raiz, root2)) return true;
		
		return contains(getLeft(raiz), root2) 
				|| contains(getRight(raiz), root2);
		
		
			
	}
	
	/**
	 * It returns the comon ancestor between two nodes of a BST. A common ancestor is the first common 
	 * node above node1 and node2. 
	 */
	public T commonAcestor(T e1, T e2){

		return commonAcestor (root, e1, e2);
	}

	private T commonAcestor(BSTNode<T> node, T e1, T e2) {
		if(node == null)
			return null;
		
		//se e1 e e2 é menor que a raiz, entao o ancestral está a esquerda
		if(node.getData().compareTo(e1) > 0 && node.getData().compareTo(e2) > 0)
			return commonAcestor(getLeft(node), e1, e2);
		if(node.getData().compareTo(e1) < 0 && node.getData().compareTo(e2) < 0)
			return commonAcestor(getRight(node), e1, e2);
		
		return node.getData();

	}

	/**
	 * Diz se um nó d é ou não descendete de um nó p.
	 * @param d
	 * @param p
	 * @return
	 */
	public boolean isDecendent(T d, T p){
		
		BSTNode<T> nodeD = search(d);
		
		if(nodeD==null) return false;
		
		while(!nodeD.getData().equals(p)){
			nodeD = getParent(nodeD);
			if(nodeD == null)
				return false;
		}
		
		return true;
	}
	
	public T[] walkLevels(BSTNode<T> no) {
	    if (no == null) throw new IllegalArgumentException("Tree node cannot be null!");
	    
	    ArrayList<T> out = new ArrayList<>();
	    Deque<BSTNode<T>> fila = new ArrayDeque<>();
	    fila.add(no);
	    while (!fila.isEmpty()) {
	    	BSTNode<T> atual = fila.removeFirst();
	    	out.add(atual.getData());
	        System.out.printf("%s ", atual.getData());
	        if (!atual.getLeft().isEmpty()) fila.add((BSTNode<T>) atual.getLeft());
	        if (!atual.getRight().isEmpty()) fila.add((BSTNode<T>) atual.getRight());
	    }
	    T[] array = (T[]) new Comparable[size()];
	    
	    out.toArray(array);
	    return array;
	}
	
	public int distance(T x1, T x2){
		
		if(!isDecendent(x2,x1)){
			throw new RuntimeException();
		}else{
			return distance(search(x1), search(x2));
		}
		
	}

	private int distance(BSTNode<T> search, BSTNode<T> search2) {
		if(search2.getData().equals(search.getData())){
			return 0;
		}else
			return 1 + distance(search, getParent(search2));
	
	}
	
	

}