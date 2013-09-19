package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeUtilitySubTab extends GHASubTab implements
		EIATypeSelectionListener, GHAClosable {

	private EIATypeUtilityGridPanel eiaTypeServicesGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeUtilitySubTab(EIATypeTab tab) {
		super("Servicios utilitarios", tab);
		setDisabled(true);
		tab.addEiaTypeSelectionListener(this);
		eiaTypeServicesGridPanel = new EIATypeUtilityGridPanel();
		addGHAClosableHandler(eiaTypeServicesGridPanel);
		addGHAHideableHandler(eiaTypeServicesGridPanel);

		setPane(eiaTypeServicesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		eiaTypeServicesGridPanel.select(eiaType);
		setDisabled(false);
	}
}
