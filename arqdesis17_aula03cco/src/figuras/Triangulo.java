package figuras;

public class Triangulo extends Poligono{

	protected double lado;
	
	public Triangulo(double base, double altura, double lado) {
		super(0.0, 0.0, base, altura);
		this.lado = lado;
	}
	
	public double area(){ return (altura*base)/2; }
	public double perimetro() { return base + altura + lado; }
	public double volume() { return 0.0; }
	public String toString(){ 
		return "Triangulo [area()=" + area() + "[perimetro()=" + perimetro() + ", toString()=" + super.toString() + "]";
	}
	
}
