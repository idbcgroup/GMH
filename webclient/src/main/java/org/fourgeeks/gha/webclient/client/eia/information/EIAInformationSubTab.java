package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

import com.google.gwt.user.client.Window;

/**
 * @author alacret Equipments sub tab
 */
public class EIAInformationSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAInformationFormPanel eiaInformationFormPanel = null;
	{
		eiaInformationFormPanel = new EIAInformationFormPanel();
	}

	/**
	 * 
	 */
	public EIAInformationSubTab(EIATab tab) {
		super("Información", tab);
		tab.addEiaSelectionListener(this);
		addGHAClosableHandler(eiaInformationFormPanel);
		addGHAHideableHandler(eiaInformationFormPanel);
		setPane(eiaInformationFormPanel);
	}

	@Override
	public void select(Eia eia) {
		Window.alert("InformationSubTab dice: ha sido seleccionado un Eia. Esto quiere decir que funciona para todas");
	}

}
