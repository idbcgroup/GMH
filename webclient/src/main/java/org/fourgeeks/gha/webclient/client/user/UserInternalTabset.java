package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.user.information.UserInformationSubTab;
import org.fourgeeks.gha.webclient.client.user.loginlog.UserLoginLogSubTab;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret, emiliot
 * 
 */
public class UserInternalTabset extends GHAInternalTabSet implements
		UserSelectionListener {

	private final UserInformationSubTab infoSubTab;
	// private UserCredentialsSubTab userCredentialsSubTab;
	private final UserLoginLogSubTab loginLogSubTab;

	// private UserPermissionSubTab permissionSubTab;
	// private UserUILogSubTab userUILogSubTab;

	/**
	 * @param tab
	 */
	public UserInternalTabset(UserTab tab) {
		super(tab);
		infoSubTab = new UserInformationSubTab(tab);
		hideables.add(infoSubTab);
		closables.add(infoSubTab);

		// userCredentialsSubTab = new UserCredentialsSubTab(userTab);

		loginLogSubTab = new UserLoginLogSubTab(tab);
		hideables.add(loginLogSubTab);
		closables.add(loginLogSubTab);

		// permissionSubTab = new UserPermissionSubTab(userTab);

		// userUILogSubTab = new UserUILogSubTab(userTab);

		addTab(infoSubTab);
		// addTab(userCredentialsSubTab);
		addTab(loginLogSubTab);
		// addTab(permissionSubTab);
		// addTab(userUILogSubTab);
	}

	@Override
	public void select(SSOUser ssoUser) {
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();
		if (selectedTab == infoSubTab)
			infoSubTab.show();
		animateShow(AnimationEffect.FADE);
	}

}
