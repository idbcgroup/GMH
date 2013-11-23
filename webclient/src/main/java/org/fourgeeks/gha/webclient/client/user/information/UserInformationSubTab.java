package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret, emiliot
 * 
 */
public class UserInformationSubTab extends GHASubTab {

	private UserInformationFormPanel form;

	/**
	 * @param tab
	 */
	public UserInformationSubTab(UserTab tab) {
		super(GHAStrings.get("information"), tab);

		form = new UserInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);
		setPane(form);

		tab.addUserSelectionListener(form);
		form.addUserSelectionListener(tab);
	}

	public void show() {
		form.show();
	}
}
