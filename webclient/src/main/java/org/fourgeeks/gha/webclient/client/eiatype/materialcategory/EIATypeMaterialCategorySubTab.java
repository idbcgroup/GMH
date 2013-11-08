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

	private EIATypeMaterialCategoryGridPanel eiaTypeMaterialGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaterialCategorySubTab(EIATypeTab tab) {
		super(GHAStrings.get("materials"), tab);

		eiaTypeMaterialGridPanel = new EIATypeMaterialCategoryGridPanel();
		addClosableHandler(eiaTypeMaterialGridPanel);
		addHideableHandler(eiaTypeMaterialGridPanel);

		setPane(eiaTypeMaterialGridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		eiaTypeMaterialGridPanel.select(eiaType);
	}
}
