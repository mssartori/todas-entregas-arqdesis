package figuras;

public class Trapezio extends Poligono{

	protected double base1;
	protected double altura1;
	
	public Trapezio(double base, double altura, double base1, double altura1) {
		super(0.0, 0.0, base, altura);
		this.base1 = base1;
		this.altura1 = altura1;
	}
	
	public double area(){ 
		double vlr = (base1 + base); 
		return vlr * altura / 2;	
	}
	public double perimetro() { 
		return base + base1 + altura + altura1 ; 
	}
	
	public double volume() { return 0; }

	public String toString(){ 
		return "Trapezio [area()=" + area() + "[perimetro()=" + perimetro() + ", toString()=" + super.toString() + "]";
	}

}
