package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserPanel;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;

/**
 * @author alacret
 * 
 */
public class UserCredentialsSubTab extends GHASubTab implements
		UserSelectionListener {

	private UserCredentialsFormPanel form;

	/**
	 * @param panel
	 */
	public UserCredentialsSubTab(UserPanel panel) {
		super("Credenciales");// TODO Uistrings
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		setDisabled(true);
		panel.addUserSelectionListener(this);
		form = new UserCredentialsFormPanel(panel);
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);
	}

	@Override
	public void select(SSOUser ssoUser) {
		setDisabled(false);

	}
}
