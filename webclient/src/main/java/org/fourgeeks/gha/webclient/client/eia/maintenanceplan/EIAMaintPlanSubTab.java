package org.fourgeeks.gha.webclient.client.eia.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
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
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		addClosableHandler(eiaMaintPlanGridPanel);
		addHideableHandler(eiaMaintPlanGridPanel);
		setPane(eiaMaintPlanGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}