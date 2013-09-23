package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret
 * 
 */
public class UserInformationSubTab extends GHASubTab implements
		UserSelectionListener {

	private UserInformationFormPanel form;

	/**
	 * @param tab
	 */
	public UserInformationSubTab(UserTab tab) {
		super("Información", tab);
		setDisabled(true);
		tab.addUserSelectionListener(this);
		form = new UserInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		setPane(form);
		
		//register to listen for selected user
		tab.addUserSelectionListener(this);
	}

	@Override
	public void select(SSOUser ssoUser) {
		setDisabled(false);
		form.select(ssoUser);
	}
}
