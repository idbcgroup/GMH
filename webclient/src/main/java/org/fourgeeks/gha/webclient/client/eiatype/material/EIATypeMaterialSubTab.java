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
	 * @param panel
	 */
	public EIATypeMaterialSubTab(EIATypePanel panel) {
		super(GHAStrings.get("materials"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		gridPanel = new EIATypeMaterialGridPanel();
		addClosableListener(gridPanel);
		addHideableListener(gridPanel);

		setPane(gridPanel);

		panel.addEiaTypeSelectionListener(gridPanel);
	}
}
