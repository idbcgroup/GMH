package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
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
public class UserInternalTabset extends TabSet implements ResizeHandler,
		GHAHideable, GHAClosable, UserSelectionListener {

	private UserInformationSubTab userInformationSubTab;
	// private UserCredentialsSubTab userCredentialsSubTab;
	private UserLoginLogSubTab userLoginLogSubTab;
	private UserPermissionSubTab permissionSubTab;
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();

	// private UserUILogSubTab userUILogSubTab;

	/**
	 * @param userTab
	 */
	public UserInternalTabset(UserTab userTab) {
		super();
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
	public boolean canBeClosen() {
		for (GHAClosable closable : closables)
			if (!closable.canBeClosen())
				return false;
		return true;
	}

	@Override
	public boolean canBeHidden() {
		for (GHAHideable hideable : hideables)
			if (!hideable.canBeHidden())
				return false;
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		for (GHAClosable closable : closables)
			closable.close();
		destroy();
	}

	@Override
	public void hide() {
		for (GHAHideable hideable : hideables)
			hideable.hide();
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void select(SSOUser ssoUser) {
		// TODO Auto-generated method stub

	}

}
