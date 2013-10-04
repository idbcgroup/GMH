package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.menu.IMenuButton;

/**
 * @author alacret
 * 
 */
public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static HorizontalPanel hPanel;
	private static GHATab currentTab;
	static {
		hPanel = new HorizontalPanel();
		hPanel.setHeight("24px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		RootPanel.get("menu-bar").add(hPanel);
		tabs = new HashMap<String, GHATab>();
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	private static void addTab(final GHATab tab) {
		GHATabHeader header = tab.getHeader();
		hPanel.add(header);
		hPanel.setCellHeight(header, "24px");

		if (currentTab != null)
			currentTab.hide();

		tabs.put(tab.getId(), tab);

		RootPanel.get("main-content").add(tab);
		currentTab = tab;
		header.selectTab();
	}

	/**
	 * @param tab
	 */
	public static void showTab(GHATab tab) {
		if (tab == null)
			return;

		if (tabs.get(tab.getId()) == null) {
			addTab(tab);
			return;
		}

		if (tab == currentTab)
			return;

		if (currentTab != null)
			currentTab.hide();

		tab.show();
		currentTab = tab;
	}

	/**
	 * @param id
	 * @return the tab by the id
	 */
	public static GHATab getById(String id) {
		return tabs.get(id);
	}

	/**
	 * @param menuButton
	 */
	public static void addMenu(IMenuButton menuButton) {
		hPanel.add(menuButton);
		hPanel.setCellHeight(menuButton, "24px");
	}

	/**
	 * @param tab
	 */
	public static void closeTab(final GHATab tab) {
		if (tab == null)
			return;

		// if (tabs.get(tab.getId()) == null)
		// return;

		tab.close();
		tabs.remove(tab.getId());

		History.back();
	}
}
