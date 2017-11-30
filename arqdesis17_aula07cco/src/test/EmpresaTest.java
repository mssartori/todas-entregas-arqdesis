package test;


import static org.junit.Assert.assertEquals;
import model.Empresa;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import service.EmpresaService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {
	Empresa empresa, copia;
	EmpresaService empresaService;
	static int id = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		empresa = new Empresa();
		empresa.setId(id);
		empresa.setNome("Batista Cepelos");
		empresa.setConjunto("4");
		empresa.setCnpj("123.456.789.-10");
		empresa.setRazaoSocial("Batista Cepelos");
		empresa.setHorarioFuncionamento("07:00 - 18:00");
		empresa.setAutorizacao("AUTORIZADO");
		empresa.setTemperatura("21�C");
		empresa.setHorarioArCondicionado("06:00");
		empresa.setCancelado("0");
		copia = new Empresa();
		copia.setId(id);
		copia.setNome("Batista Cepelos");
		copia.setConjunto("4");
		copia.setCnpj("123.456.789.-10");
		copia.setRazaoSocial("Batista Cepelos");
		copia.setHorarioFuncionamento("07:00 - 18:00");
		copia.setAutorizacao("AUTORIZADO");
		copia.setTemperatura("21�C");
		copia.setHorarioArCondicionado("06:00");
		copia.setCancelado("0");
		empresaService = new EmpresaService();
		System.out.println(empresa);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		Empresa fixture = new Empresa();
		fixture.setId(1);
		fixture.setNome("LTDA-Encomendas");
		fixture.setConjunto("5");
		fixture.setCnpj("456.654.658-85");
		fixture.setRazaoSocial("Encomenda's LTDA");
		fixture.setHorarioFuncionamento("08:00 - 17:00");
		fixture.setAutorizacao("AUTORIZADO");
		fixture.setTemperatura("22�C");
		fixture.setHorarioArCondicionado("14:00");
		fixture.setCancelado("0");
		EmpresaService novoService = new EmpresaService();
		Empresa novo = novoService.carregar(1);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		id = empresaService.criar(empresa);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", empresa, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		empresa.setCnpj("999999");
		copia.setCnpj("999999");		
		empresaService.atualizar(empresa);
		empresa = empresaService.carregar(empresa.getId());
		assertEquals("testa atualizacao", empresa, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setConjunto(null);
		copia.setCnpj(null);
		copia.setRazaoSocial(null);
		copia.setHorarioFuncionamento(null);
		copia.setAutorizacao(null);
		copia.setTemperatura(null);
		copia.setHorarioArCondicionado(null);
		copia.setCancelado(null);
		empresaService.excluir(id);
		empresa = empresaService.carregar(id);
		assertEquals("testa exclusao", empresa, copia);
	}
}