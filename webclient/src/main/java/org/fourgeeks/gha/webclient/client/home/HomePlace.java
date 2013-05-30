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

	public HomePlace() {
	}

	@Override
	public void show() {
		// User box
		RootPanel.get("main-content").clear();
		RootPanel.get("user-info").clear();
		RootPanel.get("menu-bar").clear();

		StringBuilder html = new StringBuilder();
		html.append("<div class='username-text'>Jose Pereira Martinez</div>");
		html.append("<div class='user-notifications'></div>");
		html.append("<div class='user-menu'></div>");

		HTML content = new HTML(html.toString());
		RootPanel.get("user-info").add(content);
		

		// /////////////////Tabset

		// VLayout layout = new VLayout();
		// layout.setWidth100();
		// layout.setHeight(400);
		//
		// final TabSet mainTabPanel = new TabSet();
		// mainTabPanel.setTabBarPosition(Side.TOP);
		// mainTabPanel.setTabBarAlign(Side.LEFT);
		// mainTabPanel.setWidth100();
		// mainTabPanel.setHeight100();
		// mainTabPanel.setStyleName("main-tab-set");
		//
		// // Pueba de hiding de tabset
		// VLayout layout1 = new VLayout();
		// Label lorem = new Label();
		// lorem.setContents("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras non dolor arcu. Phasellus et nunc velit. Vivamus lobortis diam eu magna sodales congue. Proin faucibus vulputate egestas. In condimentum luctus enim, ut ornare est pretium in. Maecenas ac quam ut tortor porttitor dignissim. Quisque at tellus vel orci venenatis vestibulum ut posuere sapien. Nullam et dolor id est suscipit gravida. Curabitur auctor molestie nunc eu rutrum.");
		// layout1.addMember(lorem);
		// VLayout layout2 = new VLayout();
		// Label ipsum = new Label();
		// ipsum.setContents("Aliquam id nibh purus. Duis venenatis consectetur sem, ut hendrerit mauris pulvinar vel. Cras tempus suscipit felis, nec tristique nibh placerat ac. Vivamus sit amet justo ipsum. Etiam ut est metus. Nullam bibendum pharetra nisl et volutpat. Aliquam libero lectus, porttitor eget suscipit in, tempus eget orci. Integer facilisis, est eget aliquet iaculis, sem nibh mollis ligula, quis luctus leo est at ante. Nulla pretium vehicula tempus.");
		// layout2.addMember(ipsum);
		// VLayout layout3 = new VLayout();
		// Label dolor = new Label();
		// layout3.addMember(dolor);
		// dolor.setContents("Curabitur vitae metus non tellus laoreet sollicitudin vel nec tellus. Vivamus tempor dolor vitae sem interdum placerat. Fusce eu erat elit. Nulla facilisi. In hac habitasse platea dictumst. Cras dui elit, auctor at tristique vel, facilisis in nulla. Suspendisse cursus eleifend porta. In at tortor id ipsum tincidunt sodales. Pellentesque vestibulum turpis eget lectus tincidunt porta. Praesent ut tristique sapien. Sed dignissim feugiat nibh, in fermentum orci vulputate ac. Curabitur et felis ipsum, ut tristique mauris. Nam tincidunt ante ante.");
		//
		// Tab Tab1 = createTab("Tab1", layout1, true);
		// Tab Tab2 = createTab("Tab2", layout2, true);
		// Tab Tab3 = createTab("Tab3", layout3, true);
		// mainTabPanel.addTab(Tab1);
		// mainTabPanel.addTab(Tab2);
		// mainTabPanel.addTab(Tab3);
		// mainTabPanel.selectTab(0);
		//
		// layout.addMember(mainTabPanel);

		// /////////////////Menu
		MenuItem addTabMenuItem = new MenuItem("Equipos (EIA)");

		addTabMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				History.newItem("eia");
			}
		});
		Menu menu = new Menu();
		menu.setItems(addTabMenuItem);
//		menu.setStyleName("gha-main-menu");
//		menu.setStylePrimaryName("gha-main-menu");

		IMenuButton menuButton = new IMenuButton("Aplicaciones", menu);
		menuButton.setWidth(150);
		menuButton.setHeight(24);
//		menuButton.setStyleName("gha-main-menu2");
		menuButton.setStylePrimaryName("gha-main-menu");
		// menuButton.setWidth(100);

		// HLayout menulayout = new HLayout();
		// menulayout.addMember(menuButton);
		// menulayout.setMembersMargin(10);
		// menulayout.setWidth100();
		// menulayout.setHeight("24");

		// IButton showTab1 = new IButton("Show tab 1");
		// showTab1.addClickHandler(new
		// com.smartgwt.client.widgets.events.ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// mainTabPanel.selectTab(0);
		// }
		// });
		// IButton showTab2 = new IButton("Show tab 2");
		// showTab2.addClickHandler(new
		// com.smartgwt.client.widgets.events.ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// mainTabPanel.selectTab(1);
		// }
		// });
		// IButton showTab3 = new IButton("Show tab 3");
		// showTab3.addClickHandler(new
		// com.smartgwt.client.widgets.events.ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// mainTabPanel.selectTab(2);
		// }
		// });

		// menulayout.addMember(menuButton);
		// menulayout.addMember(showTab1);
		// menulayout.addMember(showTab2);
		// menulayout.addMember(showTab3);

		// /////////////Draw

		// RootPanel.get("main-content").add(layout);
		// layout.draw();
		menuButton.setZIndex(33333333);
		GHATabSet.addMenu(menuButton);
		GHATabSet.draw();
//		
		// menulayout.draw();

	}
}