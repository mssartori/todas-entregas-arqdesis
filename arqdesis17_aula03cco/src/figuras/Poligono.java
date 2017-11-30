package figuras;

public abstract class Poligono extends Figura{
	
	protected double base;
	protected double altura;
	
	public Poligono() {
		super();
		setBase(0.0);
		setAltura(0.0);
	}
	
	public Poligono(double x0, double y0, double base, double altura){
		super(x0,y0);
		this.setBase(base);
		this.setAltura(altura);
	}
	
	public abstract double area();
	public abstract double perimetro();

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
}
