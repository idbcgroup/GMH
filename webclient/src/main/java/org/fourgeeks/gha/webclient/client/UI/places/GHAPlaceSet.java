package org.fourgeeks.gha.webclient.client.UI.places;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author alacret
 * 
 */
public final class GHAPlaceSet {

	private static GHAPlace placeWaitingToShow = null;

	private final static Map<String, GHAPlace> places;
	private static GHAPlace currentPlace;
	private final static HorizontalPanel hPanel;
	static {
		places = new HashMap<String, GHAPlace>();
		hPanel = new HorizontalPanel();
		hPanel.setHeight(GHAUiHelper.MENU_BAR_HEIGTH + "px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(GHAMenu.getMenuButton());
		RootPanel.get("menu-bar").add(hPanel);
	}

	private static void addPlace(final GHAPlace place) {
		places.put(place.getId(), place);
		final RootPanel rootPanel = RootPanel.get("main-content");
		rootPanel.add(place);
		final GHAPlaceHeader header = place.getHeader();
		if (header != null)
			hPanel.add(header);
	}

	/**
	 * 
	 */
	public static void buildMenu() {
		GHAMenu.build();
	}

	/**
	 * @param hideAction
	 * @throws UnavailableToHideException
	 */
	public static void closeCurrentPlace(final HideCloseAction hideAction)
			throws UnavailableToHideException {
		closePlace(currentPlace, hideAction);
	}

	/**
	 * @param place
	 * @throws UnavailableToCloseException
	 */
	public static void closePlace(final GHAPlace place) {
		closePlace(place, HideCloseAction.ASK);
	}

	private static void closePlace(final GHAPlace place,
			final HideCloseAction closeAction)
			throws UnavailableToCloseException {
		if (place == null)
			return;

		if (place.canBeClosen(closeAction)) {
			try {
				place.close();
			} catch (final UnavailableToCloseException e) {
				throw new UnavailableToCloseException(e);
			}
			places.remove(place.getId());
			hPanel.remove(place.getHeader());
			// showing the last tab open
			final Set<String> keySet = places.keySet();
			if (keySet.isEmpty()) {
				History.newItem("");
				currentPlace = null;
			} else {
				final String next = keySet.iterator().next();
				History.newItem(next);
				currentPlace = places.get(next);
			}
			return;
		}
		throw new UnavailableToCloseException(null);
	}

	/**
	 * @param id
	 * @return the tab with that ID
	 */
	public static GHAPlace getById(final String id) {
		return places.get(id);
	}

	/**
	 * @param hideAction
	 * @throws UnavailableToHideException
	 */
	public static void hideCurrentPlace(final HideCloseAction hideAction)
			throws UnavailableToHideException {
		hidePlace(currentPlace, hideAction);
		currentPlace = null;
		History.forward();
	}

	private static void hidePlace(final GHAPlace tab)
			throws UnavailableToHideException {
		hidePlace(tab, HideCloseAction.ASK);
	}

	private static void hidePlace(final GHAPlace place,
			final HideCloseAction hideAction) {
		if (place.canBeHidden(hideAction)) {
			try {
				place.hide();
				if (placeWaitingToShow != null) {
					if (places.get(placeWaitingToShow.getId()) == null)
						addPlace(placeWaitingToShow);
					placeWaitingToShow.show();
					placeWaitingToShow.updateToken(History.getToken());
					currentPlace = placeWaitingToShow;
					placeWaitingToShow = null;
				}
			} catch (final UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}
			return;
		}
		throw new UnavailableToHideException(null);
	}

	/**
	 * @param place
	 * @throws UnavailableToHideException
	 */
	public static void showPlace(final GHAPlace place)
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
			} catch (final UnavailableToHideException e) {
				placeWaitingToShow = place;
				throw new UnavailableToHideException(e);
			}

		if (places.get(place.getId()) == null)
			addPlace(place);
		place.show();
		place.updateToken(History.getToken());
		currentPlace = place;
	}

	/**
	 * @param historyToken
	 */
	public static void showPlace(final String historyToken) {
		final int indexOf = historyToken.indexOf("/");
		final String token;
		if (indexOf == -1)
			token = historyToken;
		else
			token = historyToken.substring(0, indexOf);
		final GHAPlace place = places.get(token);
		if (place == null)
			GHAPlacesFactory.showPlace(token);
		else
			showPlace(place);
	}

	private GHAPlaceSet() {
		throw new UnsupportedOperationException(
				"this class can be instantiaded");
	}

}
