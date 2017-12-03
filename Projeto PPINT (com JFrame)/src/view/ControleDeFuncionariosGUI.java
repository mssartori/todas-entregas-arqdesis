package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class ControleDeFuncionariosGUI extends JFrame  implements ActionListener
{
	private int Indice;
	private JPanel Painel, Painel2, Pbuscar;
	private JTextField tfBuscar;
	private JButton btAdicionar, btAlterar, btRemover, btDetalhes, btFinalizar;
	private JTable jtTabela, jtTabela2;
	private JScrollPane scroll, scroll2;
	private ResourceBundle bn = null;
	private ArrayList<model.Funcionario> lfunc = new ArrayList<model.Funcionario>();
	private int cont=0;  
	Connection conn = null;
	model.Funcionario func = new model.Funcionario();
	control.FuncionarioDAO f = new control.FuncionarioDAO();
	public void setInternacionaliza(int indice)
	{
		bn = new idiomas.Internacionalizacao().troca(indice);
		setTitle(bn.getString("Controle.Funcionario.titulo"));
		tfBuscar.setText(bn.getString("Controle.Funcionario.Buscar"));
		btAdicionar.setText(bn.getString("Controle.Funcionario.Adicionar"));
		btAlterar.setText(bn.getString("Controle.Funcionario.Alterar"));
		btRemover.setText(bn.getString("Controle.Funcionario.Remover"));
		btDetalhes.setText(bn.getString("Controle.Funcionario.Detalhes"));
		btFinalizar.setText(bn.getString("Controle.Funcionario.Finalizar"));
	}

	public ControleDeFuncionariosGUI(int indice) 
	{

		bn = new idiomas.Internacionalizacao().troca(indice);

		try
		{
			// obtem conexao com o banco
			control.AcessoBD bd = new control.AcessoBD();
			conn = bd.obtemConexao();

			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false); 
			setIndice(indice);



			String[] colunas = {bn.getString("Controle.Funcionario.id"), bn.getString("Controle.Funcionario.nome"), bn.getString("Controle.Funcionario.rg"), bn.getString("Controle.Funcionario.cpf"), bn.getString("Controle.Funcionario.nascimento")};  
			lfunc=f.SelecionarTudo(conn);

			for(int i=0;i<lfunc.size();i++){
				cont++;
			}
			Object linhas[][] = new Object[cont][5];   

			for(int i=0;i<lfunc.size();i++)
			{
				for(int j=0;j<5;j++)
				{
					linhas[i][j]=lfunc.get(i).Campos(j);
				}
			}
			setTitle(bn.getString("Controle.Funcionario.titulo"));

			Painel = new JPanel();
			Painel.setLayout(new BorderLayout(0, 0));
			setContentPane(Painel);

			Pbuscar = new JPanel();
			Pbuscar.setLayout( new FlowLayout());

			tfBuscar = new JTextField();
			Pbuscar.add(tfBuscar,new FlowLayout());
			tfBuscar.setColumns(10);


			Painel2 = new JPanel();
			Painel.add(Painel2, BorderLayout.SOUTH);

			btAdicionar = new JButton(bn.getString("Controle.Funcionario.Adicionar"));
			btAdicionar.addActionListener(this);
			Painel2.add(btAdicionar);

			btAlterar = new JButton(bn.getString("Controle.Funcionario.Alterar"));
			btAlterar.addActionListener(this);
			Painel2.add(btAlterar);

			btRemover = new JButton(bn.getString("Controle.Funcionario.Remover"));
			btRemover.addActionListener(this);
			Painel2.add(btRemover);

			btDetalhes = new JButton(bn.getString("Controle.Funcionario.Detalhes"));
			btDetalhes.addActionListener(this);
			Painel2.add(btDetalhes);

			btFinalizar = new JButton(bn.getString("Controle.Funcionario.Finalizar"));
			btFinalizar.addActionListener(this);
			Painel2.add(btFinalizar);

			Painel.add(Pbuscar, BorderLayout.NORTH);

			jtTabela = new JTable(linhas, colunas);

			scroll = new JScrollPane(jtTabela);

			Painel.add(scroll, BorderLayout.CENTER);

			setSize(700, 383);
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		KeyListener keyListener = 
				new KeyListener() {
			public void keyPressed(KeyEvent keyEvent) {
				String t=tfBuscar.getText();
				printIt(t, keyEvent);
				tfBuscar.requestFocus();
			}

			public void keyReleased(KeyEvent keyEvent) {
				String t=tfBuscar.getText();
				printIt(t, keyEvent);
				tfBuscar.requestFocus();
			}

			public void keyTyped(KeyEvent keyEvent) {
				String t=tfBuscar.getText();
				printIt(t, keyEvent);
				tfBuscar.requestFocus();
			}

			private void printIt(String title, KeyEvent keyEvent) {
				try
				{
					// obtem conexao com o banco
					control.AcessoBD bd = new control.AcessoBD();
					conn = bd.obtemConexao();

					// *** IMPORTANTE ***: Força o uso de transação.
					conn.setAutoCommit(false);

					String[] colunas2 = {bn.getString("Controle.Funcionario.id"), bn.getString("Controle.Funcionario.nome"), bn.getString("Controle.Funcionario.rg"), bn.getString("Controle.Funcionario.cpf"), bn.getString("Controle.Funcionario.nascimento")};  
					lfunc=null;
					lfunc=f.BuscarControleFuncionario(title,conn);

					Object linhas2[][] = new Object[lfunc.size()][5];   

					for(int i=0;i<lfunc.size();i++)
					{
						for(int j=0;j<5;j++)
						{
							linhas2[i][j]=lfunc.get(i).Campos(j);         
						}
					}

					jtTabela.setVisible(false);
					scroll.setVisible(false);
					Painel.add(Pbuscar, BorderLayout.NORTH);

					jtTabela2 = new JTable(linhas2, colunas2);

					scroll2 = new JScrollPane(jtTabela2);   
					Painel.add(scroll2, BorderLayout.CENTER);
					setVisible(true);
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
		};
		tfBuscar.addKeyListener(keyListener);

	}


	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == btAdicionar) 
		{  
			dispose(); 
			new CadastroUsuarioGUI(getIndice());
		}

		if (event.getSource() == btDetalhes) 
		{   
			dispose();
			new DetalhesUsuarioIdGUI(getIndice());
		}

		if (event.getSource() == btAlterar) 
		{   
			dispose();
			new AlterarUsuarioIdGUI(getIndice());
		}

		if(event.getSource() == btRemover)
		{
			dispose();
			new RemoverUsuarioGUI(getIndice());
		}

		if (event.getSource() == btFinalizar) 
		{   
			dispose();
			new InicialGUI(getIndice());
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
		new ControleDeFuncionariosGUI(3);
	}
}