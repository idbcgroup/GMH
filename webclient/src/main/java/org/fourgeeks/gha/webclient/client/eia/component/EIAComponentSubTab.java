package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

/**
 * @author alacret
 * 
 */
public class EIAComponentSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAComponentGridPanel componentGridPanel;

	/**
	 * @param panel
	 */
	public EIAComponentSubTab(EIAPanel panel) {
		super(GHAStrings.get("components"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		componentGridPanel = new EIAComponentGridPanel();
		addClosableListener(componentGridPanel);
		addHideableListener(componentGridPanel);

		setPane(componentGridPanel);

		panel.addEiaSelectionListener(this);
	}

	@Override
	public void select(Eia eia) {
		componentGridPanel.select(eia);
	}
}