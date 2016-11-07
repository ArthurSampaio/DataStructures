package poligonos;

public class Circulo implements Poligono{

	private double raio;
	
	public Circulo(double raio){
		if(raio <=0){
			throw new IllegalArgumentException();
		}
		this.raio = raio;
	}
	
	@Override
	public double area() {
		return Math.PI *(raio * raio);
	}

}
