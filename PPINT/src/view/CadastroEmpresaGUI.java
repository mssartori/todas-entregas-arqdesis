package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class CadastroEmpresaGUI extends JFrame implements ActionListener 
{
	private int Indice;
	private boolean Cadastro = true;
	private Container c;
	private String auto = "", ar = "";
	@SuppressWarnings("rawtypes")
	private JComboBox jcAutoriza;
	private JPanel Painel, Botao, Sul, Leste, Oeste;
	private JLabel lnome, lconjunto, ltemperatura, lcnpj, lrazaosocial, lhorariofunc, lautorizacao, larcondicionado;
	@SuppressWarnings("unused")
	private JTextField tnome, tconjunto, ttemperatura, tcnpj, trazaosocial, thorariofunc, tautorizacao, tarcondicionado;
	private JButton bcadastrar, bvoltar;
	private ResourceBundle bn = null;
	@SuppressWarnings("unused")
	private ArrayList<model.Empresa> lem = new ArrayList<model.Empresa>();  

	Connection conn = null;  
	control.EmpresaDAO emDAO = new control.EmpresaDAO();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CadastroEmpresaGUI(int indice) 
	{
		setIndice(indice);

		bn = new idiomas.Internacionalizacao().troca(indice);

		ar = bn.getString("Autorizacao");

		setTitle(bn.getString("cadastroEmpresa.titulo"));

		String combo[] = {bn.getString("Autorizacao"), bn.getString("NAutorizacao")};

		c = getContentPane();
		c.setLayout(new BorderLayout());

		Painel = new JPanel(new GridLayout(10, 2));
		Botao = new JPanel(new GridLayout(1, 2));
		Sul = new JPanel(new FlowLayout());
		Leste = new JPanel(new FlowLayout()); 
		Oeste = new JPanel(new FlowLayout());

		c.add(Painel, BorderLayout.CENTER);
		c.add(Botao, BorderLayout.NORTH);
		c.add(Sul, BorderLayout.SOUTH);
		c.add(Leste, BorderLayout.EAST);
		c.add(Oeste, BorderLayout.WEST);

		lnome = new JLabel(bn.getString("cadastroEmpresa.nome"));
		tnome = new JTextField(20);

		lconjunto= new JLabel(bn.getString("cadastroEmpresa.conjunto"));
		tconjunto = new JTextField(21);

		ltemperatura = new JLabel(bn.getString("cadastroEmpresa.temperatura"));
		ttemperatura = new JTextField(20);

		lcnpj = new JLabel(bn.getString("cadastroEmpresa.cnpj"));
		tcnpj = new JTextField(20);

		lrazaosocial = new JLabel(bn.getString("cadastroEmpresa.razaosocial"));
		trazaosocial = new JTextField(20);

		lhorariofunc = new JLabel(bn.getString("cadastroEmpresa.horariofuncionamento"));
		thorariofunc = new JTextField(20);

		lautorizacao = new JLabel(bn.getString("cadastroEmpresa.autorizacaoedificio"));
		//Fazer internacionalização
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

							if(aut.equalsIgnoreCase(ar))
							{
								ar = "" + bn.getString("Autorizacao");
								auto = ar;
							}
							else
							{
								ar = "" + bn.getString("NAutorizacao");
								auto = ar;
							}
						}
					} // end method itemStateChanged
				} // end anonymous inner class
				);

		larcondicionado = new JLabel(bn.getString("cadastroEmpresa.horariodoarcondiconado"));
		tarcondicionado = new JTextField(20);

		bcadastrar = new JButton(bn.getString("cadastroEmpresa.cadastrar.botao"));
		bcadastrar.addActionListener(this);

		bvoltar = new JButton(bn.getString("cadastroEmpresa.voltar.botao"));
		bvoltar.addActionListener(this);

		Painel.add(lnome);
		Painel.add(tnome);

		Painel.add(lconjunto);
		Painel.add(tconjunto);

		Painel.add(ltemperatura);
		Painel.add(ttemperatura);

		Painel.add(lcnpj);
		Painel.add(tcnpj);

		Painel.add(lrazaosocial);
		Painel.add(trazaosocial);

		Painel.add(lhorariofunc);
		Painel.add(thorariofunc);

		Painel.add(lautorizacao);
		Painel.add(jcAutoriza);

		Painel.add(larcondicionado);
		Painel.add(tarcondicionado);

		Painel.add(bcadastrar); 
		Painel.add(bvoltar);

		setSize(625, 250);
		setLocationRelativeTo(Painel);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource()== bcadastrar)
		{
			Connection conn = null;

			try
			{
				// obtem conexao com o banco
				control.AcessoBD bd = new control.AcessoBD();
				conn = bd.obtemConexao();

				// *** IMPORTANTE ***: Força o uso de transação.
				conn.setAutoCommit(false); 



				String nome = tnome.getText();
				String conjunto = tconjunto.getText();
				String temperatura = ttemperatura.getText();
				String cnpj = tcnpj.getText();
				String razaosocial = trazaosocial.getText();
				String horariofunc = thorariofunc.getText();
				String arcondicionado = tarcondicionado.getText();

				model.Empresa e = new model.Empresa(nome, conjunto, cnpj, razaosocial, horariofunc, auto, temperatura, arcondicionado);
				emDAO.incluir(conn, e);

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
				new ControleDeEmpresasGUI(getIndice());
			}
			else if(Cadastro == false)
			{            
				new ErroAoCadastrarGUI(getIndice());
				dispose();
			}
		}

		if(event.getSource()== bvoltar)
		{
			dispose();
			new ControleDeEmpresasGUI(Indice);
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
		new CadastroEmpresaGUI(3);
	}
}
