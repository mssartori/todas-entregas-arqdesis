package arqdesis17_aula02cco;

public class MinhaClasse2 {
	public int[] inverte(int[] v) {
		int[]aux = new int[v.length];
		for(int i = 0; i < v.length; i++)
		{
			aux[v.length -i -1] = v[i];
		}
		return v;
	}
}
