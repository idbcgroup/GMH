package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.webclient.client.UI.UIPlace;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class HomePlace implements UIPlace{
	
	public HomePlace(){
	}
	
	@Override
	public void show() {
		RootPanel.get("main-content").clear();
		RootPanel.get("user-info").clear();
		
		StringBuilder html = new StringBuilder();
		html.append("<div class='username-text'>Jose Pereira Martinez</div>");
		html.append("<div class='user-notifications'></div>");
		html.append("<div class='user-menu'></div>");
		
		HTML content = new HTML(html.toString());
		
		RootPanel.get("user-info").add(content);
		
		
		VLayout layout = new VLayout();
		layout.setWidth100();  
		layout.setHeight100();
		
		final TabSet mainTabPanel = new TabSet();  
		mainTabPanel.setTabBarPosition(Side.TOP);  
		mainTabPanel.setTabBarAlign(Side.LEFT);  
		mainTabPanel.setWidth100();  
		mainTabPanel.setHeight100();
		
		Menu menu = new Menu();
		MenuItem addTab = new MenuItem("Add Tab");
		
		addTab.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				Tab newTab = createTab("New app", new VLayout(), true);
				mainTabPanel.addTab(newTab);
				mainTabPanel.selectTab(newTab);
			}
		});
		
		menu.setItems(addTab);
		
		IMenuButton menuButton = new IMenuButton("Aplicaciones", menu);  
        menuButton.setWidth(100);  
        
        layout.addMember(menuButton);
        layout.addMember(mainTabPanel);
        
        RootPanel.get("main-content").add(layout);
		layout.draw();
	}
	
	public Tab createTab(String title, Canvas pane, boolean closable) {
		Tab tab = new Tab();
		tab.setTitle(title);
		tab.setCanClose(closable);
		tab.setPane(pane);
		
		return tab;
	}
}
