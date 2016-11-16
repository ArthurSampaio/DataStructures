package vetor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import solucao.vetor.Vetor;
import vetor.Aluno;

public class TestarVetor {

	public static void main(String[] args) {
		Vetor<Aluno> vetor = new Vetor<Aluno>(20);
		ComparadorMaximo compMax = new ComparadorMaximo();
		vetor.setComparadorMaximo(compMax);
		ComparadorMinimo compMin = new ComparadorMinimo();
		vetor.setComparadorMinimo(compMin);
		
		Aluno arthur = new Aluno("Arthur", 7.8);
		Aluno jose = new Aluno("Jose", 5.0);
		Aluno bianca = new Aluno("Bianca", 6.5);
		Aluno pedro = new Aluno("Pedro", 9.5);
		
		//verifica se o vetor está vazio
		assertTrue(vetor.isVazio());
		vetor.inserir(arthur);
		vetor.inserir(jose);
		vetor.inserir(bianca);
		assertNull(vetor.procurar(pedro));
		vetor.inserir(pedro);
		
		//procura
		assertEquals(pedro, vetor.procurar(pedro));
		//remove
		vetor.remover(jose);
		assertNull(vetor.remover(jose));
		
		assertEquals(pedro, vetor.maximo());
		assertEquals(bianca, vetor.minimo());
		
		//adicionando jose de novo
		vetor.inserir(jose);
		//esta cheio?
		assertFalse(vetor.isCheio());
		System.out.println("Passou");

		
	}
}


