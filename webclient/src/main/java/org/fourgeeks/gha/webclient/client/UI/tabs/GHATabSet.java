package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.HashMap;
import java.util.Map;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author alacret
 * 
 */
public final class GHATabSet {

	private Map<String, GHATab> tabs;
	private GHATab currentTab;
	private HorizontalPanel hPanel;
	{
		tabs = new HashMap<String, GHATab>();
		hPanel = new HorizontalPanel();
		hPanel.setHeight("30px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		// RootPanel.get("menu-bar").add(hPanel);//TODO
	}

	private GHATabSet() {
	}

	private void addTab(final GHATab tab) {
		tabs.put(tab.getId(), tab);
		RootPanel rootPanel = RootPanel.get("main-content");
		try {
			rootPanel.add(tab);
		} catch (Exception e) {
			Window.alert("error tring to add the tab to the manin content");
			Window.alert(e.getMessage());
		}
	}

	/**
	 * @param tab
	 * @throws UnavailableToHideException
	 */
	public void showTab(GHATab tab) throws UnavailableToHideException {
		if (tab == null)
			return;
		if (tab == currentTab)
			return;
		if (currentTab != null)
			try {
				hideTab(currentTab);
			} catch (UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}

		if (tabs.get(tab.getId()) == null) {
			addTab(tab);
		} else {
			tab.show();
		}

		hPanel.add(tab.getHeader());
		currentTab = tab;
	}

	private void hideTab(GHATab tab) throws UnavailableToHideException {
		hideTab(tab, HideCloseAction.ASK);
	}

	private void hideTab(GHATab tab, HideCloseAction hideAction) {
		if (tab.canBeHidden(hideAction)) {
			try {
				tab.hide();
			} catch (UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}
			hPanel.remove(tab.getHeader());
			return;
		}
		throw new UnavailableToHideException(null);
	}

	/**
	 * @param hideAction
	 * @throws UnavailableToHideException
	 */
	public void hideCurrentTab(HideCloseAction hideAction)
			throws UnavailableToHideException {
		hideTab(currentTab, hideAction);
		currentTab = null;
		History.forward();
	}

	/**
	 * @param hideAction
	 * @throws UnavailableToHideException
	 */
	public void closeCurrentTab(HideCloseAction hideAction)
			throws UnavailableToHideException {
		closeTab(currentTab, hideAction);
		currentTab = null;
	}

	/**
	 * @param id
	 * @return the tab with that ID
	 */
	public GHATab getById(String id) {
		return tabs.get(id);
	}

	/**
	 * @param tab
	 * @throws UnavailableToCloseException
	 */
	public void closeTab(final GHATab tab) {
		closeTab(tab, HideCloseAction.ASK);
	}

	private void closeTab(final GHATab tab, HideCloseAction closeAction)
			throws UnavailableToCloseException {
		if (tab == null)
			return;

		if (tab.canBeClosen(closeAction)) {
			try {
				tab.close();
			} catch (UnavailableToCloseException e) {
				throw new UnavailableToCloseException(e);
			}
			tabs.remove(tab.getId());
			hPanel.remove(tab.getHeader());
			History.newItem("home");
			return;
		}
		throw new UnavailableToCloseException(null);
	}

}
