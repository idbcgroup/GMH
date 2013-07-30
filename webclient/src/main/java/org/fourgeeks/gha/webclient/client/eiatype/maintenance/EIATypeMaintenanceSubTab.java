package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;

public class EIATypeMaintenanceSubTab extends Tab implements
		EIATypeSelectionListener {

	private EIATypeMaintenanceGridPanel maintenanceGridPanel = new EIATypeMaintenanceGridPanel();

	public EIATypeMaintenanceSubTab() {

		setTitle("Mantenimiento y Protocolos");
		setPaneMargin(0);
		setPane(maintenanceGridPanel);
		addTabDeselectedHandler(new TabDeselectedHandler() {

			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				maintenanceGridPanel.hide();
			}
		});
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		maintenanceGridPanel.select(eiaType);
	}

}
