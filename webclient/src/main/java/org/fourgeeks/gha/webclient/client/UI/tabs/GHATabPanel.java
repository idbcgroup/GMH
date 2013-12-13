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

	/**
	 * @param title
	 * 
	 */
	public GHATabPanel(String title) {
		tabset = new GHATabSet(title, this);
		addMember(tabset);
	}

	/**
	 * @param tab
	 */
	public void addTab(GHATab tab) {
		tabset.add(tab.getHeader());
		addMember(tab);
		tabs.put(tab.getId(), tab);
	}

	/**
	 * @param text
	 * @param clickHandler
	 * @param imgSrc
	 */
	public void addHeaderOption(String text, String imgSrc, ClickHandler clickHandler) {
		tabset.addOption(text, imgSrc, clickHandler);
	}
}