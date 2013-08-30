package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.EIATypeMaintenancePlanGridPanel;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeMaintenanceGridPanel extends VLayout implements
		EIATypeSelectionListener,GHAClosable, GHAHideable {

	private EIATypeMaintenancePlanGridPanel maintenancePlanGridPanel = new EIATypeMaintenancePlanGridPanel();	
	
	public EIATypeMaintenanceGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		Label title = new Label("<h3>Planes de Mantenimiento</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
	
		addMembers(title, maintenancePlanGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		super.hide();
		maintenancePlanGridPanel.hide();
	}

	@Override
	public void close() {
		maintenancePlanGridPanel.close();
	}
}