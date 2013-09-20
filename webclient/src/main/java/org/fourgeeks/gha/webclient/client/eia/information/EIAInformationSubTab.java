package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
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
	 * 
	 */
	public EIAInformationSubTab(EIATab tab) {
		super("Información", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		addGHAClosableHandler(eiaInformationFormPanel);
		addGHAHideableHandler(eiaInformationFormPanel);
		setPane(eiaInformationFormPanel);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
		eiaInformationFormPanel.setEia(eia);
	}

}
