package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.HashMap;
import java.util.Map;

import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author alacret
 * 
 */
public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static GHATab currentTab;
	static {
		tabs = new HashMap<String, GHATab>();
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	private static void addTab(final GHATab tab) {
		tabs.put(tab.getId(), tab);
		RootPanel.get("main-content").add(tab);
	}

	/**
	 * @param tab
	 */
	public static void showTab(GHATab tab) {
		if (tab == null)
			return;

		if (tab == currentTab)
			return;

		if (currentTab != null)
			hideTab(currentTab);

		if (tabs.get(tab.getId()) == null)
			addTab(tab);
		else
			tab.show();

		currentTab = tab;
		showHeader(tab.getHeader());
	}

	/**
	 * 
	 */
	private static void hideTab(GHATab tab) {
		GHAMenu.removeHeader();
		tab.hide();
	}

	private static void showHeader(GHATabHeader header) {
		GHAMenu.setHeader(header);
	}

	/**
	 * @param id
	 * @return the tab with that ID
	 */
	public static GHATab getById(String id) {
		return tabs.get(id);
	}

	/**
	 * @param tab
	 */
	public static void closeTab(final GHATab tab) {
		if (tab == null)
			return;

		tab.close();
		tabs.remove(tab.getId());

		History.newItem("home");
	}
}
