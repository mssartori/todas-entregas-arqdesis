package figuras;

public abstract class Figura
{
	private double x0;
	private double y0;
	
	public Figura(){
		x0 = 0; 
		y0 = 0; 
	}
	
	public Figura(double a, double b){ 
		x0 = a;
		y0 = b;
	}
	
	public double getX0(){ return x0; }
	
	public void setX0(double x0){ this.x0 = x0; }
	
	public double getY0(){ return y0; }
	
	public void setY0(double y0){ this.y0 = x0; }
	
	public String toString(){ 
		return "Figura [x0=" + getX0() + ", y0=" + getY0() + ", area()=" + area() + ", perimetro()=" + perimetro() + ", volume()=" + volume() + "]"; 
	}
	
	public abstract double area();
	public abstract double perimetro();
	public abstract double volume();
}
