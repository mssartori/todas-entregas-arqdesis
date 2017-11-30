package arqdesis17_aula02cco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinhaClasse2Test {

	@Test
	public void testInverter() {
		MinhaClasse2 mc = new MinhaClasse2();
		int[] esperado = {3, 2, 1};
		int[] entrada = {1, 2, 3};
		assertEquals("123-321", esperado, mc.inverte(entrada));
		
		int[] esperado1 = {4, 3, 2, 1};
		int[] entrada1 = {1, 2, 3, 4};
		assertEquals("1234-4321", esperado1, mc.inverte(entrada1));
	}

}
