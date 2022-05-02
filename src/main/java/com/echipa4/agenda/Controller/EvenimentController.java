package main.java.com.echipa4.agenda.Controller;

public class EvenimentController {
	private static EvenimentController evenimentControllerInstance = null;
	
	public static EvenimentController getInstance() {
		if (evenimentControllerInstance == null) { 
			evenimentControllerInstance = new EvenimentController();
		}
		
		return evenimentControllerInstance;
	}

}
