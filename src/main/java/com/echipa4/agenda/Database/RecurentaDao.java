package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.echipa4.agenda.Model.Recurenta;
import main.java.com.echipa4.agenda.Model.Reperate;

public class RecurentaDao {
	private static final String
	INSERT = "INSERT INTO recurenta (recurenta, repetare) VALUES (?, ?)";
	private static final String
	UPDATE = "UPDATE recurenta SET repetare = ?, recurenta = ? WHERE id = ?";
	private static final String
	DELETE = "DELETE FROM recurenta WHERE id = ?";
	private static final String
	FIND_ID = "SELECT * FROM recurenta WHERE id = ?";
	
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
	
	public Recurenta update(Recurenta recurenta) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, recurenta.getReperate().ordinal());
		pstmt.setInt(2, recurenta.getRecurenta());
		pstmt.setLong(3, recurenta.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
		
		return recurenta;
	}
	
	public void delete(Recurenta recurenta) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, recurenta.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	public Recurenta getById(long id) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(FIND_ID, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, id);
		Recurenta recurenta = null;

		ResultSet rset = pstmt.executeQuery();
		if (rset.next()) {
			recurenta = createRecurenta(rset);
		}
		pstmt.close();
		c.close();
		
		return recurenta;
	}
	
	private Recurenta createRecurenta(ResultSet rset) throws SQLException {
		Recurenta recurenta = new Recurenta();
		
		recurenta.setId(rset.getLong("id"));
		recurenta.setRepetare(Reperate.values()[rset.getInt("repetare")]);
		recurenta.setRecurenta(rset.getInt("recurenta"));
		
		return recurenta;
	}
}
