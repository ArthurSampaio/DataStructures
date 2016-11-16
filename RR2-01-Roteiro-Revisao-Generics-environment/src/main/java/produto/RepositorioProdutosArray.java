package produto;

public class RepositorioProdutosArray <T extends Produto> implements RepositorioProdutos<T> {

	
	private int index;
	
	private T[] produtos;
	
	
	public RepositorioProdutosArray(int tamanho){
		super();
		if(tamanho > -1){
			this.index = -1; 
			this.produtos = (T[]) new Object[tamanho];
		}else{
			throw new RuntimeException("O tamanho do array nao pode ser negativo.");
		}
		
		
		
	}
	
	@Override
	public boolean existe(int codigo) {
		
		for(int i = 0; i < index; i++){
			if(produtos[i].getCodigo()==codigo){
				return true;
			}
		}return false;
	}

	@Override
	public void inserir(T produto) {
		this.index += 1; 
		this.produtos[index]=produto;
		
	}

	private int findInd(int codigo){
		for(int i = 0; i<index; i++){
			if(produtos[i].getCodigo()==codigo){
				return i;
			}
		}return -1;
	}
	
	@Override
	public void atualizar(T produto) {
		int ind = this.findInd(produto.getCodigo());
		if(ind != -1){
			this.produtos[ind] = produto;
		}
		
	}

	@Override
	public void remover(int codigo) {
		
		int ind = this.findInd(codigo);
		if(ind != -1){
			produtos[ind]=produtos[index];
			produtos[index]=null;
			index --;
		}
						
	}
	
	

	@Override
	public T procurar(int codigo) {
		int ind = this.findInd(codigo);
		if(ind != -1){
			return produtos[ind];
		}else{
			return null;
		}
	}

}
