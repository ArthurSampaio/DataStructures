package poligonos;

public class Retangulo implements Poligono {

	private double largura; 
	private double altura;
	
	public Retangulo(double lar, double alt) {
		// TODO Auto-generated constructor stub
		if(lar <= 0 && alt<=0){
			throw new IllegalArgumentException();
		}
		this.largura = lar;
		this.altura = alt;
	}
	
	
	@Override
	public double area() {
		return this.largura * this.altura;
	}

}
