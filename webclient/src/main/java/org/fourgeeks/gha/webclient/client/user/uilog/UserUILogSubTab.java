package org.fourgeeks.gha.webclient.client.user.uilog;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserTab;

public class UserUILogSubTab extends GHASubTab {

	private UserUILogGridPanel userUiLogGridPanel;

	public UserUILogSubTab(UserTab tab) {
		super("UI Log", tab);
		
		userUiLogGridPanel = new UserUILogGridPanel(this);
		addGHAClosableHandler(userUiLogGridPanel);
		addGHAHideableHandler(userUiLogGridPanel);

		setPane(userUiLogGridPanel);
	}
}