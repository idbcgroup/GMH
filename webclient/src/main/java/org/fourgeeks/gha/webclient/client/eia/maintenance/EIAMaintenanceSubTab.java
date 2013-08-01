package org.fourgeeks.gha.webclient.client.eia.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAMaintenanceSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAMaintenanceGridPanel eiaMaintenanceGridPanel = null;
	
	public EIAMaintenanceSubTab(EIATab tab) {
		super("Mantenimiento y Protocolos",tab);
		
		eiaMaintenanceGridPanel = new EIAMaintenanceGridPanel();
		addGHAClosableHandler(eiaMaintenanceGridPanel);
		addGHAHideableHandler(eiaMaintenanceGridPanel);
		
		setPane(eiaMaintenanceGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
