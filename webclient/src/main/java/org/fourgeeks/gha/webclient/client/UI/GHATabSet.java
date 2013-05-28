package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.tab.TabSet;

public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static HorizontalPanel hPanel;
	private static TabSet tabset;
	static {
		hPanel = new HorizontalPanel();
		hPanel.setHeight("24px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);		
		RootPanel.get("menu-bar").add(hPanel);
		tabs = new HashMap<String, GHATab>();
		tabset = new TabSet();
		tabset.setBorder("none");
		tabset.setWidth100();
		tabset.setHeight100();
		tabset.setStyleName("main-tab-set");
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	public static void addTab(GHATab tab, boolean show) {
		GHATabHeader ghaTabHeader = new GHATabHeader(tab);
		hPanel.add(ghaTabHeader);
		hPanel.setCellHeight(ghaTabHeader, "24px");
		tabset.addTab(tab);
		tabs.put(tab.getId(), tab);
		if (show)
			showTab(tab);
	}

	private static void showTab(GHATab tab) {
		tabset.selectTab(tab.getId());
	}

	public static void draw() {
		RootPanel.get("main-content").add(tabset);
	}

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
