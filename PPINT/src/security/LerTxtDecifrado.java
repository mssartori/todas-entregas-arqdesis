package security;

import java.io.*;
import java.util.*;
import java.sql.*;

public class LerTxtDecifrado
{
	public static Scanner input;
	@SuppressWarnings("unused")
	private static int primeiro = 0, meio = 0, ultimo = 0;
	boolean encontrado; 
	private static String usuario, usuariotxt, senhatxt, tipotxt, us; 
	private static String user[] = new String [ContFunc()]; 
	private static String pass[] = new String [ContFunc()]; 
	private static String tipo[] = new String [ContFunc()];
	private static String uni[] = new String [ContFunc()]; 
	private static int i=0;
	public Scanner getArquivo()
	{
		return input;
	}

	public static void abrirArquivo()
	{
		try
		{
			input = new Scanner(new File("Login_Decifrado.txt"));
		}
		catch(FileNotFoundException excecao)
		{ 
			excecao.getLocalizedMessage();
		}
	}

	public static String LerDados(model.Usuario usuario) 
	{
		String Texto = "";
		String TextoTxt = "";
		String resul = "";

		abrirArquivo();

		while (input.hasNext()) 
		{
			Texto = usuario.getUsuario();
			TextoTxt = input.next();

			resul += Texto + " " + TextoTxt;

			if (Texto.equals(TextoTxt)) 
			{
				return resul;
			}
		}
		return resul;
	}


	public static boolean LerLoginGUI(model.Usuario Usuario)
	{ 
		int j=0;

		abrirArquivo();

		while(input.hasNext())
		{
			us = Usuario.getUsuario();
			usuario = Usuario.getUsuario() + " " + Usuario.getSenha() + " " + Usuario.getTipo();
			usuariotxt = input.next();
			senhatxt = input.next();
			tipotxt = input.next();
			user[j] = usuariotxt;
			pass[j] = senhatxt;
			tipo[j] = tipotxt;

			uni[j] = usuariotxt + " " + senhatxt + " " + tipotxt;           

			j++;
		}
		for(int a = 0; a < j; a++)
		{
			if(usuario.equals(uni[a]))
			{
				return binaria(user, us);
			}
		}
		return false;
	} 

	public static boolean binaria(String vet[], String elem) {
		int ini = 0, meio, fim = vet.length ;
		Arrays.sort(vet);
		do {
			meio = (ini + fim) / 2;
			if      (vet[meio].compareToIgnoreCase(elem) > 0) fim = meio - 1;
			else if (vet[meio].compareToIgnoreCase(elem) < 0) ini = meio + 1;
			else                       
				return true;
		} while (ini <= fim);
		return false;
	}

	public static int ContFunc(){

		Connection conn = null;  
		control.FuncionarioDAO funcDAO = new control.FuncionarioDAO(); 
		try
		{
			// obtem conexao com o banco
			control.AcessoBD bd = new control.AcessoBD();
			conn = bd.obtemConexao();

			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);       
			i= funcDAO.ContarFuncionario(conn);

			conn.commit();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (conn != null)
			{
				try
				{
					conn.rollback();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}   
		return i;
	}
}