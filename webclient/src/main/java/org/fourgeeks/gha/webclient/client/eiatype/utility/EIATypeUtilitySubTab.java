package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeUtilitySubTab extends GHASubTab implements
		EIATypeSelectionListener, ClosableListener {

	private EIATypeUtilityGridPanel eiaTypeServicesGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeUtilitySubTab(EIATypeTab tab) {
		super(GHAStrings.get("utility-services"), tab);

		eiaTypeServicesGridPanel = new EIATypeUtilityGridPanel();
		addClosableHandler(eiaTypeServicesGridPanel);
		addHideableHandler(eiaTypeServicesGridPanel);

		setPane(eiaTypeServicesGridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		eiaTypeServicesGridPanel.select(eiaType);
	}
}
