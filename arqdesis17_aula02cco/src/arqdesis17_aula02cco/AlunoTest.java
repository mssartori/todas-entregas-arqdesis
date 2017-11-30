package arqdesis17_aula02cco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlunoTest {
	Aluno a1, a2;

	@Before
	public void setUp() throws Exception {
		a1 = new Aluno(123, "Maria");
		a2 = new Aluno(123, "Maria");
	}

	@Test
	public void test() {
		assertEquals("123 Maria", a1, a2);
	}
}