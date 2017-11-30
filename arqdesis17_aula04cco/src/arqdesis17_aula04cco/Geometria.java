package arqdesis17_aula04cco;

import java.util.ArrayList;

public class Geometria {
	ArrayList<Figura> figuras = new ArrayList<Figura>();

	public Geometria() {
		// C�RCULO
		Circulo circulo = new Circulo();
		circulo.setRaio(3);
		circulo.setPerimetro(circulo.getRaio() * 3.1415 * 2);
		circulo.setArea(3.1415 * (circulo.getRaio() * circulo.getRaio()));
		figuras.add(circulo);
		System.out.println("C�RCULO\n\nRaio: " + circulo.getRaio() + "\n�rea: "
				+ circulo.getArea() + "\nPer�metro: " + circulo.getPerimetro() + "\n\n\n");
		
		// LOSANGO
		Losango losango = new Losango();
		losango.setAltura(10);
		losango.setBase(5);
		losango.setArea((losango.getAltura() + losango.getBase()) / 2);
		losango.setPerimetro(losango.getAltura() + losango.getBase());
		System.out.println("LOSANGO\n\nAltura: " + losango.getAltura() + "\nBase:"
				+ losango.getBase() + "\n�rea: " + losango.getArea() + "\nPer�metro:"
				+ losango.getPerimetro());
		
		// QUADRADO
		Quadrado quadrado = new Quadrado();
		quadrado.setAltura(10);
		quadrado.setBase(5);
		quadrado.setArea((quadrado.getAltura() + quadrado.getBase()) / 2);
		quadrado.setPerimetro(quadrado.getAltura() + quadrado.getBase());
		System.out.println("LOSANGO\n\nAltura: " + quadrado.getAltura() + "\nBase:"
				+ quadrado.getBase() + "\n�rea: " + quadrado.getArea() + "\nPer�metro:"
				+ quadrado.getPerimetro());
		
		// RETANGULO
		Retangulo retangulo = new Retangulo();
		retangulo.setAltura(10);
		retangulo.setBase(5);
		retangulo.setArea((retangulo.getAltura() + retangulo.getBase()) / 2);
		retangulo.setPerimetro(retangulo.getAltura() + retangulo.getBase());
		System.out.println("LOSANGO\n\nAltura: " + retangulo.getAltura() + "\nBase:"
				+ retangulo.getBase() + "\n�rea: " + retangulo.getArea() + "\nPer�metro:"
				+ retangulo.getPerimetro());
		
		// TRIANGULO
		Triangulo triangulo = new Triangulo();
		triangulo.setAltura(10);
		triangulo.setBase(5);
		triangulo.setArea((triangulo.getAltura() + triangulo.getBase()) / 2);
		triangulo.setPerimetro(triangulo.getAltura() + triangulo.getBase());
		System.out.println("LOSANGO\n\nAltura: " + triangulo.getAltura() + "\nBase:"
				+ triangulo.getBase() + "\n�rea: " + triangulo.getArea() + "\nPer�metro:"
				+ triangulo.getPerimetro());
	}

}
