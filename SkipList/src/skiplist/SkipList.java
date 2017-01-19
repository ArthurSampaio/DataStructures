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
	public void insert(int key, Comparable newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(int key, Comparable newValue, int newHeight) {
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
