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

import main.java.com.echipa4.agenda.Interfaces.EventViewTypes;
import main.java.com.echipa4.agenda.View.AddModifyEventDialog;
import main.java.com.echipa4.agenda.View.EventView;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Combo;

public class MainFrame {

	protected Shell shell;
	private EventView calendar;
	private ToolBar toolBar;
	private ToolItem weeklyItem;
	private ToolItem montlyItem;
	private ToolItem yearlyItem;

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
		calendar = new EventView(shell, SWT.NONE, EventViewTypes.TABLE);
		GridLayout gridLayout = (GridLayout) calendar.getLayout();
		GridData gd_calendar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_calendar.widthHint = 972;
		calendar.setLayoutData(gd_calendar);
	}
	
	private void createToolBar(Shell shell) {
		shell.setLayout(new GridLayout(3, false));
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo.getSelectionIndex() == 1) {
					toolBar.setVisible(true);
					if (weeklyItem.getSelection()) {
						calendar.changeType(EventViewTypes.CALENDAR_WEEKLY);
					} else if (montlyItem.getSelection()) {
						calendar.changeType(EventViewTypes.CALENDAR_MONTHLY);
					} else if (yearlyItem.getSelection()) {
						calendar.changeType(EventViewTypes.CALENDAR_YEARLY);
					} else {
						calendar.changeType(EventViewTypes.CALENDAR_WEEKLY);
					}
				} else {
					toolBar.setVisible(false);	
					calendar.changeType(EventViewTypes.TABLE);				
				}
			}
		});
		combo.setItems(new String[] {"Table", "Calendar"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);
		toolBar = new ToolBar(shell, SWT.FLAT);
		GridData gd_toolBar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_toolBar.widthHint = 700;
		toolBar.setLayoutData(gd_toolBar);
		toolBar.setVisible(false);
		
		weeklyItem = new ToolItem(toolBar, SWT.RADIO);
		weeklyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calendar.changeType(EventViewTypes.CALENDAR_WEEKLY);
			}
		});
		weeklyItem.setText("Saptamanal");
		weeklyItem.setSelection(true);
		
		montlyItem = new ToolItem(toolBar, SWT.RADIO);
		montlyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calendar.changeType(EventViewTypes.CALENDAR_MONTHLY);
			}
		});
		montlyItem.setText("Lunar");
		
		yearlyItem = new ToolItem(toolBar, SWT.RADIO);
		yearlyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calendar.changeType(EventViewTypes.CALENDAR_YEARLY);
			}
		});
		yearlyItem.setText("Anual");
	}
	
	private void createCalendar(Shell shell) {
	}
}
