package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;

import main.java.com.echipa4.agenda.Controller.EventController;
import main.java.com.echipa4.agenda.Controller.EventListener;
import main.java.com.echipa4.agenda.Model.Alarma;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;
import main.java.com.echipa4.agenda.Model.Recurenta;
import main.java.com.echipa4.agenda.Model.Reperate;

public class EventTable extends Composite {
	private Table table;
	private EventController eventController = EventController.getInstance();

	public EventTable(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this.setSize(1000, 800);
		
		table = new Table(this, SWT.BORDER | SWT.HIDE_SELECTION );
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setTouchEnabled(false);

		
		TableColumn colorColumn = new TableColumn(table, SWT.MULTI);
		colorColumn.setWidth(75);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE | SWT.HIDE_SELECTION);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Titlu");
		
		TableColumn tblclmnDescriere = new TableColumn(table, SWT.NONE | SWT.HIDE_SELECTION);
		tblclmnDescriere.setWidth(100);
		tblclmnDescriere.setText("Descriere");
		
		TableColumn tblclmnDeLa = new TableColumn(table, SWT.NONE | SWT.HIDE_SELECTION);
		tblclmnDeLa.setWidth(100);
		tblclmnDeLa.setText("De la");
		
		TableColumn tblclmnPanaLa = new TableColumn(table, SWT.NONE | SWT.HIDE_SELECTION);
		tblclmnPanaLa.setWidth(100);
		tblclmnPanaLa.setText("Pana la");
		
		TableColumn tblclmnRecurenta = new TableColumn(table, SWT.NONE | SWT.HIDE_SELECTION);
		tblclmnRecurenta.setWidth(100);
		tblclmnRecurenta.setText("Recurenta");
		
		TableColumn tblclmnAlarma = new TableColumn(table, SWT.NONE | SWT.HIDE_SELECTION);
		tblclmnAlarma.setWidth(200);
		tblclmnAlarma.setText("Alarma");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		
		updateEventsInTable();
		
		eventController.addEventListener(new EventListener() {
		    @Override
		    public void eventsUpdated() {
		    	updateEventsInTable();
		    }
		});
		
	}
	
	private void updateEventsInTable() {
		ArrayList<Eveniment> events = eventController.getAll();
		table.setItemCount(0);
		events.forEach((event) -> this.addEvenimentToTable(event));
	    TableItem[] items = table.getItems();		

		for(int index = 0; index < table.getItemCount(); index++) {
			TableEditor editor = new TableEditor(table);
			Button editButton = new Button(table, SWT.NONE);
			editButton.setText("edit");
		    editor.setEditor(editButton, items[index], 7);
		    
			editor = new TableEditor(table);
			Button deleteButton = new Button(table, SWT.NONE);
			editButton.setText("delete");
		    editor.setEditor(deleteButton, items[index], 8);
		}
	}
	
	private void addEvenimentToTable(Eveniment event) {
		TableItem tableItem = new TableItem(table, SWT.NONE);
		String[] text = new String[7];
		Interval interval = event.getInterval();
		Recurenta recurenta = event.getRecurenta();
		Alarma alarm = event.getAlarma();
		RGB rgb = new RGB(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue());
		Color color = new Color(rgb, event.getCuloare().getAlpha());
		tableItem.setBackground(0, color);
		tableItem.setForeground(0, color);
		String hex = "#"+Integer.toHexString(event.getCuloare().getRGB()).substring(2);


		text[0] = hex;
		text[1] = event.getTitlu();
		text[2] = event.getDescriere();
		if (interval != null) {
			text[3] = interval.getDataInceput().toString();
		} else {
			text[3] = "";
		}
		if (interval != null) {
			text[4] = interval.getDataSfarsit().toString();
		} else {
			text[4] = "";
		}
		text[5] = this.getTextFromRepetare(recurenta);
		text[6] = this.getTextFromAlarm(alarm);
		
		tableItem.setText(text);
	}
	
	private String getTextFromRepetare(Recurenta recurenta) {
		if (recurenta == null) {
			return "Fara repetare";
		}
		Reperate rep = recurenta.getReperate();
		int repetare = recurenta.getRecurenta();
		if (repetare == 0) {
			return "Fara repetare";
		}
		
		boolean isSingular = repetare == 1;
		switch(rep) {
			case ONCE:
				return "Fara repetare";
			case DAILY:
				return isSingular ? "O zi" : repetare + " zile";
			case WEEKLY:
				return isSingular ? "O Saptamana" : repetare + " saptamani";
			case MONTHLY:
				return isSingular ? "O luna" : repetare + " luni";
			case YEARLY:
				return isSingular ? "Un an" : repetare + " ani";
			default:
				return "Fara repetare";
		}
	}
	
	private String getTextFromAlarm(Alarma alarm) {
		if (alarm == null) {
			return "Fara alarma";
		}
		String minuteText = alarm.getMinutePornire() == 1 ? "un minut" : alarm.getMinutePornire() + " minute";
	
		return "Cu " + minuteText + " inainte";
	}

}
