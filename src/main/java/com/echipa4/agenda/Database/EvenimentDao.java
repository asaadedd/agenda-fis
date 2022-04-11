package main.java.com.echipa4.agenda.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.echipa4.agenda.Model.Alarma;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;
import main.java.com.echipa4.agenda.Model.Recurenta;

public class EvenimentDao {
	private static final String
	INSERT = "INSERT INTO eveniment (title, descriere, idInterval, idRecurenta, culoare, idAlarma) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String
	ALL = "SELECT * FROM eveniment";

	private static final String 
	FIND_BY_TITLE = "SELECT * FROM eveniment WHERE title = ?";
	
	private static final String
	FIND_BY_ID = "SELECT * FROM eveniment WHERE id = ?";

	private static final String 
	DELETE = "DELETE FROM eveniment where id = ?";
	
	private static EvenimentDao evenimentDaoInstance = null;
	
	public static EvenimentDao getInstance() {
		if (evenimentDaoInstance == null) {
			evenimentDaoInstance = new EvenimentDao();
		}
		
		return evenimentDaoInstance;
	}
	
	
	public Eveniment insert(Eveniment eveniment) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		insertInterval(eveniment);
		insertRecurenta(eveniment);
		insertAlarma(eveniment);
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, eveniment.getTitlu());
		pstmt.setString(2, eveniment.getDescriere());
		pstmt.setLong(3, eveniment.getInterval().getId());
		pstmt.setLong(4, eveniment.getRecurenta().getId());
		pstmt.setString(5, eveniment.getCuloare().toString());
		pstmt.setLong(5, eveniment.getAlarma().getId());

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong("id");
		eveniment.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return eveniment;
	}
	
	private void insertInterval(Eveniment eveniment) throws SQLException {	
		IntervalDao intervalDao = IntervalDao.getInstance();	
		
		Interval newInterval = intervalDao.insert(eveniment.getInterval());
		eveniment.setInterval(newInterval);
	}
	
	private void insertRecurenta(Eveniment eveniment) throws SQLException {
		RecurentaDao recurentaDao = RecurentaDao.getInstance();
		
		Recurenta recurenta = recurentaDao.insert(eveniment.getRecurenta());
		eveniment.setRecurenta(recurenta);
	}
	
	private void insertAlarma(Eveniment eveniment) throws SQLException {
		AlarmaDao alarmaDao = AlarmaDao.getInstance();
		
		Alarma alarma = alarmaDao.insert(eveniment.getAlarma());
		eveniment.setAlarma(alarma);
	}
}
