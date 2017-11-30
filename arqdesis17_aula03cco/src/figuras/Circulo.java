package figuras;

public class Circulo extends Figura{
	
	private double pi;
	private double raio;
	
	
	public Circulo() {
		super();
		pi = 3.14;
		raio = 0.0;
	}
	
	public Circulo(double r) {
		super(0.0, 0.0);
		pi = 3.14;
		this.raio = r;
	}
	
	@Override
	public double area(){ return pi * (raio * raio); }
	
	@Override
	public double perimetro(){ return (2 * pi) * raio; }
	
	public double volume() { return 0.0; }
	
	public String toString(){ 
		return "Circulo [area()=" + area() + "[perimetro()=" + perimetro() + ", toString()=" + super.toString() + "]";
	}
}
