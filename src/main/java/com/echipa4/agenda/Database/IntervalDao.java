package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.echipa4.agenda.Model.Interval;

public class IntervalDao {
	private static final String
	INSERT = "INSERT INTO interval (dataInceput, dataSfarsit) VALUES (?, ?)";
	
	private static IntervalDao intervalDaoInstance = null;
	
	public static IntervalDao getInstance() {
		if (intervalDaoInstance == null) {
			intervalDaoInstance = new IntervalDao();
		}
		
		return intervalDaoInstance;
	}
	
	
	public Interval insert(Interval interval) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setDate(1, interval.getDataInceput());
		pstmt.setDate(2, interval.getDataSfarsit());

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong("id");
		interval.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return interval;
	}
}
