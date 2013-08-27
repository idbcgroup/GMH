package org.fourgeeks.gha.webclient.client.maintenanceplan.equipments;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class MaintenancePlanEquipmentSubTab extends GHASubTab implements
		EIATypeSelectionListener, EIASelectionListener{

	private MaintenancePlanEquipmentGridPanel equiposGridPanel;

	public MaintenancePlanEquipmentSubTab(MaintenancePlanTab tab) {
		super("Equipos", tab);
		
		equiposGridPanel = new MaintenancePlanEquipmentGridPanel(this);
		addGHAClosableHandler(equiposGridPanel);
		addGHAHideableHandler(equiposGridPanel);
		
		setPane(equiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		equiposGridPanel.select(eia);
		
	}
}