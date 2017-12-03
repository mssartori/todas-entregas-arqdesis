package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class InicialFuncionarioGUI extends JFrame implements ActionListener 
{
	private int Indice;
	private String nome="";
	private Container c;
	private JPanel botoes;
	private JPanel Cabecalho;
	private JLabel ltitulo;
	private JButton bAr;
	private ResourceBundle bn = null;

	public void setInternacionaliza(int indice)
	{
		bn = new idiomas.Internacionalizacao().troca(indice);
		setTitle(bn.getString("inicial"));
		ltitulo.setText(bn.getString("sejabemvindo"));
		bAr.setText(bn.getString("ar.botao"));
	}

	public InicialFuncionarioGUI(int indice,String nome) 
	{
		setIndice(indice);
		setNome(nome);

		bn = ResourceBundle.getBundle("prj");
		bn = new idiomas.Internacionalizacao().troca(indice);
		setTitle(bn.getString("inicial"));

		c = getContentPane();
		c.setLayout(new BorderLayout());

		Cabecalho = new JPanel(new FlowLayout());
		botoes = new JPanel(new GridLayout(1, 4));
		c.add(Cabecalho, BorderLayout.NORTH);
		c.add(botoes, BorderLayout.SOUTH);

		ltitulo = new JLabel(bn.getString("sejabemvindo"));

		bAr = new JButton(bn.getString("ar.botao"));
		bAr.addActionListener(this);

		Cabecalho.add(ltitulo);

		botoes.add(bAr);


		setSize(700, 100);
		setLocationRelativeTo(c);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) 
	{            
		if (event.getSource() == bAr) 
		{   
			dispose();
			new ControleArCondicionadoGUI(getIndice(),getNome());         
		}
	}

	public void setIndice(int indice) 
	{
		Indice = indice;
	}

	public int getIndice() 
	{
		return Indice;
	}

	public void setNome(String n) 
	{
		nome = n;
	}

	public String getNome() 
	{
		return nome;
	}

	public static void main(String [] args)
	{
		new InicialFuncionarioGUI(3,"Leths");
	}
}