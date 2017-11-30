package security;

import java.io.File;

public class ApagarTxt
{
	public static void main(String [] args)
	{
		File f = new File("Login_Decifrado.txt");
		f.delete();
	}
}