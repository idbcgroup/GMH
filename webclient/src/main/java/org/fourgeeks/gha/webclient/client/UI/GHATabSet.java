package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.tab.TabSet;

public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static RootPanel container;
	private static TabSet tabset;
	static {
		container = RootPanel.get("tab-list");
		tabs = new HashMap<String, GHATab>();
		tabset = new TabSet();
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	public static void addTab(GHATab tab, boolean show) {
		container.add(new GHATabHeader(tab.getTitle()));
		tabset.addTab(tab);
		tabs.put(tab.getId(), tab);
		if (show)
			showTab(tab);
	}

	private static void showTab(GHATab tab) {
		tabset.selectTab(tab.getId());
	}

	public static void addTab(String id, GHATab tab) {
		addTab(tab, true);
	}

	public static GHATab getById(String id) {
		GHATab tab = tabs.get(id);
		return tab;
	}

}
