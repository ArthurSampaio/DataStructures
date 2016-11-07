package poligonos;

public class Triangulo implements Poligono {

	private double largura; 
	private double altura;
	
	public Triangulo(double lar, double alt) {
		// TODO Auto-generated constructor stub
		if(lar <= 0 && alt<=0){
			throw new IllegalArgumentException();
		}
		this.largura = lar;
		this.altura = alt;
	}
	
	@Override
	public double area() {
		return (largura*altura)/2;
	}
	

}
