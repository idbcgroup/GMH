package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.user.credentials.UserCredentialsSubTab;
import org.fourgeeks.gha.webclient.client.user.information.UserInformationSubTab;
import org.fourgeeks.gha.webclient.client.user.loginlog.UserLoginLogSubTab;
import org.fourgeeks.gha.webclient.client.user.uilog.UserUILogSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class UserInternalTabset extends TabSet implements ResizeHandler {

	private UserInformationSubTab userInformationSubTab;
	private UserCredentialsSubTab userCredentialsSubTab;
	private UserLoginLogSubTab userLoginLogSubTab;
	private UserUILogSubTab userUILogSubTab;
//	private EIACostSubTab eiaCostsSubTab;
//	private EIAMovementsSubTab eiaMovementsSubTab;
		
	public UserInternalTabset(UserTab mpTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		userInformationSubTab = new UserInformationSubTab(mpTab);
		userCredentialsSubTab = new UserCredentialsSubTab(mpTab);
		userLoginLogSubTab = new UserLoginLogSubTab(mpTab);
		userUILogSubTab = new UserUILogSubTab(mpTab);
//		eiaCostsSubTab = new EIACostSubTab(mpTab);
//		eiaMovementsSubTab = new EIAMovementsSubTab(mpTab);
		
		// Agregando las Subtabs
		addTab(userInformationSubTab);
		addTab(userCredentialsSubTab);
		addTab(userLoginLogSubTab);
		addTab(userUILogSubTab);
//		addTab(eiaCostsSubTab);
//		addTab(eiaMovementsSubTab);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
