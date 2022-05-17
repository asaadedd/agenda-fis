package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.echipa4.agenda.Model.Alarma;

public class AlarmaDao {
	private static final String
	INSERT = "INSERT INTO alarma (minutePornire, recurenta) VALUES (?, ?)";
	private static final String
	UPDATE = "UPDATE alarma SET minutePornire = ?, recurenta = ? WHERE id = ?";
	private static final String
	DELETE = "DELETE FROM alarma WHERE id = ?";
	private static final String
	FIND_ID = "SELECT * FROM alarma WHERE id = ?";
	
	private static AlarmaDao alarmaDaoInstance = null;
	
	public static AlarmaDao getInstance() {
		if (alarmaDaoInstance == null) {
			alarmaDaoInstance = new AlarmaDao();
		}
		
		return alarmaDaoInstance;
	}
	
	
	public Alarma insert(Alarma alarma) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, alarma.getMinutePornire());
		pstmt.setInt(2, alarma.getRecurenta());

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong(1);
		alarma.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return alarma;
	}
	
	public Alarma update(Alarma alarma) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, alarma.getMinutePornire());
		pstmt.setInt(2, alarma.getRecurenta());
		pstmt.setLong(3, alarma.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
		
		return alarma;
	}
	
	public void delete(Alarma alarma) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, alarma.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	public Alarma getById(long id) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(FIND_ID, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, id);
		Alarma alarma = null;

		ResultSet rset = pstmt.executeQuery();
		if (rset.next()) {
			alarma = createAlarma(rset);
		}
		pstmt.close();
		c.close();
		
		return alarma;
	}
	
	private Alarma createAlarma(ResultSet rset) throws SQLException {
		Alarma alarma = new Alarma();
		
		alarma.setId(rset.getLong("id"));
		alarma.setMinutePornire(rset.getInt("minutePornire"));
		alarma.setRecurenta(rset.getInt("recurenta"));
		
		return alarma;
	}
}
