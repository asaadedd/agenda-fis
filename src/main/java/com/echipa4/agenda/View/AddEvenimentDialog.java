package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.FormText;

public class AddEvenimentDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddEvenimentDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
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
		shell.setLayout(new ColumnLayout());
		
		Form frmAdaugaEvenimentNou = formToolkit.createForm(shell);
		frmAdaugaEvenimentNou.setTitleTextSelectable(false);
		frmAdaugaEvenimentNou.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		ColumnLayoutData cld_frmAdaugaEvenimentNou = new ColumnLayoutData();
		cld_frmAdaugaEvenimentNou.heightHint = 458;
		cld_frmAdaugaEvenimentNou.widthHint = 661;
		frmAdaugaEvenimentNou.setLayoutData(cld_frmAdaugaEvenimentNou);
		formToolkit.paintBordersFor(frmAdaugaEvenimentNou);
		frmAdaugaEvenimentNou.setText("Adauga eveniment nou");
		frmAdaugaEvenimentNou.getBody().setLayout(new ColumnLayout());
		
		FormText formText = formToolkit.createFormText(frmAdaugaEvenimentNou.getBody(), false);
		formToolkit.paintBordersFor(formText);
		formText.setText("New FormText", false, false);
		
		Label lblLabe = formToolkit.createLabel(frmAdaugaEvenimentNou.getBody(), "LAbe", SWT.NONE);
		

	}
}
