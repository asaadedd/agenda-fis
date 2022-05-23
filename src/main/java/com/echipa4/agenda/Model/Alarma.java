package main.java.com.echipa4.agenda.Model;

public class Alarma {
	private long id = -1;
	private int minutePornire;
	private int recurenta;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getMinutePornire() {
		return minutePornire;
	}
	
	public void setMinutePornire(int minutePornire) {
		this.minutePornire = minutePornire;
	}
	
	public int getRecurenta() {
		return recurenta;
	}
	
	public void setRecurenta(int recurenta) {
		this.recurenta = recurenta;
	}
	
	public Alarma clone() {
		Alarma newAlarma = new Alarma();
		
		newAlarma.setId(id);
		newAlarma.setMinutePornire(minutePornire);
		newAlarma.setRecurenta(recurenta);
		
		return newAlarma;
	}
}
