package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialCategorySubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeMaterialCategoryGridPanel gridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaterialCategorySubTab(EIATypeTab tab) {
		super(GHAStrings.get("materials"), tab);

		gridPanel = new EIATypeMaterialCategoryGridPanel();
		addGHAClosableHandler(gridPanel);
		addGHAHideableHandler(gridPanel);

		setPane(gridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		gridPanel.select(eiaType);
	}
}
