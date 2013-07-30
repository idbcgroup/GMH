package org.fourgeeks.gha.webclient.client.eia.maintenance_plan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAMantPlanSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIAMantPlanGridPanel mantPlanGridPanel = new EIAMantPlanGridPanel();
	
	public EIAMantPlanSubTab() {
		setTitle("Plan Mantenimiento");
		setPaneMargin(0);
		
		setPane(mantPlanGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		mantPlanGridPanel.close();
	}
}
