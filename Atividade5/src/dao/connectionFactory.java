package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connectionFactory {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Conected!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// Obtem conex�o com o banco de dados
	public static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/Pais?user=Firmino&password=F3rnando123!@#&useSSL=false&TrustServerCertificate=True&useTimezone=true&serverTimezone=UTC");
	}

}
