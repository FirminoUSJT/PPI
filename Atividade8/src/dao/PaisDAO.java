package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;
import model.Usuario;

public class PaisDAO {


	public int criar(Pais Pais) {
		String sqlInsert = "INSERT INTO Pais(nome, populacao, area) VALUES(?,?,?)";

		try (Connection conn = connection.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, Pais.getNome());
			stm.setLong(2, Pais.getPopulacao());
			stm.setDouble(3, Pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
					ResultSet rs = stm2.executeQuery();) { 
				if (rs.next()) {
					Pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Pais.getId();
	}

// READ //
	public Pais carregar(int id) {
		String sqlSelect = "SELECT nome, populacao, area From Pais WHERE Pais.id = ?";
		Pais Pais = new Pais();
		Pais.setId(id);
		try (Connection conn = connection.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Pais.setNome(rs.getString("nome"));
					Pais.setPopulacao(rs.getLong("populacao"));
					Pais.setArea(rs.getDouble("area"));
				} else {
					Pais.setId(0);
					Pais.setNome(null);
					Pais.setPopulacao(0);
					Pais.setArea(0);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return Pais;
	}



	public void atualizar(Pais Pais) {
		String sqlUpdate = "UPDATE Pais SET nome=?, populacao=?, area=? WHERE id=?";
		
		try (Connection conn = connection.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, Pais.getNome());
			stm.setLong(2, Pais.getPopulacao());
			stm.setDouble(3, Pais.getArea());
			stm.setInt(4, Pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void excluir(int id) {
		String sqlDelete = "DELETE FROM Pais WHERE id=?";

		try (Connection conn = connection.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	public void menorArea(Pais Pais) {
		String sqlMenor = "SELECT  nome, area FROM Pais WHERE area = (select MIN(area) FROM Pais)LIMIT 1";

		try (Connection conn = connection.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Pais.setNome(rs.getString("nome"));
					Pais.setArea(rs.getDouble("area"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}

	public long maiorPopulacao(Long l) {
		String sqlMaior = "SELECT  nome, populacao FROM Pais WHERE populacao = (select Max(populacao) FROM Pais)";

		try (Connection conn = connection.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaior);) {
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

	public Pais[] vet3Paises() {
		
		Pais Pais = null;
		Pais[] vetor = new Pais[3];
		int i = 0;

		String sqltres = "SELECT * FROM Pais LIMIT 3";

		
		try (Connection conn = connection.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqltres);) {
			 
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
					

				
				Pais = new Pais(id,nome,populacao,area);
				vetor[i++] = Pais;
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
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area From paises.pais";
		Pais paises;

		try (Connection conn = connection.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				paises = new Pais();
				paises.setId(rs.getInt("id"));
				paises.setNome(rs.getString("nome"));
				paises.setPopulacao(rs.getLong("populacao"));
				paises.setArea(rs.getDouble("area"));
				lista.add(paises);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	

	public ArrayList<Pais> listarTodos(String chave) {
		Pais paises;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area From paises.pais where upper(nome) like ?";

		try (Connection conn = connection.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					paises = new Pais();
					paises.setId(rs.getInt("id"));
					paises.setNome(rs.getString("nome"));
					paises.setPopulacao(rs.getLong("populacao"));
					paises.setArea(rs.getDouble("area"));
					lista.add(paises);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	
	
	public Usuario logar(String user) {
		String sqlSelect = "SELECT *  From usuarios WHERE usuarios.login= ?";
		Usuario usuario = new Usuario();
		usuario.setLogin(user);
		try (Connection conn = connection.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, usuario.getLogin());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSenha(rs.getString("senha"));
					System.out.println(usuario.getId());
					System.out.println(usuario.getNome());
					System.out.println(usuario.getLogin());
					System.out.println(usuario.getSenha());
				} else {
					usuario.setId(0);
					usuario.setLogin(null);
					usuario.setNome(null);
					usuario.setSenha(null);
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return usuario;
	}
}
