package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

/**
 * @author emiliot
 *
 */
public class AsociatedEiaTypeSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener{

	private AsociatedEiatypeGridPanel eiatypeGridPanel;

	public AsociatedEiaTypeSubTab(MaintenancePlanTab tab) {
		super("Tipos de Equipo", tab);
		setDisabled(true);
		
		//register as maintenanceplan listener with the tab
		tab.addMaintenancePlanSelectionListener(this);
		
		eiatypeGridPanel = new AsociatedEiatypeGridPanel(this);
		addGHAClosableHandler(eiatypeGridPanel);
		addGHAHideableHandler(eiatypeGridPanel);
		
		setPane(eiatypeGridPanel);
	}

	//Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		eiatypeGridPanel.select(maintenancePlan);
		setDisabled(false);
	}
	
	

}