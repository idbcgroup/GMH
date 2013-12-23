package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHATabPanel extends VLayout {
	private final GHATabSet tabset;
	private final Map<String, GHATab> tabs = new HashMap<String, GHATab>();
	private GHATab currentTab;

	/**
	 * @param title
	 * 
	 */
	public GHATabPanel(String title) {
		tabset = new GHATabSet(title, this);
		addMember(tabset);
	}

	private void addTab(GHATab tab) {
		tabset.add(tab.getHeader());
		addMember(tab);
		tabs.put(tab.getId(), tab);
	}

	/**
	 * @param text
	 * @param clickHandler
	 * @param imgSrc
	 */
	public void addHeaderOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		tabset.addOption(text, imgSrc, clickHandler);
	}

	/**
	 * @param id
	 * @return the tab with the id, or null if there is no tab with that id
	 */
	public GHATab getTabById(String id) {
		return tabs.get(id);
	}

	/**
	 * @param id
	 */
	public void showTab(String id) {
		GHATab tab = getTabById(id);
		if (tab == null)
			return;
		if (currentTab != null)
			currentTab.hide();
		tab.show();
		currentTab = tab;
	}

	/**
	 * @param tab
	 */
	public void addAndShow(GHATab tab) {
		addTab(tab);
		showTab(tab.getId());

	}
}