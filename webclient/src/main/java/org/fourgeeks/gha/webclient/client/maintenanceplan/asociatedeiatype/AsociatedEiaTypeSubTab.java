package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class AsociatedEiaTypeSubTab extends GHASubTab implements
		EIATypeSelectionListener, EIASelectionListener{

	private AsociatedEiatypeGridPanel eiatypeGridPanel;

	public AsociatedEiaTypeSubTab(MaintenancePlanTab tab) {
		super("Tipos de Equipo", tab);
		
		eiatypeGridPanel = new AsociatedEiatypeGridPanel(this);
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