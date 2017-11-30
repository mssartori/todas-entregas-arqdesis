package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class FuncionarioDAO
{
	model.Funcionario f = new model.Funcionario();
	public FuncionarioDAO(){
	}


	// Selecionar Empresa 
	public ArrayList<model.Funcionario> SelecionarEmpresa(int codigo,Connection conn)
	{
		String sqlSelect = "SELECT * FROM Empresa where id=? and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Funcionario> lf = new ArrayList<model.Funcionario>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, codigo);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.Funcionario f = new model.Funcionario();
				f.setidEmpresa(rs.getInt(1));
				f.setEmpresa(rs.getString(2));
				lf.add(f);
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
		return (lf);
	}


	// Selecionar Funcionario Especifico (para Detalhes e editar) 
	public ArrayList<model.Funcionario> SelecionarEspecifico(int codigo, Connection conn)
	{
		String sqlSelect = "SELECT * FROM Funcionario WHERE id = ? and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Funcionario> lf = new ArrayList<model.Funcionario>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, codigo);
			rs = stm.executeQuery();

			while (rs.next())
			{
				f.setidFuncionario(rs.getInt(1));
				f.setNome(rs.getString(2));
				f.setFuncao(rs.getString(3));
				f.setDataNasc(rs.getString(4));
				f.setRG(rs.getString(5));
				f.setCPF(rs.getString(6));
				f.setLogin(rs.getString(7));
				f.setSenha(rs.getString(8));
				f.setAcessoCatraca(rs.getString(9));
				f.setAcessoSistema(rs.getBoolean(10));
				f.setControleAr(rs.getBoolean(11));
				f.setidEmpresa(rs.getInt(12));
				lf.add(f);
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
		return(lf);
	}

	// Selecionar Funcionario 
	public ArrayList<model.Funcionario> SelecionarTudo(Connection conn)
	{
		String sqlSelect = "SELECT * FROM Funcionario where cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Funcionario> lf = new ArrayList<model.Funcionario>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.Funcionario f = new model.Funcionario();
				f.setidFuncionario(rs.getInt(1));
				f.setNome(rs.getString(2));
				f.setFuncao(rs.getString(3));
				f.setDataNasc(rs.getString(4));
				f.setRG(rs.getString(5));
				f.setCPF(rs.getString(6));
				f.setLogin(rs.getString(7));
				f.setSenha(rs.getString(8));
				f.setAcessoCatraca(rs.getString(9));
				f.setAcessoSistema(rs.getBoolean(10));
				f.setControleAr(rs.getBoolean(11));
				f.setidEmpresa(rs.getInt(12));
				lf.add(f);
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
		return (lf);
	}

	// Incluir Funcionario
	public void incluir(Connection conn,model.Funcionario func)
	{
		String sqlInsert = "INSERT INTO Funcionario(nome,funcao, datanasc, rg, cpf, login, senha, horarioAcessoCatraca,AcessoSistema,ControleAr,cancelado,id_Empresa) VALUES (?,?, ?, ?,?, ?, ?,?, ?, ?,0,?)";
		PreparedStatement stm = null;
		try
		{
			//
			// Inclusao dos dados na tabela Funcionario
			//
			stm = conn.prepareStatement(sqlInsert);
			stm.setString(1, func.getNome());
			stm.setString(2, func.getFuncao());
			stm.setString(3, func.getDataNasc());
			stm.setString(4, func.getRG());
			stm.setString(5, func.getCPF());
			stm.setString(6, func.getLogin());
			stm.setString(7, func.getSenha());
			stm.setString(8, func.getAcessoCatraca());
			stm.setBoolean(9,func.getAcessoSistema());
			stm.setBoolean(10,func.getControleAr());
			stm.setInt(11, func.getidEmpresa());
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


	// Selecionar Funcionario e nome da Empresa Junto
	public ArrayList<model.Funcionario> SelecionarEmpresaFuncionario(Connection conn)
	{
		String sqlSelect = "select * from Funcionario f INNER JOIN Empresa e on f.id_Empresa=e.id where f.cancelado=0 and e.cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Funcionario> lf = new ArrayList<model.Funcionario>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.Funcionario f = new model.Funcionario();
				f.setidFuncionario(rs.getInt(1));
				f.setNome(rs.getString(2));
				f.setFuncao(rs.getString(3));
				f.setDataNasc(rs.getString(4));
				f.setRG(rs.getString(5));
				f.setCPF(rs.getString(6));
				f.setLogin(rs.getString(7));
				f.setSenha(rs.getString(8));
				f.setAcessoCatraca(rs.getString(9));
				f.setAcessoSistema(rs.getBoolean(10));
				f.setControleAr(rs.getBoolean(11));            
				f.setidEmpresa(rs.getInt(12));
				f.setEmpresa(rs.getString(14));
				lf.add(f);
			}
			for(int i=0;i<lf.size();i++){
				System.out.println(lf.get(i).dados());
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
		return (lf);
	}


	// update Funcionario
	public void alterar(Connection conn,model.Funcionario func)
	{
		String sqlUpdate = "UPDATE Funcionario SET  nome=?,funcao=?, datanasc=?, rg=?, cpf=?, login=?, senha=?, horarioAcessoCatraca=?, AcessoSistema=?, ControleAr=?  WHERE id=?";
		PreparedStatement stm = null;
		try
		{
			//
			// update dos dados na tabela Funcionario
			//
			stm = conn.prepareStatement(sqlUpdate);
			stm.setString(1, func.getNome());
			stm.setString(2, func.getFuncao());
			stm.setString(3, func.getDataNasc());
			stm.setString(4, func.getRG());
			stm.setString(5, func.getCPF());
			stm.setString(6, func.getLogin());
			stm.setString(7, func.getSenha());
			stm.setString(8, func.getAcessoCatraca());
			stm.setBoolean(9,func.getAcessoSistema());
			stm.setBoolean(10,func.getControleAr());
			stm.setInt(11, func.getidFuncionario());
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

	public void excluir(Connection conn,model.Funcionario func)
	{
		String sqlDelete = "UPDATE Funcionario SET cancelado=1 where id = ?";
		PreparedStatement stm = null;
		try
		{
			stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, func.getidFuncionario());
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
		String sqlSelect = "SELECT id FROM Funcionario WHERE id=? and cancelado=0";
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



	public int ContarFuncionario(Connection conn)
	{
		String sqlSelect = "SELECT id FROM Funcionario WHERE cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		int cont=0;
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				cont++;
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




	public ArrayList<model.Funcionario> SelecionarNomeEmpresa(String n,Connection conn)
	{
		String sqlSelect = "select * from Funcionario f INNER JOIN Empresa e on f.id_Empresa=e.id where f.login=? and f.cancelado=0 and e.cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Funcionario> lf = new ArrayList<model.Funcionario>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1,n);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.Funcionario f = new model.Funcionario();
				f.setidFuncionario(rs.getInt(1));
				f.setNome(rs.getString(2));
				f.setFuncao(rs.getString(3));
				f.setDataNasc(rs.getString(4));
				f.setRG(rs.getString(5));
				f.setCPF(rs.getString(6));
				f.setLogin(rs.getString(7));
				f.setSenha(rs.getString(8));
				f.setAcessoCatraca(rs.getString(9));
				f.setAcessoSistema(rs.getBoolean(10));
				f.setControleAr(rs.getBoolean(11));            
				f.setidEmpresa(rs.getInt(12));
				f.setEmpresa(rs.getString(15));
				f.setTemperatura(rs.getString(21));
				lf.add(f);
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
		return (lf);
	}


	public ArrayList<model.Funcionario> BuscarControleFuncionario(String n , Connection conn)
	{
		String sqlSelect = "SELECT * FROM Funcionario WHERE nome like '"+n+"%' and cancelado=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<model.Funcionario> lf = new ArrayList<model.Funcionario>();
		try
		{
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();

			while (rs.next())
			{
				model.Funcionario f = new model.Funcionario();
				f.setidFuncionario(rs.getInt(1));
				f.setNome(rs.getString(2));
				f.setFuncao(rs.getString(3));
				f.setDataNasc(rs.getString(4));
				f.setRG(rs.getString(5));
				f.setCPF(rs.getString(6));
				f.setLogin(rs.getString(7));
				f.setSenha(rs.getString(8));
				f.setAcessoCatraca(rs.getString(9));
				f.setAcessoSistema(rs.getBoolean(10));
				f.setControleAr(rs.getBoolean(11));
				f.setidEmpresa(rs.getInt(12));
				lf.add(f);
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
		return(lf);
	}



}