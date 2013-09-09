package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserTab;

public class UserCredentialsSubTab extends GHASubTab {

	private UserCredentialsFormPanel form;

	public UserCredentialsSubTab(UserTab tab) {
		super("Credenciales", tab);
		
		form = new UserCredentialsFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}
}
