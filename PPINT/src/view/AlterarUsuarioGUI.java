package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class AlterarUsuarioGUI extends JFrame implements ActionListener 
{
	private int Indice;
	@SuppressWarnings("unused")
	private boolean Cadastro = true, vf = true, ar = true;
	private Container c;
	private JPanel Painel, Botao, Norte, Leste, Oeste;
	private JPasswordField tsenha;
	@SuppressWarnings("unused")
	private JLabel lTipoUsu, lempresa, lAr, lnome, lcargo, lnascimento, lrg, lcpf, llogin, lsenha, lhorario, lautorizacao, lacesso, lautorizado1, lautorizado2, lnaoautorizado1, lnaoautorizado2;
	private JTextField tnome, tcargo, tnascimento, trg, tcpf, tlogin, thorario;
	private JButton balterar, bvoltar;
	@SuppressWarnings("rawtypes")
	private JComboBox jcAutoriza, jUsuario, jcEmpresa;
	private String combo[] = {"Sim", "N�o"};
	private String usuario[] = {"S�ndico", "Atendente", "Funcion�rio"};
	private String empresa[] = {"LTDA Entregas", "MX-Cars", "Tio do P�o"};
	@SuppressWarnings("unused")
	private String tipo = "", usu = "", empres="";
	private int empre = 0;
	private ResourceBundle bn = null;
	private int id=0;
	private ArrayList<model.Funcionario> lfunc = new ArrayList<model.Funcionario>();  

	Connection conn = null;  
	control.FuncionarioDAO func = new control.FuncionarioDAO(); 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AlterarUsuarioGUI(int indice, int i) 
	{
		setIndice(indice);
		setId(i);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("alterarUsuario.titulo"));

		c = getContentPane();
		c.setLayout(new BorderLayout());

		Painel = new JPanel(new GridLayout(10, 2));
		Botao = new JPanel(new FlowLayout());
		Norte = new JPanel(new FlowLayout());
		Leste = new JPanel(new FlowLayout()); 
		Oeste = new JPanel(new FlowLayout());

		c.add(Norte, BorderLayout.NORTH);
		c.add(Leste, BorderLayout.EAST);
		c.add(Oeste, BorderLayout.WEST);

		lnome= new JLabel(bn.getString("Usuario.nome"));
		tnome = new JTextField(21);

		lcargo = new JLabel(bn.getString("Usuario.cargo"));
		tcargo = new JTextField(20);

		lnascimento = new JLabel(bn.getString("Usuario.nascimento"));
		tnascimento = new JTextField(20);

		lrg = new JLabel(bn.getString("Usuario.rg"));
		trg = new JTextField(20);

		lcpf = new JLabel(bn.getString("Usuario.cpf"));
		tcpf = new JTextField(20);

		llogin = new JLabel(bn.getString("Usuario.login"));
		tlogin = new JTextField(20);
		tlogin.setEditable(false);

		lsenha = new JLabel(bn.getString("Usuario.senha"));
		tsenha = new JPasswordField(21);
		tsenha.setEditable(false);

		lhorario = new JLabel(bn.getString("Usuario.horario"));
		thorario = new JTextField(20);

		lautorizacao = new JLabel(bn.getString("Usuario.autorizacao"));

		lacesso = new JLabel(bn.getString("Usuario.acesso"));

		lAr = new JLabel("Autoriza��o Ar-Condicionado");

		balterar = new JButton(bn.getString("alterarUsuario.alterar.botao"));
		balterar.addActionListener(this);

		bvoltar = new JButton(bn.getString("Usuario.voltar.botao"));
		bvoltar.addActionListener(this);

		jcAutoriza = new JComboBox(combo);

		jcAutoriza.addItemListener
		(
				new ItemListener() // anonymous inner class
				{
					// handle JComboBox event
					public void itemStateChanged( ItemEvent event )
					{
						// determine whether check box selected
						if ( event.getStateChange() == ItemEvent.SELECTED )
						{
							String aut = "" + jcAutoriza.getSelectedItem();

							if(aut.equalsIgnoreCase("Sim"))
							{
								ar = true;
							}
							else
							{
								ar = false;
							}
						}
					} // end method itemStateChanged
				} // end anonymous inner class
				);

		lTipoUsu = new JLabel("Tipo De Usu�rio:");

		jUsuario = new JComboBox(usuario);


		jUsuario.addItemListener
		(
				new ItemListener() // anonymous inner class
				{
					// handle JComboBox event
					public void itemStateChanged( ItemEvent event )
					{
						// determine whether check box selected
						if ( event.getStateChange() == ItemEvent.SELECTED )
						{
							usu = "" + jUsuario.getSelectedItem();

							if(usu.equalsIgnoreCase("S�ndico"))
							{
								tipo = "S�ndico";
							}
							else if(usu.equalsIgnoreCase("Atendente"))
							{
								tipo = "Atendente";
							}
							else if(usu.equalsIgnoreCase("Funcion�rio"))
							{
								tipo = "Funcion�rio";
							}
						}
					} // end method itemStateChanged
				} // end anonymous inner class
				);     
		lempresa = new JLabel("Empresa:");

		jcEmpresa = new JComboBox(empresa);

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
							empres = "" + jcEmpresa.getSelectedItem();

							if(empres.equalsIgnoreCase("LTDA Entregas"))
							{
								empre = 1;
							}
							else if(empres.equalsIgnoreCase("MX-Cars"))
							{
								empre = 2;
							}
							else if(empres.equalsIgnoreCase("Tio do P�o"))
							{
								empre = 3;
							}
						}
					} // end method itemStateChanged
				} // end anonymous inner class
				);

		Painel.add(lnome);
		Painel.add(tnome);

		//Painel.add(lcargo);
		//Painel.add(jUsuario);

		Painel.add(lnascimento);
		Painel.add(tnascimento);

		Painel.add(lrg);
		Painel.add(trg);

		Painel.add(lcpf);
		Painel.add(tcpf);

		Painel.add(llogin);
		Painel.add(tlogin);

		Painel.add(lsenha);
		Painel.add(tsenha);

		Painel.add(lhorario);
		Painel.add(thorario);

		Painel.add(lAr);
		Painel.add(jcAutoriza);

		Painel.add(lempresa);
		Painel.add(jcEmpresa);         

		Botao.add(balterar); 
		Botao.add(bvoltar);

		c.add(Painel, BorderLayout.CENTER);
		c.add(Botao, BorderLayout.SOUTH);

		setSize(625, 310);
		setLocationRelativeTo(Painel);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		Connection conn = null;
		try
		{
			// obtem conexao com o banco
			control.AcessoBD bd = new control.AcessoBD();
			conn = bd.obtemConexao();

			// *** IMPORTANTE ***: For�a o uso de transa��o.
			conn.setAutoCommit(false);           

			@SuppressWarnings("unused")
			model.Funcionario f = new model.Funcionario();

			lfunc = func.SelecionarEspecifico(id,conn);

			tnome.setText(lfunc.get(0).getNome());
			tcargo.setText(lfunc.get(0).getFuncao());
			trg.setText(lfunc.get(0).getRG());
			tcpf.setText(lfunc.get(0).getCPF());
			tlogin.setText(lfunc.get(0).getLogin());
			tsenha.setText(lfunc.get(0).getSenha());
			tnascimento.setText(lfunc.get(0).getDataNasc());
			thorario.setText(lfunc.get(0).getAcessoCatraca());

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

	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource()== balterar)
		{

			Connection conn = null;
			try
			{
				// obtem conexao com o banco
				control.AcessoBD bd = new control.AcessoBD();
				conn = bd.obtemConexao();

				// *** IMPORTANTE ***: For�a o uso de transa��o.
				conn.setAutoCommit(false); 



				String nome = tnome.getText();
				@SuppressWarnings("unused")
				String cargo = tcargo.getText();
				String rg = trg.getText();
				String cpf = tcpf.getText();
				String login = tlogin.getText();
				@SuppressWarnings("deprecation")
				String senha = tsenha.getText();
				String nascimento = tnascimento.getText();
				String horario = thorario.getText();

				model.Funcionario f= new model.Funcionario(getId(),nome, usu, nascimento, rg, cpf, login, senha, horario, false, ar, empre);
				func.alterar(conn,f);

				Cadastro = true;

				conn.commit();


			}
			catch (Exception e)
			{
				Cadastro = false;
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
			if(Cadastro == true)
			{
				JOptionPane.showMessageDialog(c, bn.getString("cadastrarUsuario.Add"), bn.getString("cadastrarUsuario.AddTitulo"),
						JOptionPane.PLAIN_MESSAGE);     
				dispose();
				new ControleDeFuncionariosGUI(getIndice());
			}
			else
			{            
				new ErroAoCadastrarGUI(getIndice());
				dispose();
			}
		}

		if(event.getSource()== bvoltar)
		{
			dispose();
			new ControleDeFuncionariosGUI(getIndice());
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

	public void setId(int i) 
	{
		id = i;
	}

	public int getId() 
	{
		return id;
	}

	public static void main(String args[])
	{
		new AlterarUsuarioGUI(3,1);
	}



}