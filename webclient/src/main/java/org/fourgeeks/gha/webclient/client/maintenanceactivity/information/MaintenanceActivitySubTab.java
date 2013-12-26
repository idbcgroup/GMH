package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;

/**
 * @author naramirez
 */
public class MaintenanceActivitySubTab extends GHASubTab implements
		MaintenanceActivitySelectionListener {

	private final MaintenanceActivityInformationFormPanel form;

	/**
	 * @param panel
	 */
	public MaintenanceActivitySubTab(MaintenancePlanPanel panel) {
		super("Información", panel);

		form = new MaintenanceActivityInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		form.setMaintenanceActivity(maintenanceActivity);
		setDisabled(false);
	}
}
