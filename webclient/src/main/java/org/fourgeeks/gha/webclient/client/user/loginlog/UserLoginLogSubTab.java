package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserTab;

public class UserLoginLogSubTab extends GHASubTab {

	private UserLoginLogGridPanel userLoginLogGridPanel;

	public UserLoginLogSubTab(UserTab tab) {
		super("Login Log", tab);
		
		userLoginLogGridPanel = new UserLoginLogGridPanel(this);
		addGHAClosableHandler(userLoginLogGridPanel);
		addGHAHideableHandler(userLoginLogGridPanel);

		setPane(userLoginLogGridPanel);
	}

}