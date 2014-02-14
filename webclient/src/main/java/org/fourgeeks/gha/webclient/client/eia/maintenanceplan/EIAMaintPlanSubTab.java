package org.fourgeeks.gha.webclient.client.eia.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

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
	 * @param panel
	 */
	public EIAMaintPlanSubTab(EIAPanel panel) {
		super("Planes Mantenimiento");// TODO Uistrings
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		setDisabled(true);
		panel.addEiaSelectionListener(this);
		addClosableListener(eiaMaintPlanGridPanel);
		addHideableListener(eiaMaintPlanGridPanel);
		setPane(eiaMaintPlanGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}