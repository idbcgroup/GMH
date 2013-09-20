package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.google.gwt.user.client.Window;

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
		setPane(userLoginLogGridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		Window.alert("2");
		userLoginLogGridPanel.loadData(ssoUser);
	}

}