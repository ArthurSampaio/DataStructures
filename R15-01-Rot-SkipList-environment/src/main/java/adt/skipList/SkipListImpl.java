package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;
	protected boolean USE_MAX_HEIGHT_AS_HEIGHT;

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
		if(newValue != null) {
			SkipListNode<T>[] update = new SkipListNode[height];
			SkipListNode<T> auxNode = root;

			for(int i = height-1; i >= 0; i--) {
				while(auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
					auxNode = auxNode.getForward(i);
				}
				update[i] = auxNode; // 
			}

			auxNode = auxNode.getForward(0);

			if(auxNode.getKey() == key) {
				auxNode.setValue(newValue);
			} else {

				ajustRoot(height, update);

				auxNode = new SkipListNode<T>(key,height,newValue);

				changeForwards(height, update, auxNode);
			}
		}
	}

	@Override
	public void remove(int key) {

		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> auxNode = root;

		//pesquisa local, igual search
		for(int i = this.maxHeight-1; i >= 0; i--) {
			while(auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
			update[i] = auxNode; // atualiza onde passou
		}
		
		auxNode = auxNode.getForward(0);
		
		if(auxNode.getKey() == key) {
			int i = 0;
			while(update[i].getForward()[i].equals(auxNode)){
				update[i].getForward()[i] = auxNode.getForward()[i];
				i++;
				if(i >=this.maxHeight) break;
			}
			while(this.maxHeight-1 > 0 && root.getForward()[this.maxHeight-1] == NIL) {
				if(!USE_MAX_HEIGHT_AS_HEIGHT){
					root.getForward()[this.maxHeight-1] =  null; 
				}
				this.maxHeight--;
			}
		}
	}

	@Override
	public int height() {
		int height = this.maxHeight;
		while(height >= 0 && root.getForward(height) == NIL) {
			height--;
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> auxNode = this.root;
		for(int i = maxHeight-1; i >= 0; i--){
			while(auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
		}
		SkipListNode<T> result = auxNode.getForward(0);
		if(result.getKey() != key) {
			result = null;
		}
		return result;
	}
	
	@Override
	public int size() {
		int count = 0;
		SkipListNode<T> auxNode = this.root.getForward(0);
		while(auxNode != NIL) {
			count++;
			auxNode = auxNode.getForward(0);
		}
		return count;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		if(USE_MAX_HEIGHT_AS_HEIGHT) {
			connectRootToNil();
		}
		int size = size() +2;
		SkipListNode<T>[] result = new SkipListNode[size];
		SkipListNode<T> auxNode = this.root;
		int index = 0;
		while(index < size) { 
			result[index] = auxNode;
			index++;
			auxNode = auxNode.getForward(0);
		}
		return result;
	}

	private void changeForwards(int height, SkipListNode<T>[] update, SkipListNode<T> newNode) {
		for(int i = 0; i < height; i++) {
			if(update[i].getForward(i) == null) {
				newNode.getForward()[i] = NIL; // para nao apontar para null, que é o forward de nil e tambem nao apontar o forward de nill pra node
			} else {
				newNode.forward[i] = update[i].forward[i];
				update[i].forward[i] = newNode;
			}
		}
	}
	
	private void ajustRoot(int height, SkipListNode<T>[] updateArray) {
		if(height > this.maxHeight) {
			for(int i = this.maxHeight; i < height; i++) {
				root.getForward()[i] = NIL;
			}
			this.maxHeight = height;
		}
	}

}
