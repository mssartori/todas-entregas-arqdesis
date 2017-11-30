package figuras;

public class Piramide extends Poligono{

	protected double largura;
	
	public Piramide(double base, double altura, double largura) {
		super(0.0, 0.0, base, altura);
		this.largura = largura;
	}
	
	public double area(){ 
		return base + altura;	
	}
	
	public double perimetro() { 
		return 0 ; 
	}
	
	public double volume() { return (1 * base * altura * largura)/3; }

	public String toString(){ 
		return "Piramide [area()=" + area() + "[volume()=" + volume() + ", toString()=" + super.toString() + "]";
	}

}
