package figuras;

public class Esfera extends Figura{
	
	private double pi;
	private double raio;
	private double altura;
	
	public Esfera(double raio, double altura) {
		super(0.0, 0.0);
		pi = 3.14;
		this.raio = raio;
		this.setAltura(altura);
	}
	
	@Override
	public double area(){ 
		return 4 * pi * (raio * raio);
	}
	public double perimetro() { return 0.0; }
	
	public double volume(){
		return (4 * pi * Math.pow(raio, 3))/3;
	}
	
	public String toString(){ 
		return "Esfera [area()=" + area() + "[volume()=" + volume() + ", toString()=" + super.toString() + "]";
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
}
