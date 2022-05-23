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
//		 Display display = Display.getDefault();
//	      Shell shell = new Shell ();
//	      shell.setLayout(new FillLayout());
//	 	
//	      // set the size of the scrolled content - method 1
//	      final ScrolledComposite sc1 = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
//	      final Composite c1 = new Composite(sc1, SWT.NONE);
//	      sc1.setContent(c1);
//	      GridLayout layout = new GridLayout();
//	      layout.numColumns = 1;
//	      c1.setLayout(layout);
//	      Button b1 = new Button (c1, SWT.PUSH);
//	      b1.setText("first button");
//	      new Button (c1, SWT.PUSH);
//	      new Button (c1, SWT.PUSH);
//	      new Button (c1, SWT.PUSH);
//	      new Button (c1, SWT.PUSH);
//	      
//	      c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//	 
//	      shell.open ();
//	      while (!shell.isDisposed ()) {
//	          if (!display.readAndDispatch ()) display.sleep ();
//	      }
//	      display.dispose ();
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
//		shell.layout();
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
//		GridData gd_eventToolbar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_eventToolbar.widthHint = 965;
//		gd_eventToolbar.heightHint = 38;
//		eventToolbar.setLayoutData(gd_eventToolbar);

//		gd_scrolledComposite.widthHint = 976;
//		gd_scrolledComposite.heightHint = 299;
//		scrolledComposite.setLayoutData(gd_scrolledComposite);
//		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_scrolledComposite.widthHint = 950;
//		gd_scrolledComposite.heightHint = 700;
//		scrolledComposite.setLayoutData(gd_scrolledComposite);
		
//		GridLayout gridLayout = (GridLayout) eventView.getLayout();
//		gridLayout.marginWidth = 0;
//		gridLayout.marginHeight = 0;
//		gridLayout.horizontalSpacing = 1;
//		eventView.setSize(eventView.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
