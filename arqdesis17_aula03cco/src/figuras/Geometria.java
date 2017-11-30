package figuras;
import java.util.ArrayList;

public class Geometria {

	public static void main(String[] args) {
		ArrayList<Figura> figura = new ArrayList<>();
		figura.add(new Circulo(5));
		figura.add(new Esfera(10, 8));
		figura.add(new Cilindro(4,8));
		ArrayList<Poligono> poligono = new ArrayList<>();
		poligono.add(new Triangulo(10, 5, 7 ));
		poligono.add(new Losango(6,8));
		poligono.add(new Quadrado(5,10));
		poligono.add(new Retangulo(15,5));
		poligono.add(new Trapezio(10,5,5,10));
		poligono.add(new Cubo(6,6,3));
		poligono.add(new Piramide(5,8,4));


		for(Poligono pol:poligono)
		{
			System.out.println(pol);
			if(pol instanceof Diagonal)
			{
			Diagonal diagonal = (Diagonal)pol;
			System.out.println(diagonal.diagonal());
			}
		}
		
		for(Figura fig:figura)
		{
			System.out.println(fig);
			if(fig instanceof Diagonal)
			{
			Diagonal diagonal = (Diagonal)fig;
			System.out.println(diagonal.diagonal());
			}
		}
	}
}
