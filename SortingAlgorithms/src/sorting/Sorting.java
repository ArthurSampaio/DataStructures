package sorting;

public abstract class Sorting {
	
	public Sorting(){
		
	}
	
	public abstract int[] sort(int[] v);

	public boolean checksOrderedArray(int[] v){
		
		for(int i = 0; i < v.length-1; i++){
		
			if(!(v[i] <= v[i+1])){
				return false;
			}
		}
		return true;
		
	}
}
