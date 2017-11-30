package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Empresa;
import service.EmpresaService;

@WebServlet("/ManterEmpresa.do")
public class ManterEmpresaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pConjunto = request.getParameter("conjunto");
		String pCnpj = request.getParameter("cnpj");
		String pRazaoSocial = request.getParameter("razaosocial");
		String pHorarioFuncionamento = request.getParameter("horariofuncionamentoa") + request.getParameter("horariofuncionamentof");
		String pAutorizacao = request.getParameter("autorizacao");
		String pTemperatura = request.getParameter("temperatura") + "ºC";
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
		EmpresaService cs = new EmpresaService();
		cs.criar(empresa);
		empresa = cs.carregar(empresa.getId());

		//enviar para o jsp
		request.setAttribute("empresa", empresa);
		RequestDispatcher view =
				request.getRequestDispatcher("Empresa.jsp");
		view.forward(request, response);
	}
}