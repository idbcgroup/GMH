package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.dropdownmenus.UserDropdownMenu;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.FocusChangedEvent;
import com.smartgwt.client.widgets.events.FocusChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHASessionData {

	private static Bpu loggedUser;
	private static final TreeSet<String> viewTreeSet = new TreeSet<String>();
	private static final TreeSet<String> functionTreeSet = new TreeSet<String>();
	private static final Map<String, App> appMap = new HashMap<String, App>();

	private static void buildUserBox() {
		final UserDropdownMenu userMenu = new UserDropdownMenu(loggedUser);

		final HLayout userInfo = new HLayout();
		userInfo.setMembersMargin(10);
		userInfo.setStyleName("user-info");
		userInfo.setHeight("50px");
		userInfo.setDefaultLayoutAlign(Alignment.CENTER);

		final GHALabel usernameLabel = new GHALabel(loggedUser.getCitizen()
				.getFirstName()
				+ " "
				+ loggedUser.getCitizen().getFirstLastName());
		usernameLabel.setStyleName("username-text");
		usernameLabel.setSize("400px", "25px");

		final Img userButton = new Img("../resources/icons/boton2.png");
		userButton.setStyleName("button-pointer");
		userButton.setCursor(Cursor.POINTER);
		userButton.setSize("21px", "25px");

		userButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(final ClickEvent event) {
						if (userMenu.isVisible())
							userMenu.hide();
						else
							userMenu.show();
					}
				});

		userInfo.addMembers(usernameLabel,
		/* notificationsButton, */
		userButton
		// , notifbut
		);

		userInfo.addFocusChangedHandler(new FocusChangedHandler() {

			@Override
			public void onFocusChanged(final FocusChangedEvent event) {
				Window.alert(event.getHasFocus() + "");

			}
		});

		RootPanel.get("user-info").add(userInfo);
	}

	/**
	 * @return the permissionMap
	 * @throws LoginNeededException
	 */
	public static Map<String, App> getAppsMapp() throws LoginNeededException {
		if (appMap.isEmpty())
			throw new LoginNeededException();
		return appMap;
	}

	/**
	 * @return the logged User
	 */
	public static Bpu getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @param code
	 * @return if it has permission for this App
	 * @throws LoginNeededException
	 */
	public static boolean hasAppPermission(final String code)
			throws LoginNeededException {
		if (appMap.isEmpty())
			throw new LoginNeededException();
		return appMap.containsKey(code);
	}

	/**
	 * @param code
	 * @return wheter this code is present on the permissions
	 * @throws LoginNeededException
	 */
	public static boolean hasFunctionPermission(final String code)
			throws LoginNeededException {
		if (functionTreeSet == null)
			throw new LoginNeededException();
		return functionTreeSet.contains(code);
	}

	/**
	 * @param code
	 * @return wheter this code is present on the permissions
	 * @throws LoginNeededException
	 */
	public static boolean hasViewPermission(final String code)
			throws LoginNeededException {
		if (viewTreeSet == null)
			throw new LoginNeededException();
		return viewTreeSet.contains(code);
	}

	/**
	 * @param loggedUser
	 */
	public static void setLoggedUser(final Bpu loggedUser) {
		GHASessionData.loggedUser = loggedUser;
		final List<FunctionBpu> functions = loggedUser.getFunctions();
		for (final FunctionBpu functionBpu : functions)
			functionTreeSet.add(functionBpu.getFunction().getCode());

		final List<AppView> appsViews = loggedUser.getAppsViews();

		for (final AppView appView : appsViews) {
			viewTreeSet.add(appView.getView().getCode());
			appMap.put(appView.getApp().getToken(), appView.getApp());
		}
		buildUserBox();
		GHAPlaceSet.buildMenu();
	}

	/**
	 * @return if the user is logged
	 */
	public static boolean userisLogged() {
		return !(loggedUser == null);
	}

}
