package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.user.information.UserInformationSubTab;
import org.fourgeeks.gha.webclient.client.user.loginlog.UserLoginLogSubTab;
import org.fourgeeks.gha.webclient.client.user.permissions.UserPermissionSubTab;

import com.smartgwt.client.types.AnimationEffect;

/**
 * @author alacret
 * 
 */
public class UserInternalTabset extends GHAInternalTabSet implements
		UserSelectionListener {

	private UserInformationSubTab userInformationSubTab;
	// private UserCredentialsSubTab userCredentialsSubTab;
	private UserLoginLogSubTab userLoginLogSubTab;
	private UserPermissionSubTab permissionSubTab;

	// private UserUILogSubTab userUILogSubTab;

	/**
	 * @param userTab
	 */
	public UserInternalTabset(UserTab userTab) {
		super(userTab);
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setVisible(false);
		// TODO normalize
		userInformationSubTab = new UserInformationSubTab(userTab);
		// userCredentialsSubTab = new UserCredentialsSubTab(userTab);
		userLoginLogSubTab = new UserLoginLogSubTab(userTab);
		permissionSubTab = new UserPermissionSubTab(userTab);
		// userUILogSubTab = new UserUILogSubTab(userTab);

		addTab(userInformationSubTab);
		// addTab(userCredentialsSubTab);
		addTab(userLoginLogSubTab);
		addTab(permissionSubTab);
		// addTab(userUILogSubTab);
	}

	@Override
	public void select(SSOUser ssoUser) {
		animateShow(AnimationEffect.FADE);
		selectTab(0);
	}

}
