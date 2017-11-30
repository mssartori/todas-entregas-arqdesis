package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class AlterarEmpresaGUI extends JFrame implements ActionListener 
{
	private int Indice;
	private boolean alterado = true;
	private Container c;
	private JPanel Painel, Botao, Sul, Leste, Oeste;
	private JLabel lnome, lconjunto, ltemperatura, lcnpj, lrazaosocial, lhorariofunc, lautorizacao, larcondicionado;
	private JTextField tnome, tconjunto, ttemperatura, tcnpj, trazaosocial, thorariofunc, tautorizacao, tarcondicionado;
	private JButton balterar, bvoltar;
	private ResourceBundle bn = null;
	private ArrayList<model.Empresa> lem = new ArrayList<model.Empresa>();  
	private int id=0;

	Connection conn = null;  
	control.EmpresaDAO emDAO = new control.EmpresaDAO();

	public AlterarEmpresaGUI(int indice, int i) 
	{
		setIndice(indice);
		setId(i);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("alterarEmpresa.titulo"));

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

		lnome = new JLabel(bn.getString("alterarEmpresa.nome"));
		tnome = new JTextField(20);


		lconjunto= new JLabel(bn.getString("alterarEmpresa.conjunto"));
		tconjunto = new JTextField(21);


		ltemperatura = new JLabel(bn.getString("alterarEmpresa.temperatura"));
		ttemperatura = new JTextField(20);


		lcnpj = new JLabel(bn.getString("alterarEmpresa.cnpj"));
		tcnpj = new JTextField(20);


		lrazaosocial = new JLabel(bn.getString("alterarEmpresa.razaosocial"));
		trazaosocial = new JTextField(20);


		lhorariofunc = new JLabel(bn.getString("alterarEmpresa.horariofuncionamento"));
		thorariofunc = new JTextField(20);


		lautorizacao = new JLabel(bn.getString("alterarEmpresa.autorizacaoedificio"));
		tautorizacao = new JTextField(20);


		larcondicionado = new JLabel(bn.getString("alterarEmpresa.horariodoarcondiconado"));
		tarcondicionado = new JTextField(20);


		balterar = new JButton(bn.getString("alterarEmpresa.alterar.botao"));
		balterar.addActionListener(this);

		bvoltar = new JButton(bn.getString("alterarEmpresa.voltar.botao"));
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
		Painel.add(tautorizacao);

		Painel.add(larcondicionado);
		Painel.add(tarcondicionado);

		Painel.add(balterar); 
		Painel.add(bvoltar);

		setSize(625, 250);
		setLocationRelativeTo(Painel);
		setResizable(true);
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
			model.Empresa e = new model.Empresa();

			lem = emDAO.SelecionarEspecifico(id,conn);

			tnome.setText(lem.get(0).getNome());
			tconjunto.setText(lem.get(0).getConjunto());
			tcnpj.setText(lem.get(0).getCnpj());
			trazaosocial.setText(lem.get(0).getRazaoSocial());
			thorariofunc.setText(lem.get(0).getHorarioFuncionamento());
			tautorizacao.setText(lem.get(0).getAutorizacao());
			ttemperatura.setText(lem.get(0).getTemperatura());
			tarcondicionado.setText(lem.get(0).getHorarioArCondicionado());

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

				// *** IMPORTANTE ***: Força o uso de transação.
				conn.setAutoCommit(false); 


				String nome = tnome.getText();
				String conjunto = tconjunto.getText();
				String temperatura = ttemperatura.getText();
				String cnpj = tcnpj.getText();
				String razaosocial = trazaosocial.getText();
				String horariofunc = thorariofunc.getText();
				String autorizacao = tautorizacao.getText();
				String arcondicionado = tarcondicionado.getText();

				model.Empresa e = new model.Empresa(getId(),nome, conjunto, cnpj, razaosocial, horariofunc, autorizacao, temperatura, arcondicionado);
				emDAO.alterar(conn, e);

				alterado = true;

				conn.commit();


			}
			catch (Exception e)
			{
				alterado = false;
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
			if(alterado == true)
			{
				JOptionPane.showMessageDialog(null, bn.getString("alterarEmpresa.mensagem"), bn.getString("alterarEmpresa.mensagemTitulo"),
						JOptionPane.PLAIN_MESSAGE);     
				dispose();
				new ControleDeEmpresasGUI(Indice);
			}
			else if(alterado == false)
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

	public void setId(int i) 
	{
		id = i;
	}

	public int getId() 
	{
		return id;
	}

}