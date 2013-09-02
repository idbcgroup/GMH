package org.fourgeeks.gha.webclient.client.eia.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret
 * 
 */
public class EIAMaintPlanSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAMaintPlanGridPanel eiaMaintPlanGridPanel;

	{
		eiaMaintPlanGridPanel = new EIAMaintPlanGridPanel();
	}

	/**
	 * @param tab
	 */
	public EIAMaintPlanSubTab(EIATab tab) {
		super("Planes Mantenimiento", tab);
		tab.addEiaSelectionListener(this);
		addGHAClosableHandler(eiaMaintPlanGridPanel);
		addGHAHideableHandler(eiaMaintPlanGridPanel);
		setPane(eiaMaintPlanGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub

	}
}