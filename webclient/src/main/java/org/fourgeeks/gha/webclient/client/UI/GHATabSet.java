package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.menu.IMenuButton;

public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static HorizontalPanel hPanel;
	static {
		hPanel = new HorizontalPanel();
		hPanel.setHeight("24px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		RootPanel.get("menu-bar").add(hPanel);

		tabs = new HashMap<String, GHATab>();
		// tabset.setBorder("none");
		// tabset.setWidth100();
		// tabset.setHeight("400px");
		// tabset.setStyleName("main-tab-set");///TODO: Borrar del css
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	public static void addTab(GHATab tab, boolean show) {
		hPanel.add(tab.getHeader());
		hPanel.setCellHeight(tab.getHeader(), "24px");
		RootPanel.get("main-content").add(tab);
		tabs.put(tab.getId(), tab);
		// if (show)
		// showTab(tab);
	}

	// private static void showTab(GHATab tab) {
	// tabset.selectTab(tab.getId());
	// }

	// public static void draw() {
	// RootPanel.get("main-content").add(tabset);
	// }

	public static void addTab(GHATab tab) {
		addTab(tab, true);
	}

	public static GHATab getById(String id) {
		GHATab tab = tabs.get(id);
		return tab;
	}

	public static void addMenu(IMenuButton menuButton) {
		hPanel.add(menuButton);
		hPanel.setCellHeight(menuButton, "24px");
	}

}
