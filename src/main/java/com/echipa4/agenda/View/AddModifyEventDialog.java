package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.Color;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ColorDialog;

import main.java.com.echipa4.agenda.Controller.EventController;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;
import main.java.com.echipa4.agenda.Model.Reperate;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

public class AddModifyEventDialog extends Dialog {

	protected boolean result;
	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private EventController eventController = EventController.getInstance();
	private Text text;
	private Label lblDescriere;
	private Text text_1;
	private Label lblCuloare;
	private Button btnAdaugaCuloare;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private Button btnSalveaza;
	private Button btnAnuleaza;
	private DateTime dateTime_2;
	private DateTime dateTime_3;
	private Button btnCheckButton;
	private Button btnRepetaEveniment;
	private Combo combo;
	private Text text_2;
	private Label lblNewLabel_3;
	private Label lblNumarDeRepetari;
	private Label lblNewLabel_4;
	private Text text_3;
	private Label lblNumarDeRepetari_1;
	private Text text_4;
	private ArrayList<Label> repLabels = new ArrayList<Label>(2);
	private ArrayList<Label> alarmLabels = new ArrayList<Label>(2);
	private RGB color;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddModifyEventDialog(Shell parent, int style) {
		super(parent, style);
		setText("Adauga sau modifica eveniment");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public boolean open(Eveniment eveniment) {
		eventController.createEvenimentToModify(eveniment);
		createContents();
		shell.open();
		shell.layout();
		
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(688, 483);
		shell.setText(getText());
		shell.setLayout(new GridLayout(3, false));
		
		this.addTitleInputs();
		this.addDescriptionInputs();
		this.addColorInputs();
		this.addStartDateInputs();
		this.addEndDateInputs();
		this.addRepInputs();
		this.addAlarmInputs();
		this.addSaveAndCancelInputs();
	}
	
	private void addTitleInputs() {
		Label lblNewLabel = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 138;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Titlu");
		
		text = new Text(shell, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_text.widthHint = 187;
		text.setLayoutData(gd_text);
		formToolkit.adapt(text, true, true);
		
		boolean isTextPresent = eventController.getEvenimentToModify().getTitlu() != null;
		if (isTextPresent) {
			text.setText(eventController.getEvenimentToModify().getTitlu());
		}
	}
	
	private void addDescriptionInputs() {
		lblDescriere = new Label(shell, SWT.NONE);
		GridData gd_lblDescriere = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblDescriere.widthHint = 137;
		lblDescriere.setLayoutData(gd_lblDescriere);
		formToolkit.adapt(lblDescriere, true, true);
		lblDescriere.setText("Descriere");
		
		text_1 = new Text(shell, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_1.widthHint = 516;
		text_1.setLayoutData(gd_text_1);
		formToolkit.adapt(text_1, true, true);
		
		boolean isDescriptionPresent = eventController.getEvenimentToModify().getDescriere() != null;
		if (isDescriptionPresent) {
			text_1.setText(eventController.getEvenimentToModify().getDescriere());
		}
	}
	
	private void addColorInputs() {
		lblCuloare = new Label(shell, SWT.NONE);
		GridData gd_lblCuloare = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblCuloare.widthHint = 138;
		lblCuloare.setLayoutData(gd_lblCuloare);
		formToolkit.adapt(lblCuloare, true, true);
		lblCuloare.setText("Culoare");
		
		btnAdaugaCuloare = new Button(shell, SWT.NONE);
		GridData gd_btnAdaugaCuloare = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_btnAdaugaCuloare.widthHint = 518;
		btnAdaugaCuloare.setLayoutData(gd_btnAdaugaCuloare);
		btnAdaugaCuloare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dialog = new ColorDialog(shell, SWT.NONE);
				
				color = dialog.open();				
			}
		});
		formToolkit.adapt(btnAdaugaCuloare, true, true);
		btnAdaugaCuloare.setText("Adauga culoare");
	}
	
	private void addStartDateInputs() {		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 136;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		formToolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("Data inceput");
		

		dateTime = new DateTime(shell, SWT.BORDER);
		GridData gd_dateTime = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateTime.widthHint = 278;
		dateTime.setLayoutData(gd_dateTime);
		formToolkit.adapt(dateTime);
		formToolkit.paintBordersFor(dateTime);
		
		dateTime_1 = new DateTime(shell, SWT.TIME | SWT.TIME);
		GridData gd_dateTime_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateTime_1.widthHint = 234;
		dateTime_1.setLayoutData(gd_dateTime_1);
		formToolkit.adapt(dateTime_1);
		formToolkit.paintBordersFor(dateTime_1);
		
		
		Interval internal = this.eventController.getEvenimentToModify().getInterval();
		boolean isIntervalPresent = internal != null;
		boolean isStartDateValid = isIntervalPresent && internal.getDataSfarsit() != null;
		if (isStartDateValid) {
			dateTime_1.setData(internal.getDataInceput());
		}
	}
	
	private void addEndDateInputs() {
		lblNewLabel_2 = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_2.widthHint = 136;
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);
		formToolkit.adapt(lblNewLabel_2, true, true);
		lblNewLabel_2.setText("Data sfarsit");
		
		dateTime_2 = new DateTime(shell, SWT.BORDER);
		GridData gd_dateTime_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateTime_2.widthHint = 278;
		dateTime_2.setLayoutData(gd_dateTime_2);
		formToolkit.adapt(dateTime_2);
		formToolkit.paintBordersFor(dateTime_2);
		
		dateTime_3 = new DateTime(shell, SWT.TIME | SWT.TIME);
		GridData gd_dateTime_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateTime_3.widthHint = 235;
		dateTime_3.setLayoutData(gd_dateTime_3);
		formToolkit.adapt(dateTime_3);
		formToolkit.paintBordersFor(dateTime_3);
		
//		Interval internal = this.eventController.getEvenimentToModify().getInterval();
//		boolean isIntervalPresent = internal != null;
//		boolean isEndDateValid = isIntervalPresent && internal.getDataSfarsit() != null;
//		if (isEndDateValid) {
//			dateTime_1.setData(internal.getDataSfarsit());
//		}
	}
	
	private void addRepInputs() {
		repLabels.clear();
		btnRepetaEveniment = new Button(shell, SWT.CHECK);
		GridData gd_btnRepetaEveniment = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnRepetaEveniment.widthHint = 136;
		btnRepetaEveniment.setLayoutData(gd_btnRepetaEveniment);
		btnRepetaEveniment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				toogleRepVisible();
			}
		});
		formToolkit.adapt(btnRepetaEveniment, true, true);
		btnRepetaEveniment.setText("Repetare");
		
		repLabels.add(new Label(shell, SWT.NONE));
		repLabels.add(new Label(shell, SWT.NONE));
		
		lblNewLabel_3 = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_3.widthHint = 138;
		lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
		formToolkit.adapt(lblNewLabel_3, true, true);
		lblNewLabel_3.setText("Interval");
		this.setControllVizibility(lblNewLabel_3, this.eventController.shouldDisplayRepInputs(), true);
		
		combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] {Reperate.DAILY.toString(), Reperate.WEEKLY.toString(), Reperate.MONTHLY.toString(), Reperate.YEARLY.toString()});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		this.setControllVizibility(combo, this.eventController.shouldDisplayRepInputs(), true);
		
		lblNumarDeRepetari = new Label(shell, SWT.NONE);
		GridData gd_lblNumarDeRepetari = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNumarDeRepetari.widthHint = 138;
		lblNumarDeRepetari.setLayoutData(gd_lblNumarDeRepetari);
		formToolkit.adapt(lblNumarDeRepetari, true, true);
		lblNumarDeRepetari.setText("Numar de repetari");
		this.setControllVizibility(lblNumarDeRepetari, this.eventController.shouldDisplayRepInputs(), true);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		formToolkit.adapt(text_2, true, true);
		this.setControllVizibility(text_2, this.eventController.shouldDisplayRepInputs(), true);

		repLabels.forEach((n) -> this.setControllVizibility(n, this.eventController.shouldDisplayRepInputs(), false));
	}
	
	private void addAlarmInputs() {
		alarmLabels.clear();
		btnCheckButton = new Button(shell, SWT.CHECK);
		GridData gd_btnCheckButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCheckButton.widthHint = 138;
		btnCheckButton.setLayoutData(gd_btnCheckButton);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				toogleAlarmVisible();
			}
		});
		formToolkit.adapt(btnCheckButton, true, true);
		btnCheckButton.setText("Adauga alarma");

		alarmLabels.add(new Label(shell, SWT.NONE));
		alarmLabels.add(new Label(shell, SWT.NONE));
		
		lblNewLabel_4 = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel_4 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_4.widthHint = 138;
		lblNewLabel_4.setLayoutData(gd_lblNewLabel_4);
		formToolkit.adapt(lblNewLabel_4, true, true);
		lblNewLabel_4.setText("Minute");
		this.setControllVizibility(lblNewLabel_4, this.eventController.shouldDisplayAlarmInputs(), true);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				int minutePornire;
				try {
					minutePornire = Integer.parseInt(text_3.getText());
					eventController.ensureAlarma();
					eventController.getEvenimentToModify().getAlarma().setMinutePornire(minutePornire);
				} catch (NumberFormatException ex) {
				}
			}
		});
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		formToolkit.adapt(text_3, true, true);
		this.setControllVizibility(text_3, this.eventController.shouldDisplayAlarmInputs(), true);
				
		lblNumarDeRepetari_1 = new Label(shell, SWT.NONE);
		GridData gd_lblNumarDeRepetari_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNumarDeRepetari_1.widthHint = 138;
		lblNumarDeRepetari_1.setLayoutData(gd_lblNumarDeRepetari_1);
		formToolkit.adapt(lblNumarDeRepetari_1, true, true);
		lblNumarDeRepetari_1.setText("Numar de repetari");
		this.setControllVizibility(lblNumarDeRepetari_1, this.eventController.shouldDisplayAlarmInputs(), true);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				int recurenta;
				try {
					recurenta = Integer.parseInt(text_4.getText());
					eventController.ensureAlarma();
					eventController.getEvenimentToModify().getAlarma().setRecurenta(recurenta);
				} catch (NumberFormatException ex) {
				}
			}
		});
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		formToolkit.adapt(text_4, true, true);
		this.setControllVizibility(text_4, this.eventController.shouldDisplayAlarmInputs(), true);
		
		alarmLabels.forEach((n) -> this.setControllVizibility(n, this.eventController.shouldDisplayAlarmInputs(), false));
	}
	
	private void addSaveAndCancelInputs() {
		btnAnuleaza = new Button(shell, SWT.NONE);
		GridData gd_btnSalveaza = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSalveaza.widthHint = 138;
		btnAnuleaza.setLayoutData(gd_btnSalveaza);
		btnAnuleaza.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventController.cancelEvenimentToAdd();
				result = false;
				shell.close();
			}
		});
		formToolkit.adapt(btnAnuleaza, true, true);
		btnAnuleaza.setText("Anuleaza");
		new Label(shell, SWT.NONE);
		
		btnSalveaza = new Button(shell, SWT.NONE);
		btnSalveaza.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Eveniment evenimentToModify = eventController.getEvenimentToModify();
				evenimentToModify.setTitlu(text.getText());
				evenimentToModify.setDescriere(text_1.getText());

				if (color != null) {
					evenimentToModify.setCuloare(new Color(color.red, color.green, color.blue));
				}
				

				eventController.setStartDate(dateTime, dateTime_1);
				eventController.setEndDate(dateTime_2, dateTime_3);
				
				if (eventController.shouldDisplayRepInputs()) {
					eventController.ensureRecurenta();
					
					Reperate[] recurenta = new Reperate[] {Reperate.DAILY, Reperate.WEEKLY, Reperate.MONTHLY, Reperate.YEARLY};
					eventController.ensureRecurenta();
					eventController.getEvenimentToModify().getRecurenta().setRepetare(recurenta[combo.getSelectionIndex()]);

					int dimension;
					try {
						dimension = Integer.parseInt(text_2.getText());
					} catch (NumberFormatException ex) {
						dimension = 1;
					}
					eventController.ensureRecurenta();
					eventController.getEvenimentToModify().getRecurenta().setRecurenta(dimension);
				}
				
				if (eventController.shouldDisplayAlarmInputs()) {
					eventController.ensureAlarma();

					int minutePornire;
					try {
						minutePornire = Integer.parseInt(text_3.getText());
					} catch (NumberFormatException ex) {
						minutePornire = 10;
					}
					eventController.ensureAlarma();
					eventController.getEvenimentToModify().getAlarma().setMinutePornire(minutePornire);
					
					int recurenta;
					try {
						recurenta = Integer.parseInt(text_4.getText());
					} catch (NumberFormatException ex) {
						recurenta = 1;
					}
					eventController.ensureAlarma();
					eventController.getEvenimentToModify().getAlarma().setRecurenta(recurenta);
				}
				
				result = eventController.saveEvenimentToAdd();
				shell.close();
			}
		});
		GridData gd_btnAnuleaza = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnAnuleaza.widthHint = 145;
		btnSalveaza.setLayoutData(gd_btnAnuleaza);
		formToolkit.adapt(btnSalveaza, true, true);
		btnSalveaza.setText("Salveaza");
	}
	
	private void toogleAlarmVisible() {
		eventController.toogleAlarmVisible();
		this.setControllVizibility(lblNewLabel_4, this.eventController.shouldDisplayAlarmInputs(), true);
		this.setControllVizibility(text_3, this.eventController.shouldDisplayAlarmInputs(), true);
		this.setControllVizibility(lblNumarDeRepetari_1, this.eventController.shouldDisplayAlarmInputs(), true);
		this.setControllVizibility(text_4, this.eventController.shouldDisplayAlarmInputs(), true);
        alarmLabels.forEach((n) -> this.setControllVizibility(n, this.eventController.shouldDisplayAlarmInputs(), false));
        shell.layout(false);
	}
	
	private void toogleRepVisible() {
		eventController.toogleRepVisible();
		this.setControllVizibility(lblNewLabel_3, this.eventController.shouldDisplayRepInputs(), true);
		this.setControllVizibility(combo, this.eventController.shouldDisplayRepInputs(), true);
		this.setControllVizibility(lblNumarDeRepetari, this.eventController.shouldDisplayRepInputs(), true);
		this.setControllVizibility(text_2, this.eventController.shouldDisplayRepInputs(), true);
        repLabels.forEach((n) -> this.setControllVizibility(n, this.eventController.shouldDisplayRepInputs(), false));
        shell.layout(false);
	}
	
	private void setControllVizibility(Control control, boolean show, boolean removeFromLayour) {
		control.setEnabled(show);
		control.setVisible(show);
        GridData data = (GridData) control.getLayoutData();
        if (data != null && removeFromLayour) {
            data.exclude = !show;
        }
	}
}
