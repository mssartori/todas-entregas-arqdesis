package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class AlterarEmpresaIdGUI extends JFrame implements ActionListener
{
	private int Indice, id;
	boolean deletado = true;
	private JPanel Painel, Cabecalho, Painel2, Botoes;
	private JLabel lID;
	private JButton bRemover, bVoltar;
	private JTextField tID;
	private ResourceBundle bn = null;
	private int cont=0; 
	Connection conn = null;  
	control.EmpresaDAO emDAO = new control.EmpresaDAO();

	public AlterarEmpresaIdGUI(int indice) 
	{      
		setIndice(indice);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("alterarEmpresa.tit"));

		Painel = new JPanel();
		Painel.setLayout(new BorderLayout());
		setContentPane(Painel);

		Cabecalho = new JPanel();
		Painel.add(Cabecalho, BorderLayout.NORTH);

		Painel2 = new JPanel();
		Painel.add(Painel2, BorderLayout.CENTER);

		lID = new JLabel(bn.getString("alterarEmpresa.texto"));
		Painel2.add(lID);

		tID = new JTextField();
		Painel2.add(tID);
		tID.setColumns(19);

		Botoes = new JPanel();
		Painel.add(Botoes, BorderLayout.SOUTH);

		bRemover = new JButton(bn.getString("alterarEmpresa.bAlterar"));
		bRemover.addActionListener(this);
		Botoes.add(bRemover);

		bVoltar = new JButton(bn.getString("alterarEmpresa.bvoltar"));
		bVoltar.addActionListener(this);
		Botoes.add(bVoltar);

		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(276, 150);      
	}

	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == bVoltar) 
		{
			dispose();
			new ControleDeEmpresasGUI(getIndice()); 
		}

		if (event.getSource() == bRemover) 
		{  
			int a = Integer.parseInt(tID.getText());
			setId(a);
			Connection conn = null;
			try
			{
				control.AcessoBD bd = new control.AcessoBD();
				conn = bd.obtemConexao();
				conn.setAutoCommit(false);           

				@SuppressWarnings("unused")
				model.Empresa e = new model.Empresa();

				cont = emDAO.Selecionarid(getId(),conn);

				if(cont==1)
				{
					dispose();
					new AlterarEmpresaGUI(getIndice(),getId());

				}    
				else{
					tID.setText("");
					JOptionPane.showMessageDialog(null, bn.getString("idEmpresa.mensagem"), bn.getString("idEmpresa.mensagemTitulo"),
							JOptionPane.PLAIN_MESSAGE); 
				}    
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
		new AlterarEmpresaIdGUI(3);
	}

	public void setId(int i) 
	{
		id = i;
	}

	public int getId() 
	{
		return id;
	}
}