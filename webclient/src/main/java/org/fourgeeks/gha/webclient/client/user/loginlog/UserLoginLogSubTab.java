package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret
 * 
 */
public class UserLoginLogSubTab extends GHASubTab implements
		UserSelectionListener {

	private UserLoginLogGridPanel userLoginLogGridPanel;

	/**
	 * @param tab
	 */
	public UserLoginLogSubTab(UserTab tab) {
		super(GHAStrings.get("login-log"), tab);
		tab.addUserSelectionListener(this);
		userLoginLogGridPanel = new UserLoginLogGridPanel();
		setPane(userLoginLogGridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		userLoginLogGridPanel.loadData(ssoUser);
	}

}