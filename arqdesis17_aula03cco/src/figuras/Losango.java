package figuras;

public class Losango extends Poligono {

	public Losango(double base, double altura) {
		super(0.0, 0.0, base, altura);
	}

	public double area(){ return (altura*base)/2; }
	public double perimetro() { 
		double m1 = altura/2;
		double m2 = base/2;
		double vlr = (m1 * m1) + (m2 * m2);
		double result =  Math.sqrt(vlr);
		return 4 * result;
	}
	public double volume() { return 0.0; }
	public String toString(){ 
		return "Losango [area()=" + area() + "[perimetro()=" + perimetro() + ", toString()=" + super.toString() + "]";
	}
}
