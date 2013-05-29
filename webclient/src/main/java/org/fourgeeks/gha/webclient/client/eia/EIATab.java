package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATab;

public class EIATab extends GHATab {

	public static final String ID = "eia-tab";

	public EIATab() {
		super();
		setID(ID);
		
		EIASearchForm topformLayout = new EIASearchForm();
		
		setPane(topformLayout);
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getTitle() {
		return "Tipos de equipo";
	}

}
