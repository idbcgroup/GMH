package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
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
	private static TreeSet<String> viewTreeSet;
	private static TreeSet<String> functionTreeSet;
	private static Map<String, String> permissionMap;

	/**
	 * @return the logged User
	 */
	public static Bpu getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @return if the user is logged
	 */
	public static boolean userisLogged() {
		return !(loggedUser == null);
	}

	/**
	 * @param loggedUser
	 */
	public static void setLoggedUser(Bpu loggedUser) {
		GHASessionData.loggedUser = loggedUser;
		viewTreeSet = new TreeSet<String>();
		functionTreeSet = new TreeSet<String>();
		permissionMap = new HashMap<String, String>();
		List<AppFormViewFunctionBpu> permissions = loggedUser.getPermissions();
		for (AppFormViewFunctionBpu permission : permissions) {
			viewTreeSet.add(permission.getView().getCode());
			functionTreeSet.add(permission.getFunction().getCode());
			permissionMap.put(permission.getAppForm().getToken(), permission
					.getAppForm().getName());
		}
		buildUserBox();
		GHAPlaceSet.buildMenu();
	}

	private static void buildUserBox() {
		final UserDropdownMenu userMenu = new UserDropdownMenu(loggedUser);

		HLayout userInfo = new HLayout();
		userInfo.setMembersMargin(10);
		userInfo.setStyleName("user-info");
		userInfo.setHeight("50px");
		userInfo.setDefaultLayoutAlign(Alignment.CENTER);

		GHALabel usernameLabel = new GHALabel(loggedUser.getCitizen()
				.getFirstName()
				+ " "
				+ loggedUser.getCitizen().getFirstLastName());
		usernameLabel.setStyleName("username-text");
		usernameLabel.setSize("400px", "25px");

		Img userButton = new Img("../resources/icons/boton2.png");
		userButton.setStyleName("button-pointer");
		userButton.setCursor(Cursor.POINTER);
		userButton.setSize("21px", "25px");

		userButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// int posx = event.getX() - 270;
						// int posy = event.getY();
						// if (event.getY() < 50)
						// posy += 20;
						// else
						// posy += 10;

						if (userMenu.isVisible()) {
							userMenu.hide();
						} else {
							userMenu.show();
						}
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
			public void onFocusChanged(FocusChangedEvent event) {
				Window.alert(event.getHasFocus() + "");

			}
		});

		RootPanel.get("user-info").add(userInfo);
	}

	/**
	 * @return the permissionMap
	 * @throws LoginNeededException
	 */
	public static Map<String, String> getPermissionMap()
			throws LoginNeededException {
		if (permissionMap == null)
			throw new LoginNeededException();
		return permissionMap;
	}

	/**
	 * @param code
	 * @return if it has permission for this AppForm
	 * @throws LoginNeededException
	 */
	public static boolean hasAppFormPermission(String code)
			throws LoginNeededException {
		if (permissionMap == null)
			throw new LoginNeededException();
		return permissionMap.containsKey(code);
	}

	/**
	 * @param code
	 * @return wheter this code is present on the permissions
	 * @throws LoginNeededException
	 */
	public static boolean hasViewPermission(String code)
			throws LoginNeededException {
		if (viewTreeSet == null)
			throw new LoginNeededException();
		return viewTreeSet.contains(code);
	}

	/**
	 * @param code
	 * @return wheter this code is present on the permissions
	 * @throws LoginNeededException
	 */
	public static boolean hasFunctionPermission(String code)
			throws LoginNeededException {
		if (functionTreeSet == null)
			throw new LoginNeededException();
		return functionTreeSet.contains(code);
	}

}
