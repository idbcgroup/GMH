package org.fourgeeks.gha.webclient.client.UI.places;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuBar;
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuOption;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public final class GHAPlaceSet {

	private final static Map<String, GHAPlace> places;
	private static GHAPlace currentPlace;
	private final static HorizontalPanel hPanel;
	private static GHAMenuBar verticalMenu;
	static {
		places = new HashMap<String, GHAPlace>();
		hPanel = new HorizontalPanel();
		hPanel.setHeight(GHAUiHelper.MENU_BAR_HEIGTH + "px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		RootPanel.get("menu-bar").add(hPanel);
	}

	private GHAPlaceSet() {
		throw new UnsupportedOperationException(
				"this class can be instantiaded");
	}

	private static void addPlace(final GHAPlace place) {
		places.put(place.getId(), place);
		RootPanel rootPanel = RootPanel.get("main-content");
		rootPanel.add(place);
		GHAPlaceHeader header = place.getHeader();
		if (header != null)
			hPanel.add(header);
	}

	/**
	 * @param place
	 * @throws UnavailableToHideException
	 */
	public static void showPlace(GHAPlace place)
			throws UnavailableToHideException {
		if (place == null)
			return;
		if (place == currentPlace) {
			place.updateToken(History.getToken());
			return;
		}
		if (currentPlace != null)
			try {
				hidePlace(currentPlace);
			} catch (UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}

		if (places.get(place.getId()) == null)
			addPlace(place);

		place.show();
		place.updateToken(History.getToken());
		currentPlace = place;
	}

	private static void hidePlace(GHAPlace tab)
			throws UnavailableToHideException {
		hidePlace(tab, HideCloseAction.ASK);
	}

	private static void hidePlace(GHAPlace place, HideCloseAction hideAction) {
		if (place.canBeHidden(hideAction)) {
			try {
				place.hide();
			} catch (UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}
			return;
		}
		throw new UnavailableToHideException(null);
	}

	/**
	 * @param hideAction
	 * @throws UnavailableToHideException
	 */
	public static void hideCurrentPlace(HideCloseAction hideAction)
			throws UnavailableToHideException {
		hidePlace(currentPlace, hideAction);
		currentPlace = null;
		History.forward();
	}

	/**
	 * @param hideAction
	 * @throws UnavailableToHideException
	 */
	public static void closeCurrentPlace(HideCloseAction hideAction)
			throws UnavailableToHideException {
		closePlace(currentPlace, hideAction);
	}

	/**
	 * @param id
	 * @return the tab with that ID
	 */
	public static GHAPlace getById(String id) {
		return places.get(id);
	}

	/**
	 * @param place
	 * @throws UnavailableToCloseException
	 */
	public static void closePlace(final GHAPlace place) {
		closePlace(place, HideCloseAction.ASK);
	}

	private static void closePlace(final GHAPlace place,
			HideCloseAction closeAction) throws UnavailableToCloseException {
		if (place == null)
			return;

		if (place.canBeClosen(closeAction)) {
			try {
				place.close();
			} catch (UnavailableToCloseException e) {
				throw new UnavailableToCloseException(e);
			}
			places.remove(place.getId());
			hPanel.remove(place.getHeader());
			// showing the last tab open
			Set<String> keySet = places.keySet();
			if (keySet.isEmpty()) {
				History.newItem("");
				currentPlace = null;
			} else {
				String next = keySet.iterator().next();
				History.newItem(next);
				currentPlace = places.get(next);
			}
			return;
		}
		throw new UnavailableToCloseException(null);
	}

	/**
	 * Build the Menu
	 */
	public static void buildMenu() {
		GHAImgButton menu = new GHAImgButton("../resources/icons/menu.png");
		menu.setSize("34px", "22px");
		menu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				verticalMenu.bringToFront();
				if (!verticalMenu.isVisible()) {
					verticalMenu.open();
				} else {
					verticalMenu.animateHide(AnimationEffect.FLY);
					GHAUiHelper.removeDocumentMouseOverHandler(verticalMenu);
				}
			}
		});
		hPanel.add(menu);
		verticalMenu = new GHAMenuBar(menu);
		GHAUiHelper.addGHAResizeHandler(verticalMenu);

		Bpu user = GHASessionData.getLoggedUser();
		List<GHAMenuOption> menuOptions = getMenuOptions(user);
		for (GHAMenuOption ghaMenuOption : menuOptions)
			verticalMenu.addOption(ghaMenuOption);
	}

	/**
	 * @return the list of menu option
	 */
	private static List<GHAMenuOption> getMenuOptions(Bpu loggedUser) {
		List<GHAMenuOption> menuOptions = new ArrayList<GHAMenuOption>();
		try {
			Map<String, String> permissionMap = GHASessionData
					.getPermissionMap();
			Set<Entry<String, String>> entrySet = permissionMap.entrySet();

			for (final Entry<String, String> entry : entrySet) {
				menuOptions.add(new GHAMenuOption(GHAStrings.get(entry
						.getValue()), entry.getKey(),
						"../resources/icons/menu/" + entry.getKey() + ".png"));
			}
			return menuOptions;
		} catch (LoginNeededException e) {
			return menuOptions;
		}

	}

	/**
	 * @param historyToken
	 */
	public static void showPlace(String historyToken) {
		int indexOf = historyToken.indexOf("/");
		final String token;
		if (indexOf == -1)
			token = historyToken;
		else
			token = historyToken.substring(0, indexOf);
		GHAPlace place = places.get(token);
		if (place == null)
			GHAPlacesFactory.showPlace(token);
		else
			showPlace(place);
	}

}