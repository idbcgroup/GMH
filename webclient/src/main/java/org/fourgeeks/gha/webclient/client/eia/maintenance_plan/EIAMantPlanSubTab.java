package org.fourgeeks.gha.webclient.client.eia.maintenance_plan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAMantPlanSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAMantPlanGridPanel eiaMantPlanGridPanel = null;
	
	public EIAMantPlanSubTab(EIATab tab) {
		super("Planes Mantenimiento", tab);
		
		eiaMantPlanGridPanel = new EIAMantPlanGridPanel();
		addGHAClosableHandler(eiaMantPlanGridPanel);
		addGHAHideableHandler(eiaMantPlanGridPanel);
		
		setPane(eiaMantPlanGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
