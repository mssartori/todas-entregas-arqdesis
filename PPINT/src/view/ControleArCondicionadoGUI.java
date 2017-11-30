package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class ControleArCondicionadoGUI extends JFrame implements ActionListener
{
	@SuppressWarnings("unused")
	private int Indice,idem;
	private String nome="";
	private JPanel Painel;
	private JTextPane tpTemperatura,tpNovaTemperatura;
	@SuppressWarnings("rawtypes")
	private JComboBox jcTemperatura, jcEmpresa;
	private JButton btConfirmar;
	private JLabel lTemperatura, lEmpresa; 
	private ResourceBundle bn = null;
	private String item,atemp,ntemp="";
	private ArrayList<model.Empresa> lem = new ArrayList<model.Empresa>(); 
	private ArrayList<model.Funcionario> lfunc = new ArrayList<model.Funcionario>(); 
	private String temp[] = {"16º", "17º", "18º", "19º", "20º", "21º", "22º", "23º", "24º", "25º"};
	public Connection conn = null;
	control.EmpresaDAO emDAO = new control.EmpresaDAO();
	control.FuncionarioDAO fDAO = new control.FuncionarioDAO();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ControleArCondicionadoGUI(int indice,String nome) 
	{      

		setIndice(indice);
		setNome(nome);

		@SuppressWarnings("unused")
		model.Empresa em = new model.Empresa();
		@SuppressWarnings("unused")
		model.Funcionario f= new model.Funcionario();

		bn = new idiomas.Internacionalizacao().troca(indice);

		try
		{
			// obtem conexao com o banco
			control.AcessoBD bd = new control.AcessoBD();
			conn = bd.obtemConexao();

			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);

			if(getNome()=="")
			{
				lem=emDAO.SelecionarTudo(conn);

				String empresa[] = new String[lem.size()];

				for(int i=0;i<lem.size();i++)
				{
					empresa[i]=lem.get(i).getNome();

				}
				jcEmpresa = new JComboBox(empresa);

			}
			else{
				lfunc=fDAO.SelecionarNomeEmpresa(getNome(),conn);

				String empresa[] = new String[lfunc.size()];

				for(int i=0;i<lfunc.size();i++)
				{
					empresa[i]=lfunc.get(i).getEmpresa();

				}
				jcEmpresa = new JComboBox(empresa);

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


		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("Controle.ArCondicionado.titulo"));

		Painel = new JPanel();
		Painel.setLayout(new FlowLayout());
		setContentPane(Painel);

		lTemperatura = new JLabel("Temperatura");

		jcTemperatura = new JComboBox(temp);

		jcTemperatura.addItemListener
		(
				new ItemListener() // anonymous inner class
				{
					// handle JComboBox event
					public void itemStateChanged( ItemEvent event )
					{
						// determine whether check box selected
						if ( event.getStateChange() == ItemEvent.SELECTED )
						{
							item = bn.getString("Controle.ArCondicionado.Novatemperatura");
							ntemp = jcTemperatura.getSelectedItem()+"";
							tpNovaTemperatura.setText(item+ntemp+ "\n\n     ");
						}
					} // end method itemStateChanged
				} // end anonymous inner class
				);

		lEmpresa = new JLabel("Empresa");
		jcEmpresa.addItemListener
		(
				new ItemListener() // anonymous inner class
				{
					// handle JComboBox event
					public void itemStateChanged( ItemEvent event )
					{
						// determine whether check box selected
						if ( event.getStateChange() == ItemEvent.SELECTED )
						{
							try
							{
								// obtem conexao com o banco
								control.AcessoBD bd = new control.AcessoBD();
								conn = bd.obtemConexao();

								// *** IMPORTANTE ***: Força o uso de transação.
								conn.setAutoCommit(false);

								lem=null;
								int cod=jcEmpresa.getSelectedIndex()+1;
								lem = emDAO.SelecionarEspecifico(cod,conn);
								item = bn.getString("Controle.ArCondicionado.temperatura");
								atemp = lem.get(0).getTemperatura()+"";
								tpTemperatura.setText(item+atemp+"\n\n     ");

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
					} // end method itemStateChanged
				} // end anonymous inner class
				);



		Painel.add(lEmpresa);
		Painel.add(jcEmpresa);
		Painel.add(lTemperatura);
		Painel.add(jcTemperatura);

		tpTemperatura = new JTextPane();
		tpTemperatura.setEditable(false);
		tpTemperatura.setFont(new Font("Arial", Font.BOLD, 15));
		tpTemperatura.setText(bn.getString("Controle.ArCondicionado.temperatura") + "\n\n");
		Painel.add(tpTemperatura);

		item = bn.getString("Controle.ArCondicionado.temperatura");
		if(getNome()==""){
			atemp =  lem.get(0).getTemperatura()+"";
		}
		else{
			atemp =  lfunc.get(0).getTemperatura()+"";
		}      
		tpTemperatura.setText(item+atemp+"\n\n     ");

		tpNovaTemperatura = new JTextPane();
		tpNovaTemperatura.setEditable(false);
		tpNovaTemperatura.setFont(new Font("Arial", Font.BOLD,15));
		item = bn.getString("Controle.ArCondicionado.Novatemperatura");
		ntemp = "16º";
		tpNovaTemperatura.setText(item+ntemp+ "\n\n");
		Painel.add(tpNovaTemperatura);

		btConfirmar = new JButton(bn.getString("Controle.ArCondicionado.comfirmar.botao"));
		btConfirmar.addActionListener(this);
		Painel.add(btConfirmar, BorderLayout.SOUTH);


		setSize(350, 225);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == btConfirmar) 
		{  

			try
			{
				// obtem conexao com o banco
				control.AcessoBD bd = new control.AcessoBD();
				conn = bd.obtemConexao();

				// *** IMPORTANTE ***: Força o uso de transação.
				conn.setAutoCommit(false);

				lem=null;
				String r=jcEmpresa.getSelectedItem()+"";
				lem=emDAO.BuscarEmpresa(r,conn);


				int idEmpresa=lem.get(0).getidEmpresa();
				String Temperatura=ntemp;
				model.Empresa empre = new model.Empresa(idEmpresa,Temperatura);
				emDAO.alterarTemperatura(conn,empre);

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
			JOptionPane.showMessageDialog(null, bn.getString("Controle.ArCondicionado.msgSucesso"), bn.getString("Controle.ArCondicionado.msgSucessoSuper"),
					JOptionPane.PLAIN_MESSAGE);      

			if(getNome()=="")
			{
				dispose(); 
				new InicialGUI(getIndice());
			}
			else{
				dispose(); 
				new InicialFuncionarioGUI(getIndice(),getNome());
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
		new ControleArCondicionadoGUI(3,"Leths");
	}
}