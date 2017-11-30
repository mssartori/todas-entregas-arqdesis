package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("serial")
public class ErroAoAlterarGUI extends JFrame implements ActionListener
{
	private int Indice;
	private JPanel Painel;
	private JLabel lblErro;
	private JButton bVoltar;
	private ResourceBundle bn = null;	

	public ErroAoAlterarGUI(int indice) 
	{
		setIndice(indice);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("Controle.ErroAlterar.titulo"));

		Painel = new JPanel();
		Painel.setLayout(new FlowLayout());
		setContentPane(Painel);

		lblErro = new JLabel(bn.getString("Controle.ErroAlterar.Mensagem"));
		lblErro.setFont(new Font("Arial", Font.BOLD, 22));
		lblErro.setHorizontalAlignment(SwingConstants.CENTER);
		Painel.add(lblErro, BorderLayout.CENTER);

		bVoltar = new JButton(bn.getString("Controle.ErroAlterar.voltar"));
		bVoltar.addActionListener(this);
		Painel.add(bVoltar, BorderLayout.SOUTH);

		setSize(300, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource()== bVoltar)
		{
			dispose();
			new CadastroUsuarioGUI(getIndice());
			dispose();
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