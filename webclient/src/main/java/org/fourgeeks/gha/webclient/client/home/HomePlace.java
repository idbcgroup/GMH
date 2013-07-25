package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.webclient.client.UI.GHADropdownMenus;
import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class HomePlace extends GHAPlace {

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
		RootPanel.get("main-content").removeStyleName("white-background");
		
		final VLayout userMenu = GHADropdownMenus.userMenu();
		final VLayout notificationsMenu = GHADropdownMenus.notificationsMenu();
		// userMenu.draw();

		HLayout userInfo = new HLayout();
		userInfo.setMembersMargin(10);
		userInfo.setStyleName("user-info");
		userInfo.setHeight("50px");
		userInfo.setDefaultLayoutAlign(Alignment.CENTER);

		Label usernameLabel = new Label("Jose Pereira Martinez");
		usernameLabel.setStyleName("username-text");
		usernameLabel.setSize("300	px", "25px");
		Label notificationsButton = new Label("1");
		notificationsButton.setStyleName("user-notifications-button");
		notificationsButton.setBackgroundImage("../resources/icons/boton1.png");
		notificationsButton.setSize("28px", "25px");
		Img userButton = new Img("../resources/icons/boton2.png");
		userButton.setSize("21px", "25px");

		userButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						int posx = event.getX() - 270;
						int posy = event.getY();
						if (event.getY() < 50)
							posy += 20;
						else
							posy += 10;

						// TODO Auto-generated method stub
						if (userMenu.isVisible()) {
							userMenu.animateHide(AnimationEffect.FADE);
							userMenu.setVisible(false);
							userMenu.setLeft(0);
							userMenu.setTop(0);
						} else {
							userMenu.animateShow(AnimationEffect.FADE);
							userMenu.setLeft(posx);
							userMenu.setTop(posy);
							userMenu.setVisible(true);
						}
					}
				});

		notificationsButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						int posx = event.getX() - 270;
						int posy = event.getY();
						if (event.getY() < 50)
							posy += 20;
						else
							posy += 10;

						if (notificationsMenu.isVisible()) {
							notificationsMenu.animateHide(AnimationEffect.FADE);
							notificationsMenu.setVisible(false);
							notificationsMenu.setLeft(0);
							notificationsMenu.setTop(0);
						} else {
							notificationsMenu.animateShow(AnimationEffect.FADE);
							notificationsMenu.setLeft(posx);
							notificationsMenu.setTop(posy);
							notificationsMenu.setVisible(true);
						}
					}
				});

		userInfo.addMembers(usernameLabel, notificationsButton, userButton);

		/*
		 * StringBuilder html = new StringBuilder();
		 * html.append("<div class='username-text'>Jose Pereira Martinez</div>"
		 * ); html.append("<div class='user-notifications'>1</div>");
		 * html.append("<div class='user-menu'></div>");
		 * 
		 * HTML content = new HTML(html.toString());
		 */
		RootPanel.get("user-info").add(userInfo);
		RootPanel.get("main-content").setHeight(GHAUiHelper.getTabHeight()+"px");
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("main-content").setHeight(GHAUiHelper.getTabHeight()+"px");
			}
		});
		// TODO: calculo de la altura del main content. para el homeplace y para
		// el resize

		// /////////////////Menu
		MenuItem eiaTypeMenuItem = new MenuItem("Tipos de EIA");
		eiaTypeMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				History.newItem("eiatype");
			}
		});
	
		MenuItem eiaMenuItem = new MenuItem("Equipos");
		eiaMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				History.newItem("eia");
			}
		});
		
		MenuItem edtMenuItem = new MenuItem("Estacion de Trabajo");
		edtMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				History.newItem("edt");
			}
		});	
		
		Menu menu = new Menu();
		menu.setItems(eiaTypeMenuItem,eiaMenuItem,edtMenuItem);

		IMenuButton menuButton = new IMenuButton("Aplicaciones", menu);
		menuButton.setWidth(150);
		menuButton.setHeight(24);
		menuButton.setStylePrimaryName("gha-main-menu");
		menuButton.setZIndex(33333333);
		GHATabSet.addMenu(menuButton);
		History.newItem("edt");
	}
}