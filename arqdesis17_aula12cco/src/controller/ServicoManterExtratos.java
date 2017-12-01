package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Extrato;
import util.JSonFacade;
@WebServlet("/cliente")
public class ServicoManterExtratos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		String chave = request.getParameter("chave");
		String periodo = request.getParameter("periodo");
		Extrato extrato = new Extrato();
		ArrayList<Extrato> lista = null;
		PrintWriter out = response.getWriter();
		try {
			if (chave != null && chave.length() > 0) {
				lista = extrato.getExtratos(chave, periodo);
			} else {
				lista = extrato.getExtratos();
			}
			out.println(JSonFacade.listToJSon(lista));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
	/*
	 * inclusão de clientes
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();
		try {
			Extrato extrato = JSonFacade.jSonToCliente(sb.toString());
			int id = (int) extrato.getExtratos();
			extrato.setId(id);
			//retorna o cliente cadastrado com o id atribuido pelo banco
			out.println(JSonFacade.clienteToJSon(extrato));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
	/*
	 * atualiza clientes
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();
		try {
			Extrato extrato = JSonFacade.jSonToCliente(sb.toString());
			out.println(JSonFacade.clienteToJSon(extrato));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
}