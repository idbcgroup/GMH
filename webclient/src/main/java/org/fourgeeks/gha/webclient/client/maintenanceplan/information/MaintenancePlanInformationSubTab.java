package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsSelectionListener;

/**
 * @author naramirez
 */
public class MaintenancePlanInformationSubTab extends GHASubTab implements
		MaintenanceProtocolsSelectionListener, MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationFormPanel form;

	/**
	 * @param panel
	 */
	public MaintenancePlanInformationSubTab(MaintenancePlanPanel panel) {
		super(GHAStrings.get("information"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		form = new MaintenancePlanInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		// register to listen for selected maintenance plan
		panel.addMaintenancePlanSelectionListener(this);
		form.addMaintenancePlanSelectionListener(panel);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		form.select(maintenancePlan);
	}

	@Override
	public void select(MaintenanceProtocols entity) {
		form.select(entity);
	}

	/**
	 * 
	 */
	public void show() {
		form.show();
	}
}
