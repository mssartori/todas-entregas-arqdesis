package figuras;

public class Retangulo extends Poligono implements Diagonal{
	
	public Retangulo(double base, double altura){
		super(0.0, 0.0, base, altura);
	}
	
	@Override
	public double area(){ return altura*base; }
	public double perimetro() { return (base + base) + (altura + altura); }
	public double volume() { return 0.0; }
	@Override
	public double diagonal(){
		double vlr = (altura * altura) + (base * base);
		return Math.sqrt(vlr);
	}
	
	public String toString(){ 
		return "Retangulo [area()=" + area() + "[perimetro()=" + perimetro() + ", toString()=" + super.toString() + "]";
	}
	
}
