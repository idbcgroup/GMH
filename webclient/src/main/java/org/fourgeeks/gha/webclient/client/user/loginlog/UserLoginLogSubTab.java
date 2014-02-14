package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserPanel;

/**
 * @author alacret
 * 
 */
public class UserLoginLogSubTab extends GHASubTab {

	private UserLoginLogGridPanel userLoginLogGridPanel;

	/**
	 * @param panel
	 */
	public UserLoginLogSubTab(UserPanel panel) {
		super(GHAStrings.get("login-log"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		userLoginLogGridPanel = new UserLoginLogGridPanel();
		setPane(userLoginLogGridPanel);

		panel.addUserSelectionListener(userLoginLogGridPanel);
	}

}