package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.user.information.UserInformationSubTab;
import org.fourgeeks.gha.webclient.client.user.loginlog.UserLoginLogSubTab;
import org.fourgeeks.gha.webclient.client.user.permissions.UserPermissionSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret
 * 
 */
public class UserInternalTabset extends TabSet implements ResizeHandler {

	private UserInformationSubTab userInformationSubTab;
	// private UserCredentialsSubTab userCredentialsSubTab;
	private UserLoginLogSubTab userLoginLogSubTab;
	private UserPermissionSubTab permissionSubTab;

	// private UserUILogSubTab userUILogSubTab;

	/**
	 * @param userTab
	 */
	public UserInternalTabset(UserTab userTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);
		
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
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
