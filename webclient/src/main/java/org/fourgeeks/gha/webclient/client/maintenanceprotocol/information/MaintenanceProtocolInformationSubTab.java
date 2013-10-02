package org.fourgeeks.gha.webclient.client.maintenanceprotocol.information;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

public class MaintenanceProtocolInformationSubTab extends GHASubTab implements
	MaintenanceProtocolSelectionListener {

	private MaintenanceProtocolInformationFormPanel form;

	public MaintenanceProtocolInformationSubTab(MaintenanceProtocolTab tab) {
		super("Información", tab);
		
		form = new MaintenanceProtocolInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
		
		//register to listen for a selected maintenanceProtocol
		tab.addMaintenanceProtocolSelectionListener(this);
		form.addMaintenanceProtocolSelectionListener(tab);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		setDisabled(false);
		form.setMaintenanceProtocol(maintenanceProtocol);
	}
}
