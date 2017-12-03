package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.*;

import control.EmpresaDAO;

public class EmpresaDAOTest {
	model.Empresa e1, e2, e3;
	EmpresaDAO ed = new EmpresaDAO();
	
	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("unused")
		model.Empresa e1 = new model.Empresa(1, "LTDA-Encomendas", "5", "456.654.658-85", "Encomenda's LTDA", "08:00 - 17:00", "AUTORIZADO", "22ºC", "14:00");
		model.Empresa e2 = new model.Empresa();
		e2.setidEmpresa(1);
		e2.setNome("LTDA-Encomendas");
		e2.setConjunto("5");
		e2.setCnpj("456.654.658-85");
		e2.setRazaoSocial("Encomenda's LTDA");
		e2.setHorarioFuncionamento("08:00 - 17:00");
		e2.setAutorizacao("AUTORIZADO");
		e2.setTemperatura("22ºC");
		e2.setHorarioArCondicionado("14:00");
	}

	@Test
	public void test() {
		assertEquals("Empresa no banco com empresa criada", e1, e2);
	}
	
	@Test
	public void testInserir() throws SQLException {
		control.AcessoBD bd = new control.AcessoBD();
		Connection conn = bd.obtemConexao();
		conn.setAutoCommit(false); 
		ed.incluir(conn, e2);
		assertEquals("Teste insert comparando com o instanciado", e1, e2);
	}
	
	@Test
	public void testAlterar() throws SQLException {
		control.AcessoBD bd = new control.AcessoBD();
		e1.setidEmpresa(2);
		e1.setNome("MX  CARROS");
		e1.setConjunto("4");
		e1.setCnpj("785.123.454-75");
		e1.setRazaoSocial("MX CARS");
		e1.setHorarioFuncionamento("08:00 - 18:00");
		e1.setAutorizacao("NÃO AUTORIZADO");
		e1.setTemperatura("22ºC");
		e1.setHorarioArCondicionado("15:00");
		e2.setidEmpresa(2);
		e2.setNome("MX  CARROS");
		e2.setConjunto("4");
		e2.setCnpj("785.123.454-75");
		e2.setRazaoSocial("MX CARS");
		e2.setHorarioFuncionamento("08:00 - 18:00");
		e2.setAutorizacao("NÃO AUTORIZADO");
		e2.setTemperatura("22ºC");
		e2.setHorarioArCondicionado("15:00");
		Connection conn = bd.obtemConexao();
		conn.setAutoCommit(false); 
		ed.incluir(conn, e2);
		assertEquals("Teste alterar comparando com o instanciado", e1, e2);
	}
	
	@Test
	public void testExcluir() throws SQLException {
		control.AcessoBD bd = new control.AcessoBD();
		Connection conn = bd.obtemConexao();
		conn.setAutoCommit(false); 
		ed.excluir(conn, e2);
		ed.excluir(conn, e1);
		assertEquals("Teste deletar do bd (result == null)", e1, e2);
	}

}
