package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class GHADropdownMenus {
	
	public static VLayout userMenu(){
		VLayout user_menu = new VLayout();
		
		user_menu.setPosition(Positioning.ABSOLUTE);
		user_menu.setTop(70);
		user_menu.setSize("280px", "*");
		user_menu.setBackgroundColor("#FFFFFF");
		user_menu.setBorder("1px solid #E0E0E0");
		user_menu.setVisible(false);

		HLayout topUserLayout = new HLayout();
		topUserLayout.setSize("100%", "25px");
		topUserLayout.setBackgroundColor("#666666");

		user_menu.addMember(GHAUiHelper.verticalGraySeparator("25px"));
		
		return user_menu; 
	}
	
	public static VLayout notificationsMenu(){
		VLayout notificationsMenu = new VLayout();
		notificationsMenu.setPosition(Positioning.ABSOLUTE);
		notificationsMenu.setTop(70);
		notificationsMenu.setSize("280px", "*");
		notificationsMenu.setBackgroundColor("#FFFFFF");
		notificationsMenu.setBorder("1px solid #E0E0E0");
		notificationsMenu.setVisible(false);

		HLayout topUserLayout = new HLayout();
		topUserLayout.setSize("100%", "25px");
		topUserLayout.setBackgroundColor("#666666");

		notificationsMenu.addMember(GHAUiHelper.verticalGraySeparator("25px"));
		
		return notificationsMenu; 
	}
}
