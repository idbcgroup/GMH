package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuBar;
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuOption;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public final class GHATabSet {

	private static Map<String, GHATab> tabs;
	private static GHATab currentTab;
	private static HorizontalPanel hPanel;
	private static GHAMenuBar verticalMenu;
	static {
		tabs = new HashMap<String, GHATab>();
		hPanel = new HorizontalPanel();
		hPanel.setHeight("30px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		RootPanel.get("menu-bar").add(hPanel);
	}

	private GHATabSet() {
		throw new UnsupportedOperationException("Esta clase no es instanciable");
	}

	private static void addTab(final GHATab tab) {
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
	public static void showTab(GHATab tab) throws UnavailableToHideException {
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

	private static void hideTab(GHATab tab) throws UnavailableToHideException {
		if (tab.canBeHidden()) {
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
	 * @param id
	 * @return the tab with that ID
	 */
	public static GHATab getById(String id) {
		return tabs.get(id);
	}

	/**
	 * @param tab
	 * @throws UnavailableToCloseException
	 */
	public static void closeTab(final GHATab tab)
			throws UnavailableToCloseException {
		if (tab == null)
			return;

		if (tab.canBeClosen()) {
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

	/**
	 * Build the Menu
	 */
	public static void buildMenu() {
		GHAImgButton menu = new GHAImgButton("../resources/icons/menu.png");
		menu.setSize("34px", "22px");
		menu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				verticalMenu.bringToFront();
				if (!verticalMenu.isVisible()) {
					verticalMenu.open();
				} else {
					verticalMenu.animateHide(AnimationEffect.FLY);
					GHAUiHelper.removeDocumentMouseOverHandler(verticalMenu);
				}
			}
		});

		hPanel.add(menu);

		verticalMenu = new GHAMenuBar(menu);
		GHAUiHelper.addGHAResizeHandler(verticalMenu);
		
		Bpu user = GHASessionData.getLoggedUser();
		List<GHAMenuOption> menuOptions = getMenuOptions(user);
		for (GHAMenuOption ghaMenuOption : menuOptions)
			verticalMenu.addOption(ghaMenuOption);
	}

	/**
	 * @return the list of menu option
	 */
	private static List<GHAMenuOption> getMenuOptions(Bpu loggedUser) {
		List<BpuFunction> permissions = loggedUser.getPermissions();
		Map<String, String> permissionMap = new HashMap<String, String>();
		for (BpuFunction bpuFunction : permissions)
			permissionMap.put(bpuFunction.getFunction().getView().getScreen()
					.getToken(), bpuFunction.getFunction().getView()
					.getScreen().getName());

		Set<Entry<String, String>> entrySet = permissionMap.entrySet();
		List<GHAMenuOption> menuOptions = new ArrayList<GHAMenuOption>();
		for (final Entry<String, String> entry : entrySet) {
			menuOptions.add(new GHAMenuOption(entry.getValue(), entry.getKey(),
					"../resources/icons/menu/" + entry.getKey() + ".png"));
		}
		return menuOptions;
	}

	public static GHATab getCurrentTab() {
		return currentTab;
	}

	public static void setCurrentTab(GHATab currentTab) {
		GHATabSet.currentTab = currentTab;
	}

	/**
	 * @param token
	 * @return the ghamenuoption of this token or null if is not found
	 */
	public static GHAMenuOption getGHAMenuOptionByToken(String token) {
		return verticalMenu.getByToken(token);
	}
}
