package main.java.com.echipa4.agenda.Model;

import java.sql.Date;

public class Alarma {
	private long id;
	private Date startAlarma;
	private int recurenta;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getStartAlarma() {
		return startAlarma;
	}
	
	public void setStartAlarma(Date startAlarma) {
		this.startAlarma = startAlarma;
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
		newAlarma.setStartAlarma(startAlarma);
		newAlarma.setRecurenta(recurenta);
		
		return newAlarma;
	}
}
