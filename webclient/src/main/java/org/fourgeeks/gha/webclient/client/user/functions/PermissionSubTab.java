package org.fourgeeks.gha.webclient.client.user.functions;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserPanel;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;

/**
 * @author alacret
 * 
 */
public class PermissionSubTab extends GHASubTab implements
		UserSelectionListener {

	private FunctionGridPanel gridPanel;

	/**
	 * @param panel
	 */
	public PermissionSubTab(UserPanel panel) {
		super(GHAStrings.get("permissions"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		panel.addUserSelectionListener(this);
		gridPanel = new FunctionGridPanel();
		setPane(gridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		gridPanel.loadData(ssoUser);
	}

}