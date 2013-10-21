package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EDTTab extends GHATab {

	public static final String ID = "edt";
	private static final String TITLE = "Estacion De Trabajo";

	private EDTTopMenu topMenu = new EDTTopMenu();
	private EDTTopGridPanel topGridPanel = new EDTTopGridPanel();
	private EDTBotGridPanel botGridPanel = new EDTBotGridPanel();

	/**
	 * @param token
	 */
	public EDTTab(String token) {
		super(token);
		// getHeader().setTitle(TITLE);

		// Creacion de la tab de EDT
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topMenu);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
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

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}
}