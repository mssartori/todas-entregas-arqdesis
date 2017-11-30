package arqdesis17_aula04cco;

public interface Diagonal {

	default double imposto(double base, double altura) {
		double diagonal = Math.sqrt((altura * 2) + (base * 2));
		return diagonal;
	}
}
