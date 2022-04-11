package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.echipa4.agenda.Model.Recurenta;

public class RecurentaDao {
	private static final String
	INSERT = "INSERT INTO recurenta (recurenta, repetare) VALUES (?, ?)";
	
	private static RecurentaDao recurentaDaoInstance = null;
	
	public static RecurentaDao getInstance() {
		if (recurentaDaoInstance == null) {
			recurentaDaoInstance = new RecurentaDao();
		}
		
		return recurentaDaoInstance;
	}
	
	
	public Recurenta insert(Recurenta recurenta) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, recurenta.getRecurenta());
		pstmt.setInt(2, recurenta.getReperate().ordinal());

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong("id");
		recurenta.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return recurenta;
	}

}
