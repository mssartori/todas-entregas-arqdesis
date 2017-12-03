package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EmpresaDAO
{  
	model.Empresa e = new model.Empresa();

	public EmpresaDAO()
	{   
	}

	// Selecionar Empresa Especifico (para Detalhes e editar) 
	public ArrayList<model.Empresa> SelecionarEspecifico(int codigo, Connection conn)
	{
		String sqlSelect = "SELECT * FROM Empresa WHERE id = ? and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Empresa> le = new ArrayList<model.Empresa>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, codigo);
			rs = stm.executeQuery();

			while (rs.next())
			{
				e.setidEmpresa(rs.getInt(1));
				e.setNome(rs.getString(2));
				e.setConjunto(rs.getString(3));
				e.setCnpj(rs.getString(4));
				e.setRazaoSocial(rs.getString(5));
				e.setHorarioFuncionamento(rs.getString(6));
				e.setAutorizacao(rs.getString(7));
				e.setTemperatura(rs.getString(8));
				e.setHorarioArCondicionado(rs.getString(9));
				le.add(e);
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return(le);
	}

	// Selecionar Empresa 
	public ArrayList<model.Empresa> SelecionarTudo(Connection conn)
	{
		String sqlSelect = "SELECT * FROM Empresa where cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Empresa> le = new ArrayList<model.Empresa>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.Empresa e = new model.Empresa();
				e.setidEmpresa(rs.getInt(1));
				e.setNome(rs.getString(2));
				e.setConjunto(rs.getString(3));
				e.setCnpj(rs.getString(4));
				e.setRazaoSocial(rs.getString(5));
				e.setHorarioFuncionamento(rs.getString(6));
				e.setAutorizacao(rs.getString(7));
				e.setTemperatura(rs.getString(8));
				e.setHorarioArCondicionado(rs.getString(9));;

				le.add(e);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return (le);
	}

	// Selecionar Empresa pelo nome 
	public ArrayList<model.Empresa> BuscarEmpresa(String n, Connection conn)
	{
		String sqlSelect = "SELECT * FROM Empresa WHERE nome like ? and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Empresa> le = new ArrayList<model.Empresa>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, n);
			rs = stm.executeQuery();

			while (rs.next())
			{
				e.setidEmpresa(rs.getInt(1));
				e.setNome(rs.getString(2));
				e.setConjunto(rs.getString(3));
				e.setCnpj(rs.getString(4));
				e.setRazaoSocial(rs.getString(5));
				e.setHorarioFuncionamento(rs.getString(6));
				e.setAutorizacao(rs.getString(7));
				e.setTemperatura(rs.getString(8));
				e.setHorarioArCondicionado(rs.getString(9));
				le.add(e);
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return(le);
	}



	public ArrayList<model.Empresa> BuscarControleEmpresa(String n, Connection conn)
	{
		ArrayList<model.Empresa> le = new ArrayList<model.Empresa>();
		String sqlSelect = "SELECT * FROM Empresa WHERE nome like '"+n+"%' and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next())
			{
				model.Empresa e = new model.Empresa();
				e.setidEmpresa(rs.getInt(1));
				e.setNome(rs.getString(2));
				e.setConjunto(rs.getString(3));
				e.setCnpj(rs.getString(4));
				e.setRazaoSocial(rs.getString(5));
				e.setHorarioFuncionamento(rs.getString(6));
				e.setAutorizacao(rs.getString(7));
				e.setTemperatura(rs.getString(8));
				e.setHorarioArCondicionado(rs.getString(9));
				le.add(e);
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return(le);
	}


	// Incluir Empresa
	public void incluir(Connection conn,model.Empresa em)
	{
		String sqlInsert = "INSERT INTO Empresa(nome,conjunto,Cnpj,RazaoSocial,HorarioFuncionamento,autorizacao,temperatura,horarioarcondicionado,cancelado) VALUES (?, ?, ?, ?, ?, ?, ?, ?,0)";
		PreparedStatement stm = null;
		try
		{
			//
			// Inclusao dos dados na tabela Funcionario
			//
			stm = conn.prepareStatement(sqlInsert);
			stm.setString(1, em.getNome());
			stm.setString(2, em.getConjunto());
			stm.setString(3, em.getCnpj());
			stm.setString(4, em.getRazaoSocial());
			stm.setString(5, em.getHorarioFuncionamento());
			stm.setString(6, em.getAutorizacao());
			stm.setString(7, em.getTemperatura());
			stm.setString(8, em.getHorarioArCondicionado());
			stm.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}   

	// update Funcionario
	public void alterar(Connection conn,model.Empresa em)
	{      
		String sqlUpdate = "UPDATE Empresa SET  nome=?,conjunto=?, Cnpj=?, RazaoSocial=?, HorarioFuncionamento=?, autorizacao=?, temperatura=?, horarioarcondicionado=?  WHERE id=?";
		PreparedStatement stm = null;

		try
		{
			//
			// update dos dados na tabela Funcionario
			//
			stm = conn.prepareStatement(sqlUpdate);
			stm.setString(1, em.getNome());
			stm.setString(2, em.getConjunto());
			stm.setString(3, em.getCnpj());
			stm.setString(4, em.getRazaoSocial());
			stm.setString(5, em.getHorarioFuncionamento());
			stm.setString(6, em.getAutorizacao());
			stm.setString(7, em.getTemperatura());
			stm.setString(8, em.getHorarioArCondicionado());
			stm.setInt(9, em.getidEmpresa());
			stm.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

	public void excluir(Connection conn,model.Empresa em)
	{
		String sqlDelete = "UPDATE Empresa SET cancelado=1 where id = ?";
		PreparedStatement stm = null;
		try
		{
			stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, em.getidEmpresa());
			stm.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

	public int Selecionarid(int i,Connection conn)
	{
		String sqlSelect = "SELECT id FROM Empresa WHERE id=? and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		int cont=0;
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1,i);
			rs = stm.executeQuery();

			while (rs.next())
			{
				cont=1;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return(cont);
	}


	public void alterarTemperatura(Connection conn,model.Empresa em)
	{
		String sqlDelete = "UPDATE Empresa SET Temperatura=? where id = ?";
		PreparedStatement stm = null;
		try
		{
			stm = conn.prepareStatement(sqlDelete);
			stm.setString(1, em.getTemperatura());
			stm.setInt(2, em.getidEmpresa());
			stm.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				System.out.print(e1.getStackTrace());
			}
		}
		finally
		{
			if (stm != null)
			{
				try
				{
					stm.close();
				}
				catch (SQLException e1)
				{
					System.out.print(e1.getStackTrace());
				}
			}
		}
	} 
}