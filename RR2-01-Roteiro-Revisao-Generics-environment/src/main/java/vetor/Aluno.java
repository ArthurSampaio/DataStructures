package vetor;

import java.util.Comparator;

public class Aluno implements Comparable<Aluno> {
	private String nome;
	private double media;

	public Aluno(String nome, double media) {
		super();
		this.nome = nome;
		this.media = media;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	@Override
	public int compareTo(Aluno o) {
		return (int) (this.media - o.media);
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Aluno) {
			result = (this.compareTo((Aluno) obj) == 0);
		}
		return result;
	}
	
	public String toString(){
		return this.getNome() + " " + this.getMedia();
	}

}



class ComparadorMinimo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return (int) (o2.getMedia() - o1.getMedia());
	}

}

class ComparadorMaximo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return (int) (o1.getMedia() - o2.getMedia());
	}

}
