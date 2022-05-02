package main.java.com.echipa4.agenda.Model;

public class Recurenta {
	private Long id;
	private int recurenta;
	private Reperate repetare;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getRecurenta() {
		return recurenta;
	}
	
	public void setRecurenta(int recurenta) {
		this.recurenta = recurenta;
	}
	
	public Reperate getReperate() {
		return repetare;
	}
	
	public void setRepetare(Reperate repetare) {
		this.repetare = repetare;
	}
	
	public Recurenta clone() {
		Recurenta newRecurenta = new Recurenta();
		
		newRecurenta.setId(id);
		newRecurenta.setRecurenta(recurenta);
		newRecurenta.setRepetare(repetare);
		
		return newRecurenta;
	}
}
