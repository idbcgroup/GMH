package org.fourgeeks.gha.webclient.client.maintenanceactivity.asociatedprotocols;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityTab;

public class AsociatedMaintenanceProtocolsSubTab extends GHASubTab implements
		EIATypeSelectionListener, EIASelectionListener{

	private AsociatedMaintenanceProtocolsGridPanel asociatedMaintenancePlanGridPanel;

	public AsociatedMaintenanceProtocolsSubTab(MaintenanceActivityTab tab) {
		super("Protocolos de Mant.", tab);
		
		asociatedMaintenancePlanGridPanel = new AsociatedMaintenanceProtocolsGridPanel(this);
		addClosableHandler(asociatedMaintenancePlanGridPanel);
		addHideableHandler(asociatedMaintenancePlanGridPanel);
		
		setPane(asociatedMaintenancePlanGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		asociatedMaintenancePlanGridPanel.select(eiaType);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		asociatedMaintenancePlanGridPanel.select(eia);
		
	}
}