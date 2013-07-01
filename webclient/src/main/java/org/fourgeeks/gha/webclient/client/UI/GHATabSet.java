package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.menu.IMenuButton;

public final class GHATabSet {

	// private static Map<String, GHATab> tabs;
	private static HorizontalPanel hPanel;
	private static GHATab currentTab;
	static {
		hPanel = new HorizontalPanel();
		hPanel.setHeight("24px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		RootPanel.get("menu-bar").add(hPanel);
		// tabs = new HashMap<String, GHATab>();
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	public static void addTab(final GHATab tab) {
		hPanel.add(tab.getHeader());
		hPanel.setCellHeight(tab.getHeader(), "24px");

		if (currentTab != null)
			if (currentTab != tab)
				currentTab.close();// TODO

		// tabs.put(tab.getId(), tab);

		RootPanel.get("main-content").add(tab);
		currentTab = tab;
	}

	public static void showTab(GHATab tab) {
		if (tab == null)
			return;

		if (tab == currentTab)
			return;

		// if (tabs.get(tab.getId()) == null)
		// return;

		if (currentTab != null)
			currentTab.close();// TODO

		tab.show();
		currentTab = tab;
	}

	// public static GHATab getById(String id) {
	// return tabs.get(id);
	// }

	public static void addMenu(IMenuButton menuButton) {
		hPanel.add(menuButton);
		hPanel.setCellHeight(menuButton, "24px");
	}

	public static void closeTab(final GHATab tab) {
		if (tab == null)
			return;

		// if (tabs.get(tab.getId()) == null)
		// return;

		tab.close();
		// tabs.remove(tab.getId());

		// History.back();
	}
}
