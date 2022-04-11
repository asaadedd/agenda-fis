package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
	private static String url = "jdbc:mysql://127.0.0.1:3306/";
	private static String database = "mailsystem";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "mysql";
	private static Mysql mysql_instance = null;
	
	public static Mysql getInstance() {
		if (mysql_instance == null) {
			mysql_instance = new Mysql();
		}
		
		return mysql_instance;
	}
	
	public Connection connection = null;
	
	public Connection getConnection() {
		if (connection == null) {
			connection = openConnection();
		}
		
		return connection;
	}
	
	private Connection openConnection() {   
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url + database, user, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			System.err.println(
				"Não foi possével salvar os dados! O Banco de dados não estão respondendo!");
		}
		return null;
	}
}
