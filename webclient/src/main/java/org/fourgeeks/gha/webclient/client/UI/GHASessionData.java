package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.ess.ui.Menu;
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
	private static TreeSet<String> viewTreeSet = new TreeSet<String>();
	private static TreeSet<String> functionTreeSet = new TreeSet<String>();
	private static Map<String, String> appMap = new HashMap<String, String>();

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

		// GHAButton notifbut= new GHAButton("Show", new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// // TODO Auto-generated method stub
		// GHANotification.modalNotification.show("Informeichon",
		// "Informacion del error");
		// }
		// });

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
	public static Map<String, String> getAppsMapp() throws LoginNeededException {
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

	private static void printParents(final Menu menu) {
		String text = menu.getText();
		Window.alert(text);
		if (menu.getParentMenu() != null)
			printParents(menu.getParentMenu());
	}

	/**
	 * @param loggedUser
	 */
	public static void setLoggedUser(final Bpu loggedUser) {
		Window.alert("logged User");
		GHASessionData.loggedUser = loggedUser;
		final List<Function> functions = loggedUser.getFunctions();
		for (final Function function : functions)
			functionTreeSet.add(function.getCode());

		final List<AppView> appsViews = loggedUser.getAppsViews();
		final List<App> apps = new ArrayList<App>();

		for (final AppView appView : appsViews) {
			viewTreeSet.add(appView.getView().getCode());
			appMap.put(appView.getApp().getToken(), appView.getApp().getName());
			apps.add(appView.getApp());
		}
		Window.alert("before user bos");
		buildUserBox();
		Window.alert("before user bos");
		GHAPlaceSet.buildMenu();
		Window.alert("before user bos");

		// TEST
		Window.alert("start testing" + apps.size());
		StringBuilder sb = new StringBuilder();
		for (App app : apps) {
			sb.append(app.getName());
			if (app.getMenu() != null) {
				sb.append(app.getMenu().getText());
				if (app.getMenu().getParentMenu() != null)
					sb.append(app.getMenu().getParentMenu().getText());
			}
			sb.append(" \n");
		}
		Window.alert("stop testing");
		Window.alert(sb.toString());
		for (App app : apps) {
			Menu menu = app.getMenu();
			printParents(menu);
		}
	}

	/**
	 * @return if the user is logged
	 */
	public static boolean userisLogged() {
		return !(loggedUser == null);
	}

}
