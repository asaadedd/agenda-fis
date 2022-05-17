package main.java.com.echipa4.agenda.Database;

import java.awt.Color;
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
	INSERT = "INSERT INTO eveniment (titlu, descriere, idInterval, idRecurenta, culoare, idAlarma) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String
	UPDATE = "UPDATE eveniment SET title = ?, descriere = ?, idInterval = ?, idRecurenta = ?, culoare = ?, idAlarma = ? WHERE id = ?";
	private static final String
	DELETE = "DELETE FROM eveniment WHERE id = ?";
	private static final String
	FIND_ID = "SELECT * FROM eveniment WHERE id = ?";
	
	private static EvenimentDao evenimentDaoInstance = null;
	
	public static EvenimentDao getInstance() {
		if (evenimentDaoInstance == null) {
			evenimentDaoInstance = new EvenimentDao();
		}
		
		return evenimentDaoInstance;
	}	
	
	public Eveniment insert(Eveniment eveniment) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		this.insertInterval(eveniment);
		this.insertAlarma(eveniment);
		this.insertRecurenta(eveniment);
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, eveniment.getTitlu());
		pstmt.setString(2, eveniment.getDescriere());
		if (eveniment.getInterval() != null) {
			pstmt.setLong(3, eveniment.getInterval().getId());
		} else {
			pstmt.setNull(3, 0);
		}
		if (eveniment.getRecurenta() != null) {
			pstmt.setLong(4, eveniment.getRecurenta().getId());
		} else {
			pstmt.setNull(4, 0);
		}
		if (eveniment.getCuloare() != null) {
			pstmt.setInt(5, eveniment.getCuloare().getRGB());
		} else {
			pstmt.setNull(5, 0);
		}
		if (eveniment.getAlarma() != null) {
			pstmt.setLong(6, eveniment.getAlarma().getId());
		} else {
			pstmt.setNull(6, 0);
		}

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Long idGenerated = rset.getLong(1);
		eveniment.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return eveniment;
	}
	
	public Eveniment update(Eveniment eveniment) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		this.updateInterval(eveniment);
		this.updateRecurenta(eveniment);
		this.updateAlarma(eveniment);
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, eveniment.getTitlu());
		pstmt.setString(2, eveniment.getDescriere());
		pstmt.setLong(3, eveniment.getInterval().getId());
		pstmt.setLong(4, eveniment.getRecurenta().getId());
		pstmt.setString(5, eveniment.getCuloare().toString());
		pstmt.setLong(5, eveniment.getAlarma().getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
		
		return eveniment;
	}
	
	public void delete(Eveniment eveniment) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, eveniment.getId());

		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	public Eveniment getById(long id) throws SQLException {
		Connection c = Mysql.getInstance().getConnection();
		
		PreparedStatement pstmt = c.prepareStatement(FIND_ID, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setLong(1, id);
		Eveniment eveniment = null;

		ResultSet rset = pstmt.executeQuery();
		if (rset.next()) {
			eveniment = createEveniment(rset);
		}
		pstmt.close();
		c.close();
		
		return eveniment;
	}
	
	private Eveniment createEveniment(ResultSet rset) throws SQLException {
		Eveniment eveniment = new Eveniment();
		
		eveniment.setId(rset.getLong("id"));
		eveniment.setTitlu(rset.getString("title"));
		eveniment.setDescriere(rset.getString("descriere"));
		eveniment.setInterval(IntervalDao.getInstance().getById(rset.getInt("idInterval")));
		eveniment.setRecurenta(RecurentaDao.getInstance().getById(rset.getInt("idRecurenta")));
		eveniment.setCuloare(new Color(rset.getInt("culoare")));
		eveniment.setAlarma(AlarmaDao.getInstance().getById(rset.getInt("idAlarma")));
		
		return eveniment;
	}
	
	private void insertInterval(Eveniment eveniment) throws SQLException {
		if (eveniment.getInterval() == null) {
			return;
		}
		IntervalDao intervalDao = IntervalDao.getInstance();	
		
		Interval newInterval = intervalDao.insert(eveniment.getInterval());
		eveniment.setInterval(newInterval);
	}
	
	private void insertRecurenta(Eveniment eveniment) throws SQLException {
		if (eveniment.getRecurenta() == null) {
			return;
		}
		RecurentaDao recurentaDao = RecurentaDao.getInstance();
		
		Recurenta recurenta = recurentaDao.insert(eveniment.getRecurenta());
		eveniment.setRecurenta(recurenta);
	}
	
	private void insertAlarma(Eveniment eveniment) throws SQLException {
		if (eveniment.getAlarma() == null) {
			return;
		}
		AlarmaDao alarmaDao = AlarmaDao.getInstance();
		
		Alarma alarma = alarmaDao.insert(eveniment.getAlarma());
		eveniment.setAlarma(alarma);
	}
	
	
	private void updateInterval(Eveniment eveniment) throws SQLException {
		if (eveniment.getInterval() == null) {
			return;
		}
		if (eveniment.getInterval().getId() == null) {
			return;
		}
		IntervalDao intervalDao = IntervalDao.getInstance();	
		
		Interval newInterval = intervalDao.update(eveniment.getInterval());
		eveniment.setInterval(newInterval);
	}
	
	private void updateRecurenta(Eveniment eveniment) throws SQLException {
		if (eveniment.getRecurenta() == null) {
			return;
		}
		if (eveniment.getInterval().getId() == null) {
			return;
		}
		RecurentaDao recurentaDao = RecurentaDao.getInstance();
		
		Recurenta recurenta = recurentaDao.update(eveniment.getRecurenta());
		eveniment.setRecurenta(recurenta);
	}
	
	private void updateAlarma(Eveniment eveniment) throws SQLException {
		if (eveniment.getAlarma() == null) {
			return;
		}
		if (eveniment.getInterval().getId() == null) {
			return;
		}
		AlarmaDao alarmaDao = AlarmaDao.getInstance();
		
		Alarma alarma = alarmaDao.update(eveniment.getAlarma());
		eveniment.setAlarma(alarma);
	}
}
