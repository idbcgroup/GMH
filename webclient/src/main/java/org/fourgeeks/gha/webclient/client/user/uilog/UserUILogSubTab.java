package org.fourgeeks.gha.webclient.client.user.uilog;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

/**
 * @author alacret
 * 
 */
public class UserUILogSubTab extends GHASubTab implements UserSelectionListener {

	private UserUILogGridPanel userUiLogGridPanel;

	/**
	 * @param tab
	 */
	public UserUILogSubTab(UserTab tab) {
		super("UI Log", tab);
		tab.addUserSelectionListener(this);
		userUiLogGridPanel = new UserUILogGridPanel(this);
		addGHAClosableHandler(userUiLogGridPanel);
		addGHAHideableHandler(userUiLogGridPanel);

		setPane(userUiLogGridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		// TODO Auto-generated method stub

	}
}