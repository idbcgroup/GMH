package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPanel;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityServiceAndResourceSubTab extends GHASubTab {

	private MaintenanceActivityMaterialGridPanel materialsPanel;
	private MaintenanceActivityEiaTypeGridPanel equipmentsPanel;
	private GHASectionForm sectionForm;

	{
		sectionForm = new GHASectionForm();
		materialsPanel = new MaintenanceActivityMaterialGridPanel();
		equipmentsPanel = new MaintenanceActivityEiaTypeGridPanel();
	}

	/**
	 * @param tab
	 */
	public MaintenanceActivityServiceAndResourceSubTab(
			MaintenanceActivityPanel tab) {
		super(GHAStrings.get("required-resources"), tab);

		// listeners
		addClosableListener(materialsPanel);
		addHideableListener(materialsPanel);
		addClosableListener(equipmentsPanel);
		addHideableListener(equipmentsPanel);

		// section form
		sectionForm.addSection(GHAStrings.get("materials"), materialsPanel);
		sectionForm.addSection(GHAStrings.get("job-equipments"),
				equipmentsPanel);

		tab.addMaintenanceActivitySelectionListener(materialsPanel);
		tab.addMaintenanceActivitySelectionListener(equipmentsPanel);

		GHAVerticalLayout mainLayout = new GHAVerticalLayout() {
		};

		mainLayout.addMember(sectionForm);
		setPane(mainLayout);

		// handlers
		addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				show();
			}
		});

		addTabDeselectedHandler(new TabDeselectedHandler() {
			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				hide();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab#hide()
	 */
	@Override
	public void hide() {
		super.hide();
		sectionForm.hide();
	}

	/**
	 * 
	 */
	public void openFirstSection() {
		sectionForm.openFirst();
		show();
	}

	/**
	 * 
	 */
	public void show() {
		sectionForm.show();
		materialsPanel.show();
		equipmentsPanel.show();
	}
}