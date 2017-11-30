package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class CadastroLoginGUI extends JFrame implements ActionListener 
{
	int Indice;
	private Container c;
	private JPanel Painel, botoes, Cabecalho;
	private JTextField tnome;
	private JPasswordField tsenha;
	@SuppressWarnings("unused")
	private JLabel lnome, ltitulo, lsenha;
	private JButton bVoltar, bCadastrar;
	private ResourceBundle bn = null;

	public CadastroLoginGUI(int indice) 
	{
		setIndice(indice);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("cadastrologintitulo"));

		c = getContentPane();
		c.setLayout(new BorderLayout());

		Painel = new JPanel(new FlowLayout());
		botoes = new JPanel(new GridLayout(1, 2));
		Cabecalho = new JPanel(new FlowLayout());

		c.add(Cabecalho, BorderLayout.NORTH);
		c.add(Painel, BorderLayout.CENTER);
		c.add(botoes, BorderLayout.SOUTH);

		lnome= new JLabel(bn.getString("usuario"));

		tnome = new JTextField(21);

		lsenha = new JLabel(bn.getString("senha"));

		tsenha = new JPasswordField(21);

		bCadastrar = new JButton(bn.getString("Cadastrar"));
		bCadastrar.addActionListener(this);

		bVoltar = new JButton(bn.getString("Usuario.voltar.botao"));
		bVoltar.addActionListener(this);

		Painel.add(lnome);
		Painel.add(tnome);
		Painel.add(lsenha);
		Painel.add(tsenha);
		botoes.add(bCadastrar);
		botoes.add(bVoltar);

		setSize(250, 216);
		setLocationRelativeTo(Painel);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
	}

	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == bVoltar) 
		{
			dispose();
			new LoginGUI(getIndice()); 
		}

		if (event.getSource() == bCadastrar) 
		{
			JOptionPane.showMessageDialog(null, bn.getString("cadastrarUsuario.Add"), bn.getString("cadastrarUsuario.AddTitulo"),
					JOptionPane.PLAIN_MESSAGE);
			dispose();
			new LoginGUI(getIndice()); 
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
}