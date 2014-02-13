package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserPanel;

/**
 * @author alacret, emiliot
 * 
 */
public class UserInformationSubTab extends GHASubTab {

	private UserInformationFormPanel form;

	/**
	 * @param panel
	 */
	public UserInformationSubTab(UserPanel panel) {
		super(GHAStrings.get("information"), panel);

		form = new UserInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);
		setPane(form);

		panel.addUserSelectionListener(form);
		form.addUserSelectionListener(panel);
	}

	/**
	 * 
	 */
	public void show() {
		form.show();
	}
}
