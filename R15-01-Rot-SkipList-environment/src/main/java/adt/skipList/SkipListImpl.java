   package adt.skipList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SkipListImpl<T> implements SkipList<T> {
   
      protected SkipListNode<T> root;
      protected SkipListNode<T> NIL;
   
      protected int maxHeight;
   
     protected double PROBABILITY = 0.5;
  
     public SkipListImpl(int maxHeight) {
        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
     }
  
     /**
      * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
      * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
      * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
      * metodo deve conectar apenas o forward[0].
      */
     private void connectRootToNil() {
        for (int i = 0; i < maxHeight; i++) {
           root.forward[i] = NIL;
        }
     }
  

     
   

	@Override
     public void insert(int key, T newValue, int height) {
        if (newValue != null) {
           SkipListNode<T>[] atualiza = new SkipListNode[height];
           SkipListNode<T> aux = root;
  
           for (int i = height - 1; i >= 0; i--) {
              while (aux.getForward(i) != NIL && aux.getForward(i).getKey() < key) {
                 aux = aux.getForward(i);
              }
              atualiza[i] = aux;
           }
  
           aux = aux.getForward(0);
  
           if (aux.getKey() == key) {
              aux.setValue(newValue);
           } else {
              ajustRoot(height, atualiza);
              aux = new SkipListNode<T>(key, height, newValue);
              changeRefers(height, atualiza, aux);
           }
        }
     }
     
 

  
     private void changeRefers(int height, SkipListNode<T>[] atualiza, SkipListNode<T> aux) {
        for (int i = 0; i < height; i++) {
           if (atualiza[i].getForward(i) == null) {
              aux.getForward()[i] = NIL;
           } else {
              aux.forward[i] = atualiza[i].forward[i];
              atualiza[i].forward[i] = aux;
           }
        }
     }
  
     private void ajustRoot(int height, SkipListNode<T>[] atualiza) {
        if (height > this.maxHeight) {
           for (int i = this.maxHeight; i < height; i++) {
              root.getForward()[i] = NIL;
           }
           this.maxHeight = height;
        }
     }
  
     @Override
     public void remove(int key) {
        SkipListNode[] update = new SkipListNode[this.maxHeight];
        SkipListNode<T> auxRoot = this.root;
  
        for (int i = maxHeight - 1; i >= 0; i--) {
           if (auxRoot.forward[i] != this.NIL) {
              while (auxRoot.forward[i].value != null && auxRoot.forward[i].key < key)
                 auxRoot = auxRoot.forward[i];
           }
           update[i] = auxRoot;
        }
        auxRoot = auxRoot.getForward()[0];
  
        if (auxRoot.key == key) {
  
           for (int i = 0; i < maxHeight; i++) {
              if (update[i].getForward()[i] != auxRoot) {
                 break;
              }
             update[i].getForward()[i] = auxRoot.getForward()[i];
          }
       }
    }
 

    public int height() { 
    	return height(root, maxHeight -1);
    }
    
	private int height(SkipListNode<T> node, int height) {
		
		if(node.getForward(height) != NIL)
			return height;
		else
			return height(node, height-1);
		
	}

	@Override
    public SkipListNode<T> search(int key) {
       
    	return search(key, this.root, height()-1);
    	
    }
 
    private SkipListNode<T> search(int key, SkipListNode<T> node, int level) {
		
    	if(level == 0 && (node.getForward(level) == null || node.getForward(level).getKey() > key)){
    		return null; 
    	}
    	if(node.getForward(level).getKey() == key)
    		return node.getForward(level);
    	
    	if(node.getForward(level).getKey() > key)
    		return search(key, node, level-1);
    	else
    		return search(key, node.getForward(level), level);

	}
    
 
    @Override
	public int size() {
		return size(this.root.getForward(0));
	}

    private int size(SkipListNode<T> node) {
		if(node == NIL)
			return 0;
		else
			return 1 + size(node.getForward(0));
	}

	@SuppressWarnings("unchecked")
	@Override
 	public SkipListNode<T>[] toArray() {
		ArrayList<SkipListNode<T>> out = new ArrayList<>(); 
		toArray(this.root, out);
		return out.toArray(new SkipListNode[out.size()]);
	}

	private void toArray(SkipListNode<T> node, ArrayList<SkipListNode<T>> out) {
		if(node == NIL){
			out.add(node);
		}else{
			out.add(node);
			toArray(node.getForward(0), out);
		}
	}
	
	
    public boolean bestRelationship(){
   	 return bestRelationship(this.height());
    }

    
    private boolean bestRelationship(int height) {
   	 if(height == 1) return true;
		
		int sizeAtual = this.getNodesAtHeight(height).length;
		int sizeAnterior = this.getNodesAtHeight(height - 1).length;
		
		if(sizeAtual != (sizeAnterior / 2)) return false;
		
		return bestRelationship(height - 1);
	}
    
	 private SkipListNode<T>[] getNodesAtHeight(int height){
	    	
    	ArrayList<SkipListNode<T>> out = new ArrayList<>();
    	
    	return getNodeAtHeight(this.root.getForward(height),out, height);
    	
    }

	private SkipListNode<T>[] getNodeAtHeight(SkipListNode<T> node, ArrayList<SkipListNode<T>> out,int height) {
	
		if(node.getForward(height) == NIL){
			SkipListNode<T> [] sair = new SkipListNode[out.size()];
			return out.toArray(sair);
		}
		else{
			out.add(node);
			return getNodeAtHeight(node.getForward(height), out, height);
		}
	}
 }
