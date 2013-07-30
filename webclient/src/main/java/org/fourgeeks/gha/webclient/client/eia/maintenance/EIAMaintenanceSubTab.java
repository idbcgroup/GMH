package org.fourgeeks.gha.webclient.client.eia.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAMaintenanceSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIAMaintenanceGridPanel maintenanceGridPanel = new EIAMaintenanceGridPanel();
	
	public EIAMaintenanceSubTab() {
		
		setTitle("Mantenimiento y Protocolos");
		setPaneMargin(0);
		
		setPane(maintenanceGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		maintenanceGridPanel.close();
	}
}