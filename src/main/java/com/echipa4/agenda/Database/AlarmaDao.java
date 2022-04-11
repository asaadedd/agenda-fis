package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.echipa4.agenda.Model.Alarma;

public class AlarmaDao {
	private static final String
	INSERT = "INSERT INTO alarma (startAlarma, recurenta) VALUES (?, ?)";
	
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

		pstmt.setDate(1, alarma.getStartAlarma());
		pstmt.setInt(2, alarma.getRecurenta());

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong("id");
		alarma.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return alarma;
	}
	
}
