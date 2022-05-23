package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import main.java.com.echipa4.agenda.Model.Interval;

public class IntervalDao {
	private static final String
	INSERT = "INSERT INTO agenda.interval (dataInceput, dataSfarsit) VALUES (?, ?)";
	private static final String
	UPDATE = "UPDATE agenda.interval SET dataInceput = ?, dataSfarsit = ? WHERE id = ?";
	private static final String
	DELETE = "DELETE FROM agenda.interval WHERE id = ?";
	private static final String
	FIND_ID = "SELECT * FROM agenda.interval WHERE id = ?";
	
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

		pstmt.setTimestamp(1, new Timestamp(interval.getDataInceput().getTime()));
		pstmt.setTimestamp(2, new Timestamp(interval.getDataSfarsit().getTime()));

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong(1);
		interval.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return interval;
	}
	
	public Interval update(Interval interval) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setTimestamp(1, new Timestamp(interval.getDataInceput().getTime()));
		pstmt.setTimestamp(2, new Timestamp(interval.getDataSfarsit().getTime()));
		pstmt.setLong(3, interval.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
		
		return interval;
	}
	
	public void delete(Interval interval) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, interval.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	public Interval getById(long id) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(FIND_ID, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, id);
		Interval interval = null;

		ResultSet rset = pstmt.executeQuery();
		if (rset.next()) {
			interval = createInterval(rset);
		}
		pstmt.close();
		c.close();
		
		return interval;
	}
	
	private Interval createInterval(ResultSet rset) throws SQLException {
		Interval interval = new Interval();
		
		interval.setId(rset.getLong("id"));
		interval.setDataInceput(new Date(rset.getTimestamp("dataInceput").getTime()));
		interval.setDataSfarsit(new Date(rset.getTimestamp("dataSfarsit").getTime()));
		
		return interval;
	}
}
