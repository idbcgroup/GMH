package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
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
		tab.addUserSelectionListener(this);
		form = new UserCredentialsFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);

		setPane(form);
	}

	@Override
	public void select(SSOUser ssoUser) {
		// TODO Auto-generated method stub

	}
}
