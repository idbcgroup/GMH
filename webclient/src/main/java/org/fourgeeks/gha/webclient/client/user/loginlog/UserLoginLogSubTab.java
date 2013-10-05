package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
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
		super("Login Log", tab);
		setDisabled(true);
		tab.addUserSelectionListener(this);
		userLoginLogGridPanel = new UserLoginLogGridPanel();
		setPane(userLoginLogGridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		setDisabled(false);
		userLoginLogGridPanel.loadData(ssoUser);
	}

}