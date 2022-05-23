package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import main.java.com.echipa4.agenda.Controller.EventViewController;
import main.java.com.echipa4.agenda.Controller.EventViewListener;
import main.java.com.echipa4.agenda.Interfaces.EventViewTypes;

public class EventToolbar extends Composite {
	private EventViewController eventViewController = EventViewController.getInstance();
	private ToolBar toolBar;
	private ToolItem dailyItem;
	private ToolItem weeklyItem;
	private ToolItem montlyItem;
	private ToolItem yearlyItem;
	private Button btnNewButton;
	
	public EventToolbar(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo.getSelectionIndex() == 1) {
					if (weeklyItem.getSelection()) {
						eventViewController.changeViewType(EventViewTypes.CALENDAR_WEEKLY);
					} else if (montlyItem.getSelection()) {
						eventViewController.changeViewType(EventViewTypes.CALENDAR_MONTHLY);
					} else if (yearlyItem.getSelection()) {
						eventViewController.changeViewType(EventViewTypes.CALENDAR_YEARLY);
					} else if (dailyItem.getSelection()) {
						eventViewController.changeViewType(EventViewTypes.CALENDAR_DAILY);
					} else {
						eventViewController.changeViewType(EventViewTypes.CALENDAR_WEEKLY);
					}
				} else {
					eventViewController.changeViewType(EventViewTypes.TABLE);				
				}
			}
		});
		combo.setItems(new String[] {"Table", "Calendar"});
		combo.select(1);
		
		toolBar = new ToolBar(this, SWT.FLAT);
		toolBar.setVisible(true);
		
		dailyItem = new ToolItem(toolBar, SWT.RADIO);
		dailyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.changeViewType(EventViewTypes.CALENDAR_DAILY);
			}
		});
		dailyItem.setText("Zilnic");
		
		weeklyItem = new ToolItem(toolBar, SWT.RADIO);
		weeklyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.changeViewType(EventViewTypes.CALENDAR_WEEKLY);
			}
		});
		weeklyItem.setText("Saptamanal");
		weeklyItem.setSelection(true);
		
		montlyItem = new ToolItem(toolBar, SWT.RADIO);
		montlyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.changeViewType(EventViewTypes.CALENDAR_MONTHLY);
			}
		});
		montlyItem.setText("Lunar");
		
		yearlyItem = new ToolItem(toolBar, SWT.RADIO);
		yearlyItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.changeViewType(EventViewTypes.CALENDAR_YEARLY);
			}
		});
		yearlyItem.setText("Anual");
		
		btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createDialog();
			}
		});
		btnNewButton.setText("Adauga eveniment");
		
		eventViewController.addEventViewListener(new EventViewListener() {
			@Override
			public void updateViewMode() {
				EventViewTypes viewType = eventViewController.getViewType();
				if (viewType == EventViewTypes.TABLE) {
					toolBar.setVisible(false);
				} else {
					toolBar.setVisible(true);

					switch (viewType) {
					case CALENDAR_DAILY:
						dailyItem.setSelection(true);
						weeklyItem.setSelection(false);
						montlyItem.setSelection(false);
						yearlyItem.setSelection(false);
						break;
					case CALENDAR_WEEKLY:
						dailyItem.setSelection(false);
						weeklyItem.setSelection(true);
						montlyItem.setSelection(false);
						yearlyItem.setSelection(false);
						break;
					case CALENDAR_MONTHLY:
						dailyItem.setSelection(false);
						weeklyItem.setSelection(false);
						montlyItem.setSelection(true);
						yearlyItem.setSelection(false);
						break;
					case CALENDAR_YEARLY:
						dailyItem.setSelection(false);
						weeklyItem.setSelection(false);
						montlyItem.setSelection(false);
						yearlyItem.setSelection(true);
						break;
					default:
						break;
					}
				}
				
			}
		});
	}
	
	private void createDialog() {
		btnNewButton.setEnabled(false);
		AddModifyEventDialog dialog = new AddModifyEventDialog(getShell(), SWT.None);
		
		dialog.open(null);
		btnNewButton.setEnabled(true);
	}

}
