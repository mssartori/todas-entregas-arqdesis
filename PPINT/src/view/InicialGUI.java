package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class InicialGUI extends JFrame implements ActionListener 
{
	private int Indice;
	private Container c;
	private JPanel botoes;
	private JPanel Cabecalho;
	private JLabel ltitulo;
	private JButton bUsu, bEmpresa, bAcesso, bAr;
	private ResourceBundle bn = null;

	public void setInternacionaliza(int indice)
	{
		bn = new idiomas.Internacionalizacao().troca(indice);
		setTitle(bn.getString("inicial"));
		ltitulo.setText(bn.getString("sejabemvindo"));
		bUsu.setText(bn.getString("usuario.botao"));
		bEmpresa.setText(bn.getString("empresas.botao"));
		bAcesso.setText(bn.getString("acesso.botao"));
		bAr.setText(bn.getString("ar.botao"));
	}

	public InicialGUI(int indice) 
	{
		setIndice(indice);

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

		bUsu = new JButton(bn.getString("usuario.botao"));
		bUsu.addActionListener(this);

		bEmpresa = new JButton(bn.getString("empresas.botao"));
		bEmpresa.addActionListener(this);

		bAcesso = new JButton(bn.getString("acesso.botao"));
		bAcesso.addActionListener(this);

		bAr = new JButton(bn.getString("ar.botao"));
		bAr.addActionListener(this);

		Cabecalho.add(ltitulo);

		botoes.add(bUsu);
		botoes.add(bEmpresa);
		botoes.add(bAcesso);
		botoes.add(bAr);


		setSize(700, 100);
		setLocationRelativeTo(c);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) 
	{      
		if (event.getSource() == bUsu) 
		{   
			dispose();
			new ControleDeFuncionariosGUI(getIndice());         
		}

		if (event.getSource() == bEmpresa) 
		{   
			setVisible(false);
			new ControleDeEmpresasGUI(getIndice());         
		}

		if (event.getSource() == bAcesso) 
		{   
			dispose();
			new ControleCatracaGUI(getIndice());
		}

		if (event.getSource() == bAr) 
		{   
			dispose();
			new ControleArCondicionadoGUI(getIndice(),"");         
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

	public static void main(String [] args)
	{
		new InicialGUI(3);
	}
}