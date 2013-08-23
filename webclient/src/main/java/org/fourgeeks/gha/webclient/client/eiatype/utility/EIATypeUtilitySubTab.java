package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
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

		eiaTypeServicesGridPanel = new EIATypeUtilityGridPanel();
		addGHAClosableHandler(eiaTypeServicesGridPanel);
		addGHAHideableHandler(eiaTypeServicesGridPanel);

		setPane(eiaTypeServicesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		eiaTypeServicesGridPanel.select(eiaType);

	}
}
