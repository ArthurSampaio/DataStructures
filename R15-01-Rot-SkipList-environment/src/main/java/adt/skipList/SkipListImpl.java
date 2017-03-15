package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

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
			SkipListNode<T> node = this.getRoot();

			for(int i = height-1; i >= 0; i--) {
				while(node.getForward(i) != null && node.getForward(i).getKey() < key) {
					node = node.getForward(i);
				}
				update[i] = node; // 
			}

			node = node.getForward(0);

			if(node.getKey() == key) {
				node.setValue(newValue);
			} else {

				ajustRoot(height, update);

				node = new SkipListNode<T>(key,height,newValue);

				changeForwards(height, update, node);
			}
		}
	}

	@Override
	public void remove(int key) {

		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> node = this.getRoot();

		//pesquisa local, igual search
		for(int i = this.maxHeight-1; i >= 0; i--) {
			while(node.getForward(i) != null && node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
			update[i] = node; // atualiza onde passou
		}
		
		node = node.getForward(0);
		
		if(node.getKey() == key) {
			int i = 0;
			while(update[i].getForward()[i].equals(node)){
				update[i].getForward()[i] = node.getForward()[i];
				i++;
				if(i >=this.maxHeight) break;
			}
			while(this.maxHeight-1 > 0 && root.getForward()[this.maxHeight-1] == NIL) {
					root.getForward()[this.maxHeight-1] =  null; 
				
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
		SkipListNode<T> node = this.getRoot();
		for(int i = maxHeight-1; i >= 0; i--){
			while(node.getForward(i) != null && node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
		}
		SkipListNode<T> result = node.getForward(0);
		if(result.getKey() != key) {
			result = null;
		}
		return result;
	}
	
	@Override
	public int size() {
		int count = 0;
		SkipListNode<T> auxNode = getRoot().getForward(0);
		while(auxNode != NIL) {
			count++;
			auxNode = auxNode.getForward(0);
		}
		return count;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		
		int size = size() +2;
		SkipListNode<T>[] result = new SkipListNode[size];
		SkipListNode<T> node = getRoot();
		int index = 0;
		while(index < size) { 
			result[index] = node;
			index++;
			node = node.getForward(0);
		}
		return result;
	}

	private void changeForwards(int height, SkipListNode<T>[] update, SkipListNode<T> node) {
		for(int i = 0; i < height; i++) {
			if(update[i].getForward(i) == null) {
				node.getForward()[i] = NIL; 
			} else {
				node.forward[i] = update[i].forward[i];
				update[i].forward[i] = node;
			}
		}
	}
	
	private void ajustRoot(int height, SkipListNode<T>[] updateArray) {
		if(height > this.maxHeight) {
			for(int i = this.maxHeight; i < height; i++) {
				getRoot().getForward()[i] = NIL;
			}
			this.maxHeight = height;
		}
	}
	
	private SkipListNode<T> getRoot(){
		return this.root;
	}

}
