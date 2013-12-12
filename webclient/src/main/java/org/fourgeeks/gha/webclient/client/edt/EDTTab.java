package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EDTTab extends GHATab {

	private final EDTTopMenu topMenu = new EDTTopMenu();
	private final EDTTopGridPanel topGridPanel = new EDTTopGridPanel();
	private final EDTBotGridPanel botGridPanel = new EDTBotGridPanel();

	/**
	 * @param token
	 */
	public EDTTab() {
		super();
		header = new GHATabHeader(this, GHAStrings.get("workstation"));
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
	public String getId() {
		return "";
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}