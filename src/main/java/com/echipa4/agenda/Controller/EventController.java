package main.java.com.echipa4.agenda.Controller;

import java.sql.SQLException;

import main.java.com.echipa4.agenda.Database.EvenimentDao;
import main.java.com.echipa4.agenda.Model.Eveniment;

public class EventController {
	private static EventController evenimentControllerInstance = null;
	
	public static EventController getInstance() {
		if (evenimentControllerInstance == null) { 
			evenimentControllerInstance = new EventController();
		}
		
		return evenimentControllerInstance;
	}
	
	private Eveniment eventToModify;
	
	public void createEvenimentToModify(Eveniment event) {
		if (event == null) {
			this.eventToModify = new Eveniment();
		} else {
			this.eventToModify = event.clone();
		}
	}
	
	public Eveniment getEvenimentToModify() {
		return this.eventToModify;
	}
	
	public boolean saveEvenimentToAdd() {
		try {
			EvenimentDao.getInstance().insert(this.eventToModify);
			return true;
		} catch (SQLException e) {

			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public void cancelEvenimentToAdd() {
		this.eventToModify = null;
	}

}
