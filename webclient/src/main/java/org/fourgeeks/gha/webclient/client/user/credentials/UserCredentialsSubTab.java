package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret
 * 
 */
public class UserCredentialsSubTab extends GHASubTab implements
		UserSelectionListener {

	private UserCredentialsFormPanel form;

	/**
	 * @param tab
	 */
	public UserCredentialsSubTab(UserTab tab) {
		super("Credenciales", tab);
		setDisabled(true);
		tab.addUserSelectionListener(this);
		form = new UserCredentialsFormPanel(tab);
		addClosableHandler(form);
		addHideableHandler(form);

		setPane(form);
	}

	@Override
	public void select(SSOUser ssoUser) {
		setDisabled(false);

	}
}
