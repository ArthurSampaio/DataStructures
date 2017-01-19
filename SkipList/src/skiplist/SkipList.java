package skiplist;

public class SkipList <T extends Comparable> implements SkipListInterface<T>{
	
	private final static int INFINTO = Integer.MAX_VALUE;
	private final static int MENOS_INFINITO = Integer.MIN_VALUE;
	
	private SkipNode root; 
	private SkipNode NIL; 
	private int level; 
	private int maxLevel; 
	private boolean maxAsLevel; 
	private double probabilty; 
	
	 
	public SkipList (int maxLevel, boolean maxAsLevel){
		
		if(maxAsLevel)
			this.level = maxLevel; 
		else
			this.level = 1; 
		
		root = new SkipNode(MENOS_INFINITO, this.level, MENOS_INFINITO);
		NIL = new SkipNode(INFINTO, this.level, INFINTO);
		connectRootAndNIL();
		
	}

	private void connectRootAndNIL() {
		for(int i = 0; i < level; i++)
			root.setForward(i, NIL);
	}

	@Override
	public void insert(int key, T newValue) {
		
		SkipNode [] update = new SkipNode[this.maxLevel];
		SkipNode aux = this.root; 
		
		//search
		for(int i = this.level-1; i > 0; i--){
			while(aux.getNode(i).getKey() < key)
				aux = aux.getNode(i);
			//save the next nodes. 
			update[i] = aux; 
		}
		
		aux = aux.getNode(0);
		
		if(aux.getKey() == key)
			aux.setData(newValue);
		else{
			int newLevel = this.generateRandomLevel();
			
			if(newLevel > this.level){
				for(int i = this.level; i > newLevel; i--)
					update[i] = this.root;
				this.level = newLevel; 
			}
			
			aux = makeNode(key, newValue, newLevel);
			
			//change the pointers
			for(int i = 0; i < newLevel; i++){
				aux.setForward(i, update[i].getNode(i));
				update[i].setForward(i, aux);
			}
			
		}
		
		
	}

	private SkipNode makeNode(int key, T newValue, int newLevel) {
		return new SkipNode(key,newLevel, newValue);
	}

	@Override
	public void insert(int key, T newValue, int newHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T search(int key) {

		SkipNode<T> aux = this.root; 
		for(int i = this.level-1; i > 0; i--){
			while(aux.getNode(i).getKey() < key){
				aux = aux.getNode(i);
			}
		}
		aux = aux.getNode(0);
		
		if(aux.getKey() == key)
			return aux.getData(); 
		else		
			return null;
	}
	
	private int generateRandomLevel(){
		
		int v = 1; 
		while(Math.random() < this.probabilty && v < this.maxLevel)
			v++; 
		return v; 
		
	}
	

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SkipNode<T>[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
