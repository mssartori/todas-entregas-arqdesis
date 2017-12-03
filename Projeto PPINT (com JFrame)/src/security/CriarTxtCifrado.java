package security;

import java.io.*;
import java.util.*;

public class CriarTxtCifrado
{
	private static FileWriter arquivo;
	private static PrintWriter Gravar;

	public static FileWriter getArquivo() 
	{
		return arquivo;
	}

	public static void abrirArquivo() 
	{
		try 
		{
			arquivo = new FileWriter("Login_Cifrado.txt", true);
			Gravar = new PrintWriter(arquivo);
		} 
		catch (SecurityException se) 
		{
			se.printStackTrace();
		} 
		catch (FileNotFoundException excecao) 
		{
			excecao.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static boolean gravarDados(model.Usuario usuario) 
	{
		boolean Verifica = false;
		abrirArquivo();

		try 
		{
			Gravar.printf("%s%s%s\n", usuario.getUsuario(), usuario.getSenha(), usuario.getTipo());
			closeArquivo();
			Verifica = true;
		} 
		catch (FormatterClosedException excecao) 
		{
			excecao.printStackTrace();
			Verifica = false;
		}
		return Verifica;
	}

	public static void closeArquivo() 
	{
		if (getArquivo() != null) 
		{
			try 
			{
				getArquivo().close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}