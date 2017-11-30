package security;//Criando Arquivo

import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.*;
import javax.swing.*;

public class CriarTxtDecifrado
{
	private Formatter output;

	public void openFile()
	{
		try
		{
			output = new Formatter( "Login_Decifrado.txt" );// NOme do arquivo que sera gerado
		} // end try
		catch ( SecurityException securityException )
		{
			System.err.println(
					"You do not have write access to this file." );
			System.exit( 1 );
		} // end catch
		catch ( FileNotFoundException filesNotFoundException )
		{
			System.err.println( "Error creating file." );
			System.exit( 1 );
		} // end catch

	} // end method openFile

	public void addFrase(String a)
	{
		@SuppressWarnings("unused")
		boolean Verifica = false;
		openFile();
		@SuppressWarnings("unused")
		int n = 0;
		String retval[] =  a.split(" ");
		try 
		{
			for(int i = 1; i < retval.length; i++)
			{           
				if(i%3 == 1)
					output.format(retval[i]+ " " + retval[i+1]+ " " + retval[i+2] + "\r\n");
			}
			closeFile();
			Verifica = true; 
		}
		catch(FormatterClosedException formatterClosedException)
		{
			JOptionPane.showMessageDialog(null,"Erro ao preencher o arquivo");
		}
		catch(NoSuchElementException elementException)
		{
			JOptionPane.showMessageDialog(null,"Campo inválido, preencher novamente!");
		}   
	}

	public void closeFile()
	{
		if ( output != null )
			output.close();//fecha o arquivo txt
	} // end method closeFile

}