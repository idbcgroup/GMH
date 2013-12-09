package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.dropdownmenus.UserDropdownMenu;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.places.NeedLoginPlace;
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
public class HomePlace extends NeedLoginPlace {

	private static boolean HOME_HAS_BEEN_BUILT = false;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * 
	 */
	// private EIADispatchmentForm dispatchmentForm = new EIADispatchmentForm();
	// private EIAInstallationCertificateForm installationCertificateForm = new
	// EIAInstallationCertificateForm();

	public HomePlace(String token) throws LoginNeededException {
		super(token);
	}

	@Override
	public void show() {
		try {
			GHAPlaceSet.closeCurrentPlace(HideCloseAction.ASK);
		} catch (UnavailableToHideException e) {
			return;
		}
		if (HOME_HAS_BEEN_BUILT)
			return;
		HOME_HAS_BEEN_BUILT = true;
		// User box
		RootPanel.get("main-content").clear();
		RootPanel.get("main-content").setHeight(
				GHAUiHelper.getTabHeight() + "px");
		// RootPanel.get("user-info").clear();
		// RootPanel.get("menu-bar").clear();
		// RootPanel.get("main-content").removeStyleName("white-background");

		Bpu user = GHASessionData.getLoggedUser();

		final UserDropdownMenu userMenu = new UserDropdownMenu(user);

		HLayout userInfo = new HLayout();
		userInfo.setMembersMargin(10);
		userInfo.setStyleName("user-info");
		userInfo.setHeight("50px");
		userInfo.setDefaultLayoutAlign(Alignment.CENTER);

		GHALabel usernameLabel = new GHALabel(user.getCitizen().getFirstName()
				+ " " + user.getCitizen().getFirstLastName());
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

		GHAPlaceSet.buildMenu();
	}

	@Override
	public String getId() {
		return "home";
	}
}