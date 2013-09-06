package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SingleSignOnUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;

import com.smartgwt.client.widgets.layout.VLayout;

public class UserTab extends GHATab {

	public static final String ID = "user";
	private static final String TITLE = "Usuarios";
	private UserTopSection topSection;
	private UserInternalTabset internalTabset;

	private SingleSignOnUser user;
	
	public UserTab() {
		super();
		getHeader().setTitle(TITLE);

		topSection = new UserTopSection(this);
		internalTabset = new UserInternalTabset(this);

		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);

	}

	@Override
	protected void onDraw() {
		if (user == null)
			topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}
}