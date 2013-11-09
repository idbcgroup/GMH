package org.fourgeeks.gha.webclient.client.eia.spares;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

public class EIASpareSubTab extends GHASubTab implements EIASelectionListener{
	
	private EIASpareGridPanel eiaSpareGridPanel = null;
	
	public EIASpareSubTab(EIATab tab) {
		super("Repuestos", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		eiaSpareGridPanel = new EIASpareGridPanel();
		addClosableHandler(eiaSpareGridPanel);
		addHideableHandler(eiaSpareGridPanel);
		
		setPane(eiaSpareGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}
