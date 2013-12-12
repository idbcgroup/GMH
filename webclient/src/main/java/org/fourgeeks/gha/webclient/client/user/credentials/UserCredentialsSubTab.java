package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserPanel;

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
	public UserCredentialsSubTab(UserPanel tab) {
		super("Credenciales", tab);
		setDisabled(true);
		tab.addUserSelectionListener(this);
		form = new UserCredentialsFormPanel(tab);
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);
	}

	@Override
	public void select(SSOUser ssoUser) {
		setDisabled(false);

	}
}
