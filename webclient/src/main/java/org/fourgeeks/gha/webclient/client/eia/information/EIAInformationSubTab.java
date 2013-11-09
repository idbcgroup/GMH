package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret, emiliot Equipments sub tab
 */
public class EIAInformationSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAInformationFormPanel form;

	/**
	 * @param tab
	 * 
	 */
	public EIAInformationSubTab(EIATab tab) {
		super(GHAStrings.get("information"), tab);

		form = new EIAInformationFormPanel();
		addClosableHandler(form);
		addHideableHandler(form);

		setPane(form);

		form.addEiaSelectionListener(tab);
		tab.addEiaSelectionListener(this);
	}

	@Override
	public void select(Eia eia) {
		form.setEia(eia);
	}
}
