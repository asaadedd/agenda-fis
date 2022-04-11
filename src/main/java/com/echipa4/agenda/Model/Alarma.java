package main.java.com.echipa4.agenda.Model;

import java.sql.Date;
import java.util.ArrayList;

public class Alarma {
	private long id;
	private Date startAlarma;
	private int recurenta;
	private ArrayList<Date> timpiDeclansare;

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
}
