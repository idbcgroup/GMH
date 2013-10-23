package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret Equipments sub tab
 */
public class EIAInformationSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAInformationFormPanel eiaInformationFormPanel = null;
	{
		eiaInformationFormPanel = new EIAInformationFormPanel();
	}

	/**
	 * @param tab
	 * 
	 */
	public EIAInformationSubTab(EIATab tab) {
		super("Información", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		eiaInformationFormPanel.addEiaSelectionListener(tab);
		addGHAClosableHandler(eiaInformationFormPanel);
		addGHAHideableHandler(eiaInformationFormPanel);
		setPane(eiaInformationFormPanel);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
		eiaInformationFormPanel.setEia(eia);
	}

	@Override
	public void hide() {
	}

}
