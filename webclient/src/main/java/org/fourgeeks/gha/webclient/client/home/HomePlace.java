package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class HomePlace implements GHAPlace {

	private static boolean HOME_HAS_BEEN_BUILT = false;

	public HomePlace() {
	}

	@Override
	public void show() {
		if (HOME_HAS_BEEN_BUILT)
			return;
		HOME_HAS_BEEN_BUILT = true;
		// User box
		RootPanel.get("main-content").clear();
		RootPanel.get("user-info").clear();
		RootPanel.get("menu-bar").clear();

		StringBuilder html = new StringBuilder();
		html.append("<div class='username-text'>Jose Pereira Martinez</div>");
		html.append("<div class='user-notifications'>1</div>");
		html.append("<div class='user-menu'></div>");

		HTML content = new HTML(html.toString());
		RootPanel.get("user-info").add(content);

		// /////////////////Menu
		MenuItem eiaTypeMenuItem = new MenuItem("Tipos de EIA");
		eiaTypeMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				History.newItem("eiatype");
			}
		});
		Menu menu = new Menu();
		menu.setItems(eiaTypeMenuItem);

		IMenuButton menuButton = new IMenuButton("Aplicaciones", menu);
		menuButton.setWidth(150);
		menuButton.setHeight(24);
		menuButton.setStylePrimaryName("gha-main-menu");
		menuButton.setZIndex(33333333);
		GHATabSet.addMenu(menuButton);

	}
}