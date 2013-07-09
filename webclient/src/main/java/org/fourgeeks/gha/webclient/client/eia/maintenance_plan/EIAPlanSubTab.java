package org.fourgeeks.gha.webclient.client.eia.maintenance_plan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class EIAPlanSubTab extends Tab implements EIATypeSelectionListener {
	
	public EIAPlanSubTab() {
		setTitle("Plan Mantenimiento");
		setPaneMargin(0);
		
		setPane(new VLayout());
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
