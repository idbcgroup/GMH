package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author alacret
 * 
 */
public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static GHATab currentTab;
	private static LinkedList<String> historyStack;
	static {
		tabs = new HashMap<String, GHATab>();
		// historyStack = new LinkedList<String>();
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	private static void addTab(final GHATab tab) {
		// addHeader(tab.getHeader());
		tabs.put(tab.getId(), tab);
		RootPanel.get("main-content").add(tab);
	}

	// /**
	// * @param header
	// */
	// private static void addHeader(GHATabHeader header) {
	// hPanel.add(header);
	// hPanel.setCellHeight(header, "24px");
	// header.selectTab();
	// }

	/**
	 * @param tab
	 */
	public static void showTab(GHATab tab) {
		if (tab == null)
			return;

		if (tab == currentTab)
			return;

		if (currentTab != null)
			currentTab.hide();

		if (tabs.get(tab.getId()) == null)
			addTab(tab);
		else
			tab.show();

		historyStack.add(tab.getId());
		currentTab = tab;
	}

	/**
	 * @param id
	 * @return the tab with that ID
	 */
	public static GHATab getById(String id) {
		return tabs.get(id);
	}

	// /**
	// * @param menuButton
	// */
	// @Deprecated
	// public static void addMenu(IMenuButton menuButton) {
	// hPanel.add(menuButton);
	// hPanel.setCellHeight(menuButton, "24px");
	// }

	// /**
	// * @param menuButton
	// */
	// public static void addMenu(GHAImgButton menuButton) {
	// hPanel.add(menuButton);
	// hPanel.setCellHeight(menuButton, "24px");
	// }

	/**
	 * @param tab
	 */
	public static void closeTab(final GHATab tab) {
		if (tab == null)
			return;

		tab.close();
		tabs.remove(tab.getId());

		History.newItem("home");
		// historyStack.remove(tab.getId());

		// LinkedList<String> tempHistoryStack = new LinkedList<String>();
		// for (String token : historyStack)
		// if (!tab.getId().equals(token))
		// tempHistoryStack.add(token);
		// historyStack = tempHistoryStack;

		// if (tabs.size() == 0)
		// History.newItem("home");
		// else
		// History.newItem(historyStack.removeLast());
	}
}
