package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.MenuLevel;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author alacret The Menu
 */
public class GHAMenu {

	private static GHAMenuBar verticalMenu = new GHAMenuBar();

	/**
	 * 
	 */
	public static void build() {
		// List<GHAMenuOption> menuOptions = getMenuOptions();
		//
		// for (GHAMenuOption ghaMenuOption : menuOptions)
		// verticalMenu.addOption(ghaMenuOption);

		Map<String, App> appset;
		Map<String, MenuLevel> firstLevelMenu = new HashMap<String, MenuLevel>();
		try {
			appset = GHASessionData.getAppsMapp();
		} catch (LoginNeededException e) {
			return;
		}
		Set<Entry<String, App>> entrySet = appset.entrySet();

		for (Entry<String, App> entry : entrySet) {
			MenuLevel menuLevel = entry.getValue().getMenuLevel();
			if (menuLevel != null) {
				MenuLevel parentMenu = menuLevel.getParentMenu();
				if (parentMenu != null) {
					Window.alert(parentMenu.getCode());
					firstLevelMenu.put(parentMenu.getCode(), parentMenu);

					verticalMenu.addOption(new GHAMenuOption(parentMenu
							.getText(), null, parentMenu.getCode()));
				}
			}
		}

		Set<Entry<String, MenuLevel>> entryMenuSet = firstLevelMenu.entrySet();

		for (Entry<String, MenuLevel> entry : entryMenuSet) {
			MenuLevel menu = entry.getValue();
			Window.alert(menu.getCode());
			verticalMenu.addOption(new GHAMenuOption(menu.getText(), null, menu
					.getCode()));
		}

	}

	/**
	 * @return the button that opens the menu
	 */
	public static Widget getMenuButton() {
		return verticalMenu.getMenuButton();
	}

	private static List<GHAMenuOption> getMenuOptions() {
		final List<GHAMenuOption> menuOptions = new ArrayList<GHAMenuOption>();
		try {
			final Map<String, App> appMap = GHASessionData.getAppsMapp();
			final Set<Entry<String, App>> entrySet = appMap.entrySet();

			for (final Entry<String, App> entry : entrySet) {
				menuOptions.add(new GHAMenuOption(GHAStrings.get(entry
						.getValue().getName()), entry.getKey(),
						"../resources/icons/menu/" + entry.getKey() + ".png"));
			}
			return menuOptions;
		} catch (final LoginNeededException e) {
			return menuOptions;
		}

	}

	private GHAMenu() {
		throw new UnsupportedOperationException(
				"This class mus not be instantiaded");
	}
}
