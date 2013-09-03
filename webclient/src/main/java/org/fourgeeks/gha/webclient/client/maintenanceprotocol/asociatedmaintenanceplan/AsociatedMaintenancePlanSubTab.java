package org.fourgeeks.gha.webclient.client.maintenanceprotocol.asociatedmaintenanceplan;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

public class AsociatedMaintenancePlanSubTab extends GHASubTab implements
		EIATypeSelectionListener, EIASelectionListener{

	private AsociatedMaintenancePlanGridPanel asociatedMaintenancePlanGridPanel;

	public AsociatedMaintenancePlanSubTab(MaintenanceProtocolTab tab) {
		super("Planes de Mant.", tab);
		
		asociatedMaintenancePlanGridPanel = new AsociatedMaintenancePlanGridPanel(this);
		addGHAClosableHandler(asociatedMaintenancePlanGridPanel);
		addGHAHideableHandler(asociatedMaintenancePlanGridPanel);
		
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