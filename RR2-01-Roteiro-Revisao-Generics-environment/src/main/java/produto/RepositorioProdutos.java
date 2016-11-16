package produto;

public interface RepositorioProdutos<E> {

	public abstract boolean existe(int codigo);

	public abstract void inserir(E produto);

	public abstract void atualizar(E produto);

	public abstract void remover(int codigo);

	public abstract E procurar(int codigo);
}
