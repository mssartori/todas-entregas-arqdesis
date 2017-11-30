package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class DetalhesUsuarioGUI extends JFrame implements ActionListener 
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
	@SuppressWarnings("unused")
	private JButton balterar, bvoltar;
	@SuppressWarnings("rawtypes")
	private JComboBox jcAutoriza, jUsuario, jcEmpresa;
	private String empresa[] = {"LTDA Entregas", "MX-Cars", "Tio do Pão"};
	private String tipo = "", usu = "", empres="", tipo2 = "";
	@SuppressWarnings("unused")
	private int empre = 0;
	private ResourceBundle bn = null;
	private int id=0;
	private ArrayList<model.Funcionario> lfunc = new ArrayList<model.Funcionario>();  

	Connection conn = null;  
	control.FuncionarioDAO func = new control.FuncionarioDAO(); 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DetalhesUsuarioGUI(int indice, int i) 
	{
		setIndice(indice);
		setId(i);

		bn = new idiomas.Internacionalizacao().troca(indice);

		tipo = bn.getString("Sindico");
		tipo2 = bn.getString("Atendente");

		setTitle(bn.getString("detalhesUsuario.titulo"));

		String combo[] = {bn.getString("Sim"), bn.getString("Nao")};
		String usuario[] = {bn.getString("Sindico"), bn.getString("Atendente"), bn.getString("Funcionario")};

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

		tnome.setEditable(false);

		lcargo = new JLabel(bn.getString("Usuario.cargo"));
		tcargo = new JTextField(20);

		tcargo.setEditable(false);

		lnascimento = new JLabel(bn.getString("Usuario.nascimento"));
		tnascimento = new JTextField(20);

		tnascimento.setEditable(false);

		lrg = new JLabel(bn.getString("Usuario.rg"));
		trg = new JTextField(20);

		trg.setEditable(false);

		lcpf = new JLabel(bn.getString("Usuario.cpf"));
		tcpf = new JTextField(20);

		tcpf.setEditable(false);

		llogin = new JLabel(bn.getString("Usuario.login"));
		tlogin = new JTextField(20);

		tlogin.setEditable(false);

		lsenha = new JLabel(bn.getString("Usuario.senha"));
		tsenha = new JPasswordField(21);

		tsenha.setEditable(false);

		lhorario = new JLabel(bn.getString("Usuario.horario"));
		thorario = new JTextField(20);

		thorario.setEditable(false);

		lautorizacao = new JLabel(bn.getString("Usuario.autorizacao"));

		lacesso = new JLabel(bn.getString("Usuario.acesso"));

		lAr = new JLabel("Autorização Ar-Condicionado");

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

							if(aut.equalsIgnoreCase("Sim")||aut.equalsIgnoreCase("Yes")||aut.equalsIgnoreCase("Sí"))
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

		lTipoUsu = new JLabel("Tipo De Usuário:");

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

							if(usu.equalsIgnoreCase(tipo))
							{
								tipo = bn.getString("Sindico");
							}
							else if(usu.equalsIgnoreCase(tipo2))
							{
								tipo = bn.getString("Atendente");
							}
							else
							{
								tipo = bn.getString("Funcionario");
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
							else if(empres.equalsIgnoreCase("Tio do Pão"))
							{
								empre = 3;
							}
						}
					} // end method itemStateChanged
				} // end anonymous inner class
				);

		Painel.add(lnome);
		Painel.add(tnome);

		Painel.add(lcargo);
		Painel.add(jUsuario);

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

			// *** IMPORTANTE ***: Força o uso de transação.
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
	}}