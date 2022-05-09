package main.java.com.echipa4.agenda;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;

import main.java.com.echipa4.agenda.Interfaces.CalendarTypes;
import main.java.com.echipa4.agenda.View.AddModifyEventDialog;
import main.java.com.echipa4.agenda.View.Calendar;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Group;

public class MainFrame {

	protected Shell shell;
	private Calendar calendar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainFrame window = new MainFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMaximumSize(new Point(1000, 800));
		shell.setMinimumSize(new Point(1000, 800));
		shell.setSize(1000, 800);
		shell.setText("Agenda");
		createToolBar(shell);
		createCalendar(shell);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddModifyEventDialog dialog = new AddModifyEventDialog(shell, SWT.None);
				
				dialog.open(null);
			}
		});
		btnNewButton.setText("Adauga eveniment");
		calendar = new Calendar(shell, SWT.NONE, CalendarTypes.WEEKLY);
		calendar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
	}
	
	private void createToolBar(Shell shell) {
		shell.setLayout(new GridLayout(2, false));
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
		GridData gd_toolBar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_toolBar.widthHint = 827;
		toolBar.setLayoutData(gd_toolBar);
		
		ToolItem tltmRadioItem = new ToolItem(toolBar, SWT.RADIO);
		tltmRadioItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calendar.changeType(CalendarTypes.WEEKLY);
			}
		});
		tltmRadioItem.setText("Saptamanal");
		
		ToolItem tltmRadioItem_1 = new ToolItem(toolBar, SWT.RADIO);
		tltmRadioItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calendar.changeType(CalendarTypes.MONTHLY);
			}
		});
		tltmRadioItem_1.setText("Lunar");
		
		ToolItem tltmRadioItem_2 = new ToolItem(toolBar, SWT.RADIO);
		tltmRadioItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calendar.changeType(CalendarTypes.YEARLY);
			}
		});
		tltmRadioItem_2.setText("Anual");
	}
	
	private void createCalendar(Shell shell) {
	}
}
