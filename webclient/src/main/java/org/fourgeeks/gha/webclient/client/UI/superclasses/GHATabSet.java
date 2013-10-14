package org.fourgeeks.gha.webclient.client.UI.superclasses;

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
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuBar;
import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuOption;

import com.google.gwt.user.client.History;
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
		RootPanel.get("main-content").add(tab);
	}

	/**
	 * @param tab
	 */
	public static void showTab(GHATab tab) {
		if (tab == null)
			return;

		if (tab == currentTab)
			return;

		if (currentTab != null)
			hideTab(currentTab);

		if (tabs.get(tab.getId()) == null)
			addTab(tab);
		else
			tab.show();

		hPanel.add(tab.getHeader());
		currentTab = tab;
	}

	/**
	 * 
	 */
	private static void hideTab(GHATab tab) {
		tab.hide();
		hPanel.remove(tab.getHeader());
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
	 */
	public static void closeTab(final GHATab tab) {
		if (tab == null)
			return;

		tab.close();
		tabs.remove(tab.getId());

		History.newItem("home");
	}

	/**
	 * Build the Menu
	 */
	public static void buildMenu() {
		final GHAMenuBar verticalMenu = new GHAMenuBar();
		GHAUiHelper.addGHAResizeHandler(verticalMenu);

		GHAImgButton menu = new GHAImgButton("../resources/icons/menu.png");
		menu.setSize("34px", "24px");
		menu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				verticalMenu.bringToFront();
				if (!verticalMenu.isVisible()) {
					verticalMenu.open();
				} else {
					verticalMenu.animateHide(AnimationEffect.FLY);
					GHAUiHelper.removeDocumentClickHandler(verticalMenu);
				}
			}
		});

		// HLayout menuPanel = new HLayout();
		// menuPanel.setWidth100();
		// menuPanel.setHeight(30);
		// menuPanel.setMembersMargin(10);
		// menuPanel.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		// menuPanel.setStyleName("left-menu-padding");
		hPanel.add(menu);

		Bpu user = GHASessionData.getLoggedUser();
		List<GHAMenuOption> menuOptions = getMenuOptions(user);
		for (GHAMenuOption ghaMenuOption : menuOptions) {
			ghaMenuOption.setBar(verticalMenu);
			verticalMenu.addOption(ghaMenuOption);
		}

		// RootPanel.get("menu-bar").add(menuPanel);
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

}
