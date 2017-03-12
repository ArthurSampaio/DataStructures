package function;

public class Function {
	
	private int n; 
	private int m; 
	private static final int i = 3;
	private static final int j = 5; 
	
	public Function(int a, int b){
		if(a >=0 && b >= 0){
			this.n = a; 
			this.m = b;
		}else{
			throw new IllegalArgumentException("someone or both of the terms are less than zero.");
			
		}
	}
	
	public long somatory(){
		long sum = 0;
		for(int z = 1; z<=n; z++){
			sum += Math.sqrt(i);
		}
		return sum;
	}

	public long produtory(){
		long prod = 0; 
		for(int i = 1; i <= m/2; i++){
			prod *= j;
		}
		return prod;
	}
	
	public String toString(){
		long value = this.somatory() + this.produtory(); 
		return String.format("%d", value);
	}
}
