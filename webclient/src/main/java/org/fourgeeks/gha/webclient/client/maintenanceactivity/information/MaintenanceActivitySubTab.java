package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPanel;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.MaintenanceActivitySubProtocolListener;

/**
 * @author naramirez
 */
public class MaintenanceActivitySubTab extends GHASubTab {

	private final MaintenanceActivityDefinitionFormPanel formPanel;

	/**
	 * @param panel
	 */
	public MaintenanceActivitySubTab(MaintenanceActivityPanel panel) {
		super(GHAStrings.get("activity-definition"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		formPanel = new MaintenanceActivityDefinitionFormPanel();
		addClosableListener(formPanel);
		addHideableListener(formPanel);

		setPane(formPanel);

		formPanel.addMaintenanceActivitySelectionListener(panel);
		panel.addMaintenanceActivitySelectionListener(formPanel);
	}

	/**
	 * 
	 * @param listener
	 */
	public void addMaintenanceActivitySubProtocolListener(
			MaintenanceActivitySubProtocolListener listener) {
		formPanel.addMaintenanceActivitySubProtocolListener(listener);
	}

	/**
	 * 
	 */
	public void show() {
		formPanel.show();
	}
}
