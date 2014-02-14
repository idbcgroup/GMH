package org.fourgeeks.gha.webclient.client.eia.spares;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

/**
 * @author alacret
 * 
 */
public class EIASpareSubTab extends GHASubTab implements EIASelectionListener {

	private EIASpareGridPanel eiaSpareGridPanel = null;

	/**
	 * @param panel
	 */
	public EIASpareSubTab(EIAPanel panel) {
		super("Repuestos"); // TODO Uistrings
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		setDisabled(true);
		panel.addEiaSelectionListener(this);
		eiaSpareGridPanel = new EIASpareGridPanel();
		addClosableListener(eiaSpareGridPanel);
		addHideableListener(eiaSpareGridPanel);

		setPane(eiaSpareGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}
