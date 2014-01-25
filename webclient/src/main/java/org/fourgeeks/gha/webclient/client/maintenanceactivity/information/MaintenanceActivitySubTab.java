package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPanel;

/**
 * @author naramirez
 */
public class MaintenanceActivitySubTab extends GHASubTab {

	private final MaintenanceActivityDefinitionFormPanel formPanel;

	/**
	 * @param panel
	 */
	public MaintenanceActivitySubTab(MaintenanceActivityPanel panel) {
		super(GHAStrings.get("activity-definition"), panel);

		formPanel = new MaintenanceActivityDefinitionFormPanel();
		addClosableListener(formPanel);
		addHideableListener(formPanel);

		setPane(formPanel);

		formPanel.addMaintenanceActivitySelectionListener(panel);
		panel.addMaintenanceActivitySelectionListener(formPanel);
	}

	/**
	 * 
	 */
	public void show() {
		formPanel.show();
	}
}
