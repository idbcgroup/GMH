package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHATabbedPanel extends VLayout {
	private final GHATabSet tabsetHeader;
	private final Map<String, GHATab> tabMap = new HashMap<String, GHATab>();
	private GHATab currentTab;

	/**
	 * @param title
	 * 
	 */
	public GHATabbedPanel(String title) {
		tabsetHeader = new GHATabSet(title, this);
		addMember(tabsetHeader);
	}

	private void addTab(GHATab tab) {
		tabsetHeader.add(tab.getHeader());
		addMember(tab);
		tabMap.put(tab.getId(), tab);
	}

	/**
	 * @param text
	 * @param clickHandler
	 * @param imgSrc
	 *            Known values: "limpiarButton", "agregarButton", "cerrarButton"
	 */
	public void addHeaderOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		tabsetHeader.addOption(text, imgSrc, clickHandler);
	}

	/**
	 * @param id
	 * @return the tab with the id, or null if there is no tab with that id
	 */
	public GHATab getTabById(String id) {
		return tabMap.get(id);
	}

	/**
	 * @param id
	 */
	public void showTab(String id) {
		final GHATab tab = getTabById(id);
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