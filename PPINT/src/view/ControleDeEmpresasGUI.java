package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;


@SuppressWarnings("serial")
public class ControleDeEmpresasGUI extends JFrame implements ActionListener
{
	private int Indice;
	private JPanel Painel, Painel2, Pbuscar;
	private JTextField tfBuscar;
	private JButton btAdicionar, btAlterar, btRemover, btDetalhes, btFinalizar;
	private JTable jtTabela, jtTabela2;
	private ResourceBundle bn = null;
	private JScrollPane scroll, scroll2;
	@SuppressWarnings("unused")
	private TextArea taEmpresa;
	@SuppressWarnings("unused")
	private String dados = "";
	private ArrayList<model.Empresa> lem = new ArrayList<model.Empresa>(); 
	private ArrayList<model.Empresa> lemBusca = new ArrayList<model.Empresa>(); 
	private int cont=0;
	Connection conn = null;


	model.Empresa em = new model.Empresa();
	control.EmpresaDAO emDAO = new control.EmpresaDAO();
	public void setInternacionaliza(int indice)
	{
		bn = new idiomas.Internacionalizacao().troca(indice);
		setTitle(bn.getString("Controle.Empresas.titulo"));
		tfBuscar.setText(bn.getString("Controle.Empresas.Buscar"));
		btAdicionar.setText(bn.getString("Controle.Empresas.Adicionar"));
		btAlterar.setText(bn.getString("Controle.Empresas.Alterar"));
		btRemover.setText(bn.getString("Controle.Empresas.Remover"));
		btFinalizar.setText(bn.getString("Controle.Empresas.Finalizar"));
	}

	public ControleDeEmpresasGUI(int indice) 
	{
		setIndice(indice);
		bn = new idiomas.Internacionalizacao().troca(indice);

		try
		{
			// obtem conexao com o banco
			control.AcessoBD bd = new control.AcessoBD();
			conn = bd.obtemConexao();

			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);

			String[] colunas = {bn.getString("Controle.Empresas.id"), bn.getString("Controle.Empresas.nome"), bn.getString("Controle.Empresas.cojunto"), bn.getString("Controle.Empresas.temperaturas"), bn.getString("Controle.Empresas.autorizacao"), bn.getString("Controle.Empresas.horario")};

			lem=emDAO.SelecionarTudo(conn);

			for(int i=0;i<lem.size();i++){
				cont++;
			}
			Object linhas[][] = new Object[cont][6];   

			for(int i=0;i<lem.size();i++)
			{
				for(int j=0;j<6;j++)
				{
					linhas[i][j]=lem.get(i).Campos(j);
				}
			}

			setTitle(bn.getString("Controle.Empresas.titulo"));

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

			btAdicionar = new JButton(bn.getString("Controle.Empresas.Adicionar"));
			btAdicionar.addActionListener(this);
			Painel2.add(btAdicionar);

			btAlterar = new JButton(bn.getString("Controle.Empresas.Alterar"));
			btAlterar.addActionListener(this);
			Painel2.add(btAlterar);

			btRemover = new JButton(bn.getString("Controle.Empresas.Remover"));
			btRemover.addActionListener(this);
			Painel2.add(btRemover);

			btDetalhes = new JButton(bn.getString("Controle.Funcionario.Detalhes"));
			btDetalhes.addActionListener(this);
			Painel2.add(btDetalhes);

			btFinalizar = new JButton(bn.getString("Controle.Empresas.Finalizar"));
			btFinalizar.addActionListener(this);
			Painel2.add(btFinalizar);


			Painel.add(Pbuscar, BorderLayout.NORTH);

			jtTabela = new JTable(linhas, colunas);

			scroll = new JScrollPane(jtTabela);

			Painel.add(scroll, BorderLayout.CENTER);
			setSize(1110, 364);
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

					String[] colunas2 = {bn.getString("Controle.Empresas.id"), bn.getString("Controle.Empresas.nome"), bn.getString("Controle.Empresas.cojunto"), bn.getString("Controle.Empresas.temperaturas"), bn.getString("Controle.Empresas.autorizacao"), bn.getString("Controle.Empresas.horario")};
					lemBusca=null;
					lemBusca=emDAO.BuscarControleEmpresa(title,conn);

					Object linhas2[][] = new Object[lemBusca.size()][6];   

					for(int i=0;i<lemBusca.size();i++)
					{
						for(int j=0;j<6;j++)
						{
							linhas2[i][j]=lemBusca.get(i).Campos(j);         
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
			new CadastroEmpresaGUI(Indice);
		}

		if (event.getSource() == btDetalhes) 
		{   
			dispose();
			new DetalhesEmpresaIdGUI(getIndice());
		}

		if(event.getSource() == btRemover)
		{
			dispose();
			new RemoverEmpresaGUI(getIndice());
		}

		if (event.getSource() == btAlterar) 
		{   
			dispose();
			new AlterarEmpresaIdGUI(Indice);
		}

		if (event.getSource() == btFinalizar) 
		{   
			dispose();
			new InicialGUI(Indice);
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
		new ControleDeEmpresasGUI(3);
	}
}