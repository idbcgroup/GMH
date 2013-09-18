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
		tab.addUserSelectionListener(this);
		userLoginLogGridPanel = new UserLoginLogGridPanel(this);
		addGHAClosableHandler(userLoginLogGridPanel);
		addGHAHideableHandler(userLoginLogGridPanel);

		setPane(userLoginLogGridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		// TODO Auto-generated method stub

	}

}