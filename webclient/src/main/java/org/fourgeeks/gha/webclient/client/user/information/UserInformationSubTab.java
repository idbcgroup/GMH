package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserTab;

public class UserInformationSubTab extends GHASubTab {

	private UserInformationFormPanel form;

	public UserInformationSubTab(UserTab tab) {
		super("Información", tab);
		
		form = new UserInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}
}
