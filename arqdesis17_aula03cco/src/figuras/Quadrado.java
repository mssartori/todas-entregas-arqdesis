package figuras;

public class Quadrado extends Poligono implements Diagonal{
	
	public Quadrado(double base, double altura){
		super(0.0, 0.0, base, altura);
	}
	
	@Override
	public double area(){ return altura*base; }
	public double perimetro() { 
		double vlr = altura + altura + base + base; 
		return vlr;
	}
	public double volume() { return 0.0; }
	@Override
	public double diagonal(){
		double vlr = (altura * altura) + (base * base);
		return Math.sqrt(vlr);
	}
	
	public String toString(){ 
		return "Quadrado [area()=" + area() + "[perimetro()=" + perimetro() + ", toString()=" + super.toString() + "]";
	}
	
}
