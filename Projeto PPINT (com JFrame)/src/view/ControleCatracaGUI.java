package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class ControleCatracaGUI extends JFrame implements ActionListener
{
	private int Indice;
	private JPanel Painel, Painel2, Pbuscar;;
	private JTextField tfBuscar;
	private JButton btFinalizar;
	private JTable jtTabela, jtTabela2;;
	private ResourceBundle bn = null;
	private JScrollPane scroll, scroll2;
	private ArrayList<model.ControleAcesso> lc = new ArrayList<model.ControleAcesso>(); 
	model.ControleAcesso cl = new model.ControleAcesso();
	control.ControleAcessoDAO cDAO = new control.ControleAcessoDAO();
	Connection conn = null; 
	public void setInternacionaliza(int indice)
	{
		bn = new idiomas.Internacionalizacao().troca(indice);
		setTitle(bn.getString("Controle.Catraca.titulo"));
		tfBuscar.setText(bn.getString("Controle.Catraca.Buscar"));
		btFinalizar.setText(bn.getString("Controle.Catraca.Finalizar"));
	}

	public ControleCatracaGUI(int indice) 
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


			String[] colunas = {bn.getString("Controle.Catraca.id"), bn.getString("Controle.Catraca.usuario"), bn.getString("Controle.Catraca.adicionado")};

			lc=cDAO.SelecionarTudo(conn);

			Object linhas[][] = new Object[lc.size()][6];   

			for(int i=0;i<lc.size();i++)
			{
				for(int j=0;j<6;j++)
				{
					linhas[i][j]=lc.get(i).Campos(j);
				}
			}


			setTitle(bn.getString("Controle.Catraca.titulo"));

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

			btFinalizar = new JButton(bn.getString("Controle.Catraca.Finalizar"));
			btFinalizar.addActionListener(this);
			Painel2.add(btFinalizar);
			Painel.add(Pbuscar, BorderLayout.NORTH);
			jtTabela = new JTable(linhas, colunas);			
			scroll = new JScrollPane(jtTabela);

			Painel.add(scroll, BorderLayout.CENTER);

			setSize(670, 364);
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

					String[] colunas2 = {bn.getString("Controle.Catraca.id"), bn.getString("Controle.Catraca.usuario"), bn.getString("Controle.Catraca.adicionado")};

					lc=null;
					lc=cDAO.BuscarControleAcesso(title,conn);

					Object linhas2[][] = new Object[lc.size()][3];   

					for(int i=0;i<lc.size();i++)
					{
						for(int j=0;j<3;j++)
						{
							linhas2[i][j]=lc.get(i).Campos(j);         
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


	public static void main(String[] args)
	{
		new ControleCatracaGUI(3);
	}
}