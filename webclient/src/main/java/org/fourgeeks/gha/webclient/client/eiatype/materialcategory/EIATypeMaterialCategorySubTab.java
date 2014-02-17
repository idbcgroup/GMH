package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

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
	public EIATypeMaterialCategorySubTab(EIATypePanel tab) {
		super(GHAStrings.get("materials"));
		tab.addHideableListener(this);
		tab.addClosableListener(this);
		gridPanel = new EIATypeMaterialCategoryGridPanel();
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
