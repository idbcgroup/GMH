package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EDTTab extends GHATab{

	public static final String ID = "edt";
	private static final String TITLE = "Estacion De Trabajo";

	private EDTTopMenu topMenu = new EDTTopMenu();
	private EDTTopGridPanel topGridPanel = new EDTTopGridPanel();
	private EDTBotGridPanel botGridPanel = new EDTBotGridPanel();
	
	public EDTTab() {
		super();
		getHeader().setTitle(TITLE);
		
		// Creacion de la tab de EDT
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");
		
		verticalPanel.addMember(topMenu);
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(topGridPanel);
		verticalPanel.addMember(botGridPanel);
		
		addMember(verticalPanel);
	}

	@Override
	protected void onDraw() {
		
	}

	@Override
	public String getId() {
		return ID;
	}
}