package security;

import java.io.*;
import java.util.*;

public class LerTxt
{
	public static Scanner input;
	@SuppressWarnings("unused")
	private static int primeiro = 0, meio = 0, ultimo = 0;
	boolean encontrado; 
	@SuppressWarnings("unused")
	private static String usuario, usuariotxt, senhatxt, tipotxt, us; 
	private static String user[] = new String [3]; 

	public Scanner getArquivo()
	{
		return input;
	}

	public static void abrirArquivo()
	{
		try
		{
			input = new Scanner(new File("LoginGUI.txt"));
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

			j++;
		}   

		return binaria(user, us);
	} 

	public static boolean binaria(String vet[], String elem) 
	{
		int ini = 0, meio, fim = vet.length ;

		Arrays.sort(vet);

		do 
		{
			meio = (ini + fim) / 2;
			if      (vet[meio].compareToIgnoreCase(elem) > 0) fim = meio - 1;
			else if (vet[meio].compareToIgnoreCase(elem) < 0) ini = meio + 1;
			else                       
				return true;
		}
		while (ini <= fim);

		return false;
	}
}