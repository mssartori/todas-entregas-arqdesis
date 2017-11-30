package figuras;

public class Cilindro extends Figura{
	
	private double pi;
	private double raio;
	private double altura;
	
	public Cilindro(double raio, double altura) {
		super(0.0, 0.0);
		pi = 3.14;
		this.raio = raio;
		this.altura = altura;
	}
	
	@Override
	public double area(){ 
		double vlr = 2 * pi * raio;
		return vlr * (altura + raio); 
	}
	
	@Override
	public double perimetro(){ return (2 * pi) * raio; }
	
	public double volume(){
		return pi * (raio * raio * raio) * altura;
	}
	
	public String toString(){ 
		return "Cilindro [area()=" + area() + "[volume()=" + volume() + ", toString()=" + super.toString() + "]";
	}
}
