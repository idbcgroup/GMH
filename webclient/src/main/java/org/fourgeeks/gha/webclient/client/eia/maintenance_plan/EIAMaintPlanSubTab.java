package org.fourgeeks.gha.webclient.client.eia.maintenance_plan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAMaintPlanSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAMaintPlanGridPanel eiaMaintPlanGridPanel = null;
	
	public EIAMaintPlanSubTab(EIATab tab) {
		super("Planes Mantenimiento", tab);
		
		eiaMaintPlanGridPanel = new EIAMaintPlanGridPanel();
		addGHAClosableHandler(eiaMaintPlanGridPanel);
		addGHAHideableHandler(eiaMaintPlanGridPanel);
		
		setPane(eiaMaintPlanGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
