package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialSubTab extends GHASubTab {

	private EIATypeMaterialGridPanel gridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaterialSubTab(EIATypePanel tab) {
		super(GHAStrings.get("materials"), tab);

		gridPanel = new EIATypeMaterialGridPanel();
		addClosableListener(gridPanel);
		addHideableListener(gridPanel);

		setPane(gridPanel);

		tab.addEiaTypeSelectionListener(gridPanel);
	}
}
