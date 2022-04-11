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
import main.java.com.echipa4.agenda.View.Calendar;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

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
		shell.setMinimumSize(new Point(1000, 800));
		shell.setSize(450, 300);
		shell.setText("Agenda");
		RowLayout rl_shell = new RowLayout(SWT.VERTICAL);
		rl_shell.center = true;
		rl_shell.fill = true;
		shell.setLayout(rl_shell);
		createToolBar(shell);
		createCalendar(shell);
	}
	
	private void createToolBar(Shell shell) {
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
		
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
		calendar = new Calendar(shell, SWT.NONE, CalendarTypes.WEEKLY);
	}
}
