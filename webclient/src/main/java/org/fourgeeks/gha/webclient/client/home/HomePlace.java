package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.dropdownmenus.UserDropdownMenu;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.FocusChangedEvent;
import com.smartgwt.client.widgets.events.FocusChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;

/**
 * @author alacret
 * 
 */
public class HomePlace extends GHAPlace {

	private static boolean HOME_HAS_BEEN_BUILT = false;

	/**
 * 
 */
	// private EIADispatchmentForm dispatchmentForm = new EIADispatchmentForm();
	// private EIAInstallationCertificateForm installationCertificateForm = new
	// EIAInstallationCertificateForm();

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

		Bpu user = GHASessionData.getLoggedUser();

		final UserDropdownMenu userMenu = new UserDropdownMenu(user);
		userMenu.addFocusChangedHandler(new FocusChangedHandler() {
			@Override
			public void onFocusChanged(FocusChangedEvent event) {
				Window.alert("Focus Changed");
				if (userMenu.isVisible()) {					
					userMenu.hide();
				}
			}
		});

		// final VLayout notificationsMenu =
		// GHADropdownMenus.notificationsMenu();

		HLayout userInfo = new HLayout();
		userInfo.setMembersMargin(10);
		userInfo.setStyleName("user-info");
		userInfo.setHeight("50px");
		userInfo.setDefaultLayoutAlign(Alignment.CENTER);

		Label usernameLabel = new Label(user.getCitizen().getFirstName() + " "
				+ user.getCitizen().getFirstLastName());
		usernameLabel.setStyleName("username-text");
		usernameLabel.setSize("300	px", "25px");
		
		// Label notificationsButton = new Label("1");
		// notificationsButton.setStyleName("user-notifications-button button-pointer");
		// notificationsButton.setBackgroundImage("../resources/icons/boton1.png");
		// notificationsButton.setSize("28px", "25px");
		
		// notificationsButton
		// .addClickHandler(new
		// com.smartgwt.client.widgets.events.ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// int posx = event.getX() - 270;
		// int posy = event.getY();
		// if (event.getY() < 50)
		// posy += 20;
		// else
		// posy += 10;
		//
		// if (notificationsMenu.isVisible()) {
		// notificationsMenu.animateHide(AnimationEffect.FADE);
		// notificationsMenu.setVisible(false);
		// notificationsMenu.setLeft(0);
		// notificationsMenu.setTop(0);
		// } else {
		// notificationsMenu.animateShow(AnimationEffect.FADE);
		// notificationsMenu.setLeft(posx);
		// notificationsMenu.setTop(posy);
		// notificationsMenu.setVisible(true);
		// }
		// }
		// });
		
		Img userButton = new Img("../resources/icons/boton2.png");
		userButton.setStyleName("button-pointer");
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
					userMenu.hide();
				} else {
					userMenu.show();
				}
			}
		});
		
		userInfo.addMembers(usernameLabel, /* notificationsButton, */
				userButton);

		/*
		 * StringBuilder html = new StringBuilder();
		 * html.append("<div class='username-text'>Jose Pereira Martinez</div>"
		 * ); html.append("<div class='user-notifications'>1</div>");
		 * html.append("<div class='user-menu'></div>");
		 * 
		 * HTML content = new HTML(html.toString());
		 */
		RootPanel.get("user-info").add(userInfo);
		RootPanel.get("main-content").setHeight(
				GHAUiHelper.getTabHeight() + "px");
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("main-content").setHeight(
						GHAUiHelper.getTabHeight() + "px");
			}
		});
		// TODO: calculo de la altura del main content. para el homeplace y para
		// el resize

		// /////////////////Menu
		// MenuItem userMenuItem = new MenuItem("Usuarios");
		// userMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("user");
		// }
		// });
		//
		// MenuItem eiaTypeMenuItem = new MenuItem("Tipos de Equipo");
		// eiaTypeMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("eiatype");
		// }
		// });
		//
		// MenuItem eiaMenuItem = new MenuItem("Equipos");
		// eiaMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("eia");
		// }
		// });

		// MenuItem edtMenuItem = new MenuItem("Estacion de Trabajo");
		// edtMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("edt");
		// }
		// });
		//
		// MenuItem mplanMenuItem = new MenuItem("Planes de Mantenimiento");
		// mplanMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("mplan");
		// }
		// });
		//
		// MenuItem mprotMenuItem = new MenuItem("Protocolos de Mantenimiento");
		// mprotMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("mprot");
		// }
		// });
		//
		// MenuItem mactMenuItem = new MenuItem("Actividades de Mantenimiento");
		// mactMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// History.newItem("mact");
		// }
		// });
		//
		// ////////External EIA Forms
		// MenuItem dispatchMenuItem = new MenuItem("Despacho de Equipos");
		// dispatchMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// dispatchmentForm.open();
		// }
		// });
		//
		// MenuItem installationMenuItem = new MenuItem("Acta de Instalación");
		// installationMenuItem.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(MenuItemClickEvent event) {
		// installationCertificateForm.open();
		// }
		// });
		// ///////////////////////////

		Menu menu = GHASessionData.getUserMenu();
		// menu.setItems(userMenuItem,
		// // edtMenuItem,
		// eiaTypeMenuItem,
		// eiaMenuItem/*,
		// mplanMenuItem,
		// mprotMenuItem,
		// mactMenuItem,
		// dispatchMenuItem,
		// installationMenuItem*/
		// );

		IMenuButton menuButton = new IMenuButton("Aplicaciones", menu);
		menuButton.setWidth(150);
		menuButton.setHeight(24);
//		menuButton.setStylePrimaryName("gha-main-menu");
		menuButton.getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setId("gha-main-menu");
//		Window.alert("Number of childrens:"+menuButton.getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName("lets-see"));
		int i=0;
		for(Canvas c: menuButton.getChildren()){
			Window.alert("Child "+i+" ClassName: "+c.getClassName());
			i++;
		}
		menuButton.setZIndex(33333333);
		GHATabSet.addMenu(menuButton);

		// History.newItem("edt");
	}
}