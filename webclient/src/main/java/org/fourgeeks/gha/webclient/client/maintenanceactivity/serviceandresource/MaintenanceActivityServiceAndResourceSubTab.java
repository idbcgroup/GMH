package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
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
		sectionForm = new GHASectionForm("Opciones");
		materialsPanel = new MaintenanceActivityMaterialGridPanel();
		equipmentsPanel = new MaintenanceActivityEiaTypeGridPanel();
	}

	/**
	 * @param panel
	 */
	public MaintenanceActivityServiceAndResourceSubTab(
			MaintenanceActivityPanel panel) {
		super(GHAStrings.get("required-resources"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		// listeners
		addClosableListener(materialsPanel);
		addHideableListener(materialsPanel);
		addClosableListener(equipmentsPanel);
		addHideableListener(equipmentsPanel);

		// section form
		sectionForm.addSection(GHAStrings.get("materials"), materialsPanel);
		sectionForm.addSection(GHAStrings.get("job-equipments"),
				equipmentsPanel);

		panel.addMaintenanceActivitySelectionListener(materialsPanel);
		panel.addMaintenanceActivitySelectionListener(equipmentsPanel);

		final GHAFormLayout mainLayout = new GHAFormLayout() {
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