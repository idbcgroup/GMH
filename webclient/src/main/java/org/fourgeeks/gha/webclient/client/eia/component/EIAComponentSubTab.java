package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

import com.google.gwt.user.client.Window;

/**
 * @author alacret
 * 
 */
public class EIAComponentSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAComponentGridPanel eiaComponentGridPanel;

	/**
	 * @param tab
	 */
	public EIAComponentSubTab(EIATab tab) {
		super("Componentes", tab);
		tab.addEiaSelectionListener(this);
		eiaComponentGridPanel = new EIAComponentGridPanel();
		addGHAClosableHandler(eiaComponentGridPanel);
		addGHAHideableHandler(eiaComponentGridPanel);

		setPane(eiaComponentGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		eiaComponentGridPanel.select(eia);
	}
}