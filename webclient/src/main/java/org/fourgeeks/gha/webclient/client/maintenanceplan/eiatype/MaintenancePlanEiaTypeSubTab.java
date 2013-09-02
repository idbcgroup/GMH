package org.fourgeeks.gha.webclient.client.maintenanceplan.eiatype;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class MaintenancePlanEiaTypeSubTab extends GHASubTab implements
		EIATypeSelectionListener, EIASelectionListener{

	private MaintenancePlanEiatypeGridPanel eiatypeGridPanel;

	public MaintenancePlanEiaTypeSubTab(MaintenancePlanTab tab) {
		super("Tipos de Equipo", tab);
		
		eiatypeGridPanel = new MaintenancePlanEiatypeGridPanel(this);
		addGHAClosableHandler(eiatypeGridPanel);
		addGHAHideableHandler(eiatypeGridPanel);
		
		setPane(eiatypeGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		eiatypeGridPanel.select(eiaType);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		eiatypeGridPanel.select(eia);
		
	}
}