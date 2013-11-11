package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeMaterialGridPanel gridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaterialSubTab(EIATypeTab tab) {
		super(GHAStrings.get("materials"), tab);

		gridPanel = new EIATypeMaterialGridPanel();
		addClosableListener(gridPanel);
		addHideableListener(gridPanel);

		setPane(gridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		gridPanel.select(eiaType);
	}
}
