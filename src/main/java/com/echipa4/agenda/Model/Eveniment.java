package main.java.com.echipa4.agenda.Model;

import java.awt.Color;

public class Eveniment {
	private long id;
	private String titlu;
	private String descriere;
	private Interval interval;
	private Recurenta recurenta;
	private Color culoare;
	private Alarma alarma;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitlu() {
		return titlu;
	}
	
	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}
	
	public String getDescriere() {
		return descriere;
	}
	
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	public Interval getInterval() {
		return interval;
	}
	
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	
	public Recurenta getRecurenta() {
		return recurenta;
	}
	
	public void setRecurenta(Recurenta recurenta) {
		this.recurenta = recurenta;
	}
	
	public Color getCuloare() {
		return culoare;
	}
	
	public void setCuloare(Color culoare) {
		this.culoare = culoare;
	}
	
	public Alarma getAlarma() {
		return alarma;
	}
	
	public void setAlarma(Alarma alarma) {
		this.alarma = alarma;
	}
}
