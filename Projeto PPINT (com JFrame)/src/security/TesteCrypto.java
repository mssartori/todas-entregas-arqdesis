package security;

import java.io.File;

public class TesteCrypto
{   
	public TesteCrypto() throws Exception
	{
		String resulCifrado = "";
		String resulDecifrado = "";
		@SuppressWarnings("unused")
		security.LerTxt ler = new security.LerTxt();
		model.Usuario u = new model.Usuario("", "", "");
		@SuppressWarnings("unused")
		security.CriarTxtCifrado txtCifrado = new CriarTxtCifrado();
		security.CriarTxtDecifrado txtDecifrado = new CriarTxtDecifrado();

		String senha = u.getSenha();
		String tipo = u.getTipo() + "\n";
		String sMsgClara = LerTxt.LerDados(u);
		String sMsgCifrada = null;
		String sMsgDecifrada = null;
		byte[] bMsgClara = null;
		byte[] bMsgCifrada = null;
		byte[] bMsgDecifrada = null;

		// Instancia objeto da classe Impressora
		security.Impressora prn = new security.Impressora();

		// Converte o u String dado no equivalente byte[]
		bMsgClara = sMsgClara.getBytes("ISO-8859-1");


		/*
		 * Criptografia AES --------------------------------------------------------------
		 */

		// Imprime Texto
		resulCifrado += ">>> Cifrando com o algoritmo AES... <<<";
		resulCifrado += "\n";

		// Instancia um objeto da classe CryptoAES
		security.CryptoAES caes = new security.CryptoAES();

		// Gera a Chave criptografica AES simetrica e o nome do arquivo onde será armazenada
		caes.geraChave(new File ("chave.simetrica"));

		// Gera a cifra AES da mensagem dada, com a chave simetrica dada
		caes.geraCifra(bMsgClara, new File ("chave.simetrica"));

		// Recebe o u cifrado
		bMsgCifrada = caes.getTextoCifrado();

		// Converte o u byte[] no equivalente String
		sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));

		// Imprime cabecalho da mensagem
		resulCifrado += "Mensagem Cifrada(Hexadecimal): ";

		// Imprime o u cifrado em Hexadecimal
		resulCifrado += prn.hexBytesToString(bMsgCifrada);
		resulCifrado += "\n";

		// Imprime cabecalho da mensagem
		resulCifrado += "Mensagem Cifrada(String): ";

		// Imprime o u cifrado em String
		resulCifrado += sMsgCifrada;
		resulCifrado += "\n\n";

		// Imprime u
		//resulDecifrado += ">>> Decifrando com o algoritmo AES... <<<";

		// Gera a decifra AES da mensagem dada, segundo a chave simetrica gerada
		caes.geraDecifra(bMsgCifrada, new File ("chave.simetrica"));

		// recebe o u decifrado
		bMsgDecifrada = caes.getTextoDecifrado();

		// Converte o u byte[] no equivalente String
		sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));

		// Imprime o u decifrado em String
		resulDecifrado += sMsgDecifrada;

		model.Usuario usu = new model.Usuario(resulCifrado, senha, tipo);
		//Usuario usuario = new Usuario(resulDecifrado, "\n");

		security.CriarTxtCifrado.gravarDados(usu);
		txtDecifrado.addFrase(resulDecifrado);
	}
}