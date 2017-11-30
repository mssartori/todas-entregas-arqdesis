package figuras;

public class Cubo extends Poligono{

	protected double largura;
	
	public Cubo(double base, double altura, double largura) {
		super(0.0, 0.0, base, altura);
		this.largura = largura;
	}
	
	public double area(){ 
		return 6 * Math.pow(base, 2);	
	}
	
	public double perimetro() { 
		return 0 ; 
	}
	
	public double volume() { return base * altura * largura; }

	public String toString(){ 
		return "Cubo [area()=" + area() + "[volume()=" + volume() + ", toString()=" + super.toString() + "]";
	}

}
