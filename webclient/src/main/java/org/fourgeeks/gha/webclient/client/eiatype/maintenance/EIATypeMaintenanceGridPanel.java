package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeMaintenanceGridPanel extends VLayout implements
		EIATypeSelectionListener,GHAClosable, GHAHideable {

	private EIATypeMaintenancePlanGridPanel maintenancePlanGridPanel = new EIATypeMaintenancePlanGridPanel();
	private EIATypeMaintenanceProtocolGridPanel maintenanceProtocolGridPanel = new EIATypeMaintenanceProtocolGridPanel();

	private GHASectionForm sectionForm;
	{
		sectionForm = new GHASectionForm();
		maintenancePlanGridPanel = new EIATypeMaintenancePlanGridPanel();
		maintenanceProtocolGridPanel = new EIATypeMaintenanceProtocolGridPanel();
	}

	public EIATypeMaintenanceGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!

		Label title = new Label("<h3>Planes y Protocolos de Mantenimiento</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");

		sectionForm.addSection("Planes", maintenancePlanGridPanel, true);
		sectionForm.addSection("Protocolos", maintenanceProtocolGridPanel,
				false);

		addMembers(title, sectionForm);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		super.hide();
		sectionForm.deactivate();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		sectionForm.deactivate();
	}
}