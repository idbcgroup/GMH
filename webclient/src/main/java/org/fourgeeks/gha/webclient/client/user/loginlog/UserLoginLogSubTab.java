package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret
 * 
 */
public class UserLoginLogSubTab extends GHASubTab {

	private UserLoginLogGridPanel userLoginLogGridPanel;

	/**
	 * @param tab
	 */
	public UserLoginLogSubTab(UserTab tab) {
		super(GHAStrings.get("login-log"), tab);

		userLoginLogGridPanel = new UserLoginLogGridPanel();
		setPane(userLoginLogGridPanel);

		tab.addUserSelectionListener(userLoginLogGridPanel);
	}

}