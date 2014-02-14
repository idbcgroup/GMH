package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

/**
 * @author alacret
 * 
 */
public class EIAMaterialSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAMaterialGridPanel eiaMaterialGridPanel;

	/**
	 * @param panel
	 */
	public EIAMaterialSubTab(EIAPanel panel) {
		super("Materiales");// TODO Uistrings
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		setDisabled(true);
		panel.addEiaSelectionListener(this);

		eiaMaterialGridPanel = new EIAMaterialGridPanel();
		setPane(eiaMaterialGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}
