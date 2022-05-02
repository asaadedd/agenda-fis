package main.java.com.echipa4.agenda.Model;

import java.sql.Date;

public class Interval {
	private Long id;
	private Date dataInceput;
	private Date dataSfarsit;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataInceput() {
		return dataInceput;
	}
	
	public void setDataInceput(Date data_inceput) {
		dataInceput = data_inceput;		
	}
	
	public Date getDataSfarsit() {
		return dataSfarsit;
	}
	
	public void setDataSfarsit(Date data_sfarsit) {
		dataSfarsit = data_sfarsit;		
	}
	
	public Interval clone() {
		Interval newInterval = new Interval();
	
		newInterval.setId(id);
		newInterval.setDataInceput(dataInceput);
		newInterval.setDataSfarsit(dataSfarsit);
		
		return newInterval;
	}
}
