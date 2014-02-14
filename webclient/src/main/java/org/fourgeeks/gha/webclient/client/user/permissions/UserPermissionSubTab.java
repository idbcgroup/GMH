package org.fourgeeks.gha.webclient.client.user.permissions;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserPanel;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;

/**
 * @author alacret
 * 
 */
public class UserPermissionSubTab extends GHASubTab implements
		UserSelectionListener {

	private AppFormViewFunctionGridPanel gridPanel;

	/**
	 * @param panel
	 */
	public UserPermissionSubTab(UserPanel panel) {
		super(GHAStrings.get("permissions"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		panel.addUserSelectionListener(this);
		gridPanel = new AppFormViewFunctionGridPanel();
		setPane(gridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		gridPanel.loadData(ssoUser);
	}

}