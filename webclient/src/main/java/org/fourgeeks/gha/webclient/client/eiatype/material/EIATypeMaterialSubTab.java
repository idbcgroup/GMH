package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeMaterialGridPanel eiaTypeMaterialGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaterialSubTab(EIATypeTab tab) {
		super(GHAStrings.get("materials"), tab);

		eiaTypeMaterialGridPanel = new EIATypeMaterialGridPanel();
		addGHAClosableHandler(eiaTypeMaterialGridPanel);
		addGHAHideableHandler(eiaTypeMaterialGridPanel);

		setPane(eiaTypeMaterialGridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		eiaTypeMaterialGridPanel.select(eiaType);
	}
}
