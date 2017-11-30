package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.util.Calendar;

@SuppressWarnings("serial")
public class BotaoEntrarGUI extends JFrame implements ActionListener
{
	private int Indice;
	private String tipo,nome;
	private JPanel Painel;
	private JTextPane Texto;
	private boolean login = true;
	private JButton bOk;
	private ResourceBundle bn = null;
	private ArrayList<model.ControleAcesso> lcon = new ArrayList<model.ControleAcesso>(); 
	Calendar calendar = Calendar.getInstance();
	Connection conn = null;  
	control.ControleAcessoDAO controleDAO = new control.ControleAcessoDAO();

	public BotaoEntrarGUI(int indice,String tipo,String nome) 
	{		
		setIndice(indice);
		setTipo(tipo);
		setNome(nome);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("bemvindo"));   

		Painel = new JPanel();
		setContentPane(Painel);
		Painel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Texto = new JTextPane();
		Texto.setEditable(false);
		Texto.setFont(new Font("Tahoma", Font.BOLD, 22));

		if(login == true)
		{
			Texto.setText(bn.getString("atendenteBemVindo"));
		}
		else
		{
			Texto.setText(bn.getString("atendenteBemVindoErro"));
		}

		Painel.add(Texto);

		bOk = new JButton("OK");
		bOk.addActionListener(this);

		Painel.add(bOk);

		setSize(200, 140);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource()== bOk)
		{
			if(getTipo()=="Funcionario"){

				Connection conn = null;
				try
				{
					// obtem conexao com o banco
					control.AcessoBD bd = new control.AcessoBD();
					conn = bd.obtemConexao();

					// *** IMPORTANTE ***: Força o uso de transação.
					conn.setAutoCommit(false);           

					@SuppressWarnings("unused")
					model.ControleAcesso c = new model.ControleAcesso(); 

					lcon = controleDAO.SelecionarControleAcesso(getNome(),conn);

					int id = lcon.get(0).getidFuncionario();

					String datahora= String.format("%tF",calendar)+"  "+String.format("%1$tH:%1$tM:%1$tS",calendar);

					model.ControleAcesso ca = new model.ControleAcesso(datahora,id);

					controleDAO.incluir(conn,ca);

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

				dispose();
				new InicialFuncionarioGUI(getIndice(),getNome());

			}
			else{
				Connection conn = null;
				try
				{
					// obtem conexao com o banco
					control.AcessoBD bd = new control.AcessoBD();
					conn = bd.obtemConexao();

					// *** IMPORTANTE ***: Força o uso de transação.
					conn.setAutoCommit(false);           

					@SuppressWarnings("unused")
					model.ControleAcesso c = new model.ControleAcesso(); 

					lcon = controleDAO.SelecionarControleAcesso(getNome(),conn);

					int id = lcon.get(0).getidFuncionario();

					String datahora= String.format("%tF",calendar)+"  "+String.format("%1$tH:%1$tM:%1$tS",calendar);

					model.ControleAcesso ca = new model.ControleAcesso(datahora,id);

					controleDAO.incluir(conn,ca);

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


				dispose();
				new InicialGUI(getIndice());
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

	public void setTipo(String t) 
	{
		tipo = t;
	}

	public String getTipo() 
	{
		return tipo;
	}


	public void setNome(String n) 
	{
		nome = n;
	}

	public String getNome() 
	{
		return nome;
	}

}