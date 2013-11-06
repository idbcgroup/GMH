package org.fourgeeks.gha.webclient.client.user.permissions;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret
 * 
 */
public class UserPermissionSubTab extends GHASubTab implements
		UserSelectionListener {

	private FunctionGridPanel gridPanel;

	/**
	 * @param tab
	 */
	public UserPermissionSubTab(UserTab tab) {
		super(GHAStrings.get("permissions"), tab);
		tab.addUserSelectionListener(this);
		gridPanel = new FunctionGridPanel(this);
		setPane(gridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		gridPanel.loadData(ssoUser);
	}

}