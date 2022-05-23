package main.java.com.echipa4.agenda;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;

import main.java.com.echipa4.agenda.View.EventView;
import org.eclipse.swt.custom.ScrolledComposite;

public class MainFrame {
	protected Shell shell;
	private EventView eventView;

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
		shell.setLayout(new FillLayout());		
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		eventView = new EventView(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(eventView);
	}
}
