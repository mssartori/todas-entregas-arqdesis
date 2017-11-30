package arqdesis17_aula02cco;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MinhaClasse1Test {
	MinhaClasse1 mc;
	
	@Before
	public void setUp() throws Exception {
		mc = new MinhaClasse1();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSoma() {
		assertEquals("2+2=4", 4, mc.soma(2, 2));
		assertEquals("1+1=2", 2, mc.soma(1, 1));
		assertEquals("1-1=0", 0, mc.soma(1, -1));
		assertEquals("-2-2=-4", -4, mc.soma(-2, -2));
	}
	
	@Test
	public void testDivisaoDouble() {
		assertEquals("5/10=0.5", 0.5, mc.divisao(5.0, 10.0),0.1);
		assertEquals("1.0/3.0=0.33", 0.33, mc.divisao(1.0, 3.0),0.01);
	}
	
	@Test
	public void testDivisaoDoublePorZero() {
		assertEquals("1.0/0.0=Infinity", Double.POSITIVE_INFINITY, mc.divisao(1.0, 0.0), 0.0);
	}
	
	@Test(expected=ArithmeticException.class)
	public void testDivisaoInteiroPorZero()	{
		assertEquals("1/0=Infinity", 0, mc.divisao(1, 0));
	}

}
