package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class ControleAcessoDAO
{
	model.ControleAcesso c = new model.ControleAcesso();
	public ControleAcessoDAO(){


	}


	// Selecionar ControleAcesso especifico
	public ArrayList<model.ControleAcesso> SelecionarControleAcesso(String n,Connection conn)
	{
		String sqlSelect = "SELECT id FROM Funcionario where login=? and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.ControleAcesso> lc = new ArrayList<model.ControleAcesso>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, n);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.ControleAcesso c = new model.ControleAcesso();
				c.setidFuncionario(rs.getInt(1));
				lc.add(c);
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
		return (lc);
	}

	// Selecionar ControleAcesso especifico
	public ArrayList<model.ControleAcesso> BuscarControleAcesso(String n,Connection conn)
	{
		String sqlSelect = "SELECT * FROM ControleAcesso ca inner join Funcionario f on ca.id_Funcionario=f.id where f.nome like '"+n+"%' and f.cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.ControleAcesso> lc = new ArrayList<model.ControleAcesso>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.ControleAcesso c = new model.ControleAcesso();
				c.setidControleAcesso(rs.getInt(1));
				c.setHorarioAcesso(rs.getString(2));
				c.setidFuncionario(rs.getInt(3));
				c.setNomeFuncionario(rs.getString(5));
				lc.add(c);
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
		return (lc);
	}


	// Selecionar Tudo 
	public ArrayList<model.ControleAcesso> SelecionarTudo(Connection conn)
	{
		String sqlSelect = "SELECT * FROM ControleAcesso ca inner join Funcionario f on ca.id_Funcionario=f.id where f.cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.ControleAcesso> lc = new ArrayList<model.ControleAcesso>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.ControleAcesso c = new model.ControleAcesso();
				c.setidControleAcesso(rs.getInt(1));
				c.setHorarioAcesso(rs.getString(2));
				c.setidFuncionario(rs.getInt(3));
				c.setNomeFuncionario(rs.getString(5));
				lc.add(c);
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
		return (lc);
	}



	// Incluir ControleAcesso
	public void incluir(Connection conn,model.ControleAcesso ca)
	{
		String sqlInsert = "INSERT INTO ControleAcesso(HorarioAcesso,id_Funcionario) VALUES (?,?)";
		PreparedStatement stm = null;
		try
		{
			//
			// Inclusao dos dados na tabela Funcionario
			//
			stm = conn.prepareStatement(sqlInsert);
			stm.setString(1, ca.getHorarioAcesso());
			stm.setInt(2, ca.getidFuncionario());
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


	// Selecionar ControleAcesso e tudo da funcionario Junto
	public ArrayList<model.ControleAcesso> SelecionarJuntoControleAcesso(Connection conn)
	{
		String sqlSelect = "select * from ControleAcesso ca INNER JOIN Funcionario f on ca.id_Funcionario=f.id";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.ControleAcesso> lc = new ArrayList<model.ControleAcesso>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.ControleAcesso c = new model.ControleAcesso();
				c.setidControleAcesso(rs.getInt(1));
				c.setHorarioAcesso(rs.getString(2));
				c.setNomeFuncionario(rs.getString(4));
				lc.add(c);
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
		return (lc);
	}


	// update ControleAcesso
	public void alterar(Connection conn,model.ControleAcesso ca)
	{
		String sqlUpdate = "UPDATE ControleAcesso SET  HorarioAcesso=?,id_Funcionario=?  WHERE id=?";
		PreparedStatement stm = null;
		try
		{
			//
			// update dos dados na tabela ControleArCondicionado
			//
			stm = conn.prepareStatement(sqlUpdate);
			stm.setString(1, ca.getHorarioAcesso());
			stm.setInt(2, ca.getidFuncionario());
			stm.setInt(11, ca.getidControleAcesso());
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

	public void excluir(Connection conn,model.ControleAcesso ca)
	{
		String sqlDelete = "DELETE FROM ControleAcesso WHERE id = ?";
		PreparedStatement stm = null;
		try
		{
			stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, ca.getidControleAcesso());
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