package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Empresa;

public class EmpresaDAO {
	public int criar(Empresa empresa) {
		String sqlInsert = "INSERT INTO empresa(nome, conjunto, Cnpj, RazaoSocial, HorarioFuncionamento, autorizacao, temperatura, horarioarcondicionado, cancelado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, empresa.getNome());
			stm.setString(2, empresa.getConjunto());
			stm.setString(3, empresa.getCnpj());
			stm.setString(4, empresa.getRazaoSocial());
			stm.setString(5, empresa.getHorarioFuncionamento());
			stm.setString(6, empresa.getAutorizacao());
			stm.setString(7, empresa.getTemperatura());
			stm.setString(8, empresa.getHorarioArCondicionado());
			stm.setString(9, empresa.getCancelado());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					empresa.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresa.getId();
	}

	public void atualizar(Empresa empresa) {
		String sqlUpdate = "UPDATE empresa SET nome=?, conjunto=?, cnpj=?, razaoSocial=?, "
				+ "horarioFuncionamento=?, autorizacao=?, temperatura=?, "
				+ "horarioarcondicionado=?, cancelado=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, empresa.getNome());
			stm.setString(2, empresa.getConjunto());
			stm.setString(3, empresa.getCnpj());
			stm.setString(4, empresa.getRazaoSocial());
			stm.setString(5, empresa.getHorarioFuncionamento());
			stm.setString(6, empresa.getAutorizacao());
			stm.setString(7, empresa.getTemperatura());
			stm.setString(8, empresa.getHorarioArCondicionado());
			stm.setString(9, empresa.getCancelado());
			stm.setInt(10, empresa.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM empresa WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Empresa carregar(int id) {
		Empresa empresa = new Empresa();
		empresa.setId(id);
		String sqlSelect = "SELECT nome, conjunto, Cnpj, RazaoSocial, HorarioFuncionamento,"
				+ " autorizacao, temperatura, horarioarcondicionado, cancelado "
				+ "FROM empresa WHERE empresa.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, empresa.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					empresa.setNome(rs.getString("nome"));
					empresa.setConjunto(rs.getString("conjunto"));
					empresa.setCnpj(rs.getString("Cnpj"));
					empresa.setRazaoSocial(rs.getString("RazaoSocial"));
					empresa.setHorarioFuncionamento(rs.getString("horarioFuncionamento"));
					empresa.setAutorizacao(rs.getString("autorizacao"));
					empresa.setTemperatura(rs.getString("temperatura"));
					empresa.setHorarioArCondicionado(rs.getString("horarioarcondicionado"));
					empresa.setCancelado(rs.getString("cancelado"));
				} else {
					empresa.setId(-1);
					empresa.setNome(null);
					empresa.setConjunto(null);
					empresa.setCnpj(null);
					empresa.setRazaoSocial(null);
					empresa.setHorarioFuncionamento(null);
					empresa.setAutorizacao(null);
					empresa.setTemperatura(null);
					empresa.setHorarioArCondicionado(null);
					empresa.setCancelado(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return empresa;
	}

}
