package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Pais;



public class PaisDAO {
	

// MÉTODO CREATE //	

	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES(?,?,?)";

		try (Connection conn = connectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
					ResultSet rs = stm2.executeQuery();) { //
				if (rs.next()) {
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}

	// MÉTODO READ // 
	public Pais carregar(int id) {
		String sqlSelect = "SELECT nome, populacao, area From pais WHERE pais.id = ?";
		Pais pais = new Pais();
		pais.setId(id);
		try (Connection conn = connectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(0);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return pais;
	}

	// MÉTODO UPDATE  //

	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		
		try (Connection conn = connectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// MÉTODO DELETE//

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id=?";

		try (Connection conn = connectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// MINOR AREA //

	public void menorArea(Pais pais) {
		String sqlMenor = "SELECT  nome, area FROM pais WHERE area = (select MIN(area) FROM pais)LIMIT 1";

		try (Connection conn = connectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setArea(rs.getDouble("area"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}

	// LARGER AREA //
	public long maiorPopulacao(Long l) {
		String sqlMaior = "SELECT  nome, populacao FROM pais WHERE populacao = (select Max(populacao) FROM pais)";

		try (Connection conn = connectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					rs.getString("nome");
					l = rs.getLong("populacao");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	// SELECT 3 COUNTRIES //

public Pais[] trespais() {
		
		Pais pais = null;
		Pais[] vetor = new Pais[3];
		int i = 0;

		String sqltres = "SELECT * FROM pais LIMIT 3";

		
		try (Connection conn = connectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqltres);) {
			 
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
					
					
		
				
				pais = new Pais(id,nome,populacao,area);
				vetor[i++] = pais;
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return vetor;
		}
		
		public ArrayList<Pais> listarTodos() {
			ArrayList<Pais> pais = new ArrayList<>();
			String sqlSelect = "SELECT id, nome, populacao, area FROM pais.pais";
	

			try (Connection conn = connectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);
					ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						Pais pais = new Pais();
						pais.setId(rs.getInt("id"));
						pais.setNome(rs.getString("nome"));
						pais.setPopulacao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
						pais.add(pais);
					} 
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		return pais;
	}
}


 



