package org.fourgeeks.gha.webclient.client.eiatype.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeInformationSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeInformationFormPanel form;

	/**
	 * @param tab
	 */
	public EIATypeInformationSubTab(EIATypeTab tab) {
		super("Información", tab);
		setDisabled(true);

		form = new EIATypeInformationFormPanel();
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);

		setPane(form);

		form.addEiaTypeSelectionListener(tab);
		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		form.setEiaType(eiaType);
		setDisabled(false);
	}
}
