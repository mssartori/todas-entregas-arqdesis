package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Empresa;
import service.EmpresaService;

/**
 * Servlet implementation class ManterClienteController
 */
@WebServlet("/ManterEmpresa.do")
public class ManterEmpresaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pConjunto = request.getParameter("conjunto");
		String pCnpj = request.getParameter("cnpj");
		String pRazaoSocial = request.getParameter("RazaoSocial");
		String pHorarioFuncionamento = request.getParameter("horarioFuncionamento");
		String pAutorizacao = request.getParameter("autorizacao");
		String pTemperatura = request.getParameter("temperatura");
		String pHorarioArCondicionado = request.getParameter("horarioarcondicionado");
		String pCancelado = request.getParameter("cancelado");
		
		//instanciar o javabean
		Empresa empresa = new Empresa();
		empresa.setNome(pNome);
		empresa.setConjunto(pConjunto);
		empresa.setCnpj(pCnpj);
		empresa.setRazaoSocial(pRazaoSocial);
		empresa.setHorarioFuncionamento(pHorarioFuncionamento);
		empresa.setAutorizacao(pAutorizacao);
		empresa.setTemperatura(pTemperatura);
		empresa.setHorarioArCondicionado(pHorarioArCondicionado);
		empresa.setCancelado(pCancelado);
		
		//instanciar o service
		EmpresaService es = new EmpresaService();
		es.criar(empresa);
		empresa = es.carregar(empresa.getId());
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Empresa Cadastrada</title></head><body>");
		out.println(	"ID: "+empresa.getId()+"<br>");
		out.println(	"Nome: "+empresa.getNome()+"<br>");
		out.println(	"Conjunto: "+empresa.getConjunto()+"<br>");
		out.println(	"CNPJ: "+empresa.getCnpj()+"<br>");
		out.println(	"Razão Social: "+empresa.getRazaoSocial()+"<br>");
		out.println(	"Horário Funcionamento: "+empresa.getHorarioFuncionamento()+"<br>");
		out.println(	"Autorização: "+empresa.getAutorizacao()+"<br>");
		out.println(	"Temperatura: "+empresa.getTemperatura()+"<br>");
		out.println(	"Horário Ar Condicionado: "+empresa.getHorarioArCondicionado()+"<br>");
		out.println(	"Cancelado: "+empresa.getCancelado()+"<br>");
		out.println("</body></html>");
		
	}

}
