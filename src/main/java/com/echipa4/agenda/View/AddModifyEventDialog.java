package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ColorDialog;

import main.java.com.echipa4.agenda.Controller.EventController;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.DateTime;

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
		shell.setSize(677, 508);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		
		this.addTitleInputs();
		this.addDescriptionInputs();
		this.addColorInputs();
		this.addStartDateInputs();
		this.addEndDateInputs();
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
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				eventController.getEvenimentToModify().setTitlu(text.getText());
			}
		});
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				eventController.getEvenimentToModify().setDescriere(text_1.getText());
			}
		});
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		btnAdaugaCuloare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dialog = new ColorDialog(shell, SWT.NONE);
				
				RGB result = dialog.open();
				
				eventController.getEvenimentToModify().setCuloare(Color.getColor(result.toString()));
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
		formToolkit.adapt(dateTime);
		formToolkit.paintBordersFor(dateTime);
		
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
		
		dateTime_1 = new DateTime(shell, SWT.BORDER);
		formToolkit.adapt(dateTime_1);
		formToolkit.paintBordersFor(dateTime_1);
		
		Interval internal = this.eventController.getEvenimentToModify().getInterval();
		boolean isIntervalPresent = internal != null;
		boolean isEndDateValid = isIntervalPresent && internal.getDataSfarsit() != null;
		if (isEndDateValid) {
			dateTime_1.setData(internal.getDataSfarsit());
		}
	}
	
	private void addSaveAndCancelInputs() {
		btnAnuleaza = new Button(shell, SWT.NONE);
		GridData gd_btnSalveaza = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSalveaza.widthHint = 136;
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
		
		btnSalveaza = new Button(shell, SWT.NONE);
		btnSalveaza.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = eventController.saveEvenimentToAdd();
				shell.close();
			}
		});
		GridData gd_btnAnuleaza = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAnuleaza.widthHint = 504;
		btnSalveaza.setLayoutData(gd_btnAnuleaza);
		formToolkit.adapt(btnSalveaza, true, true);
		btnSalveaza.setText("Salveaza");
	}
}
