package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class GHAMenus {

	public static VLayout userMenu(){
		VLayout userMenu = new VLayout();
		userMenu.setPosition(Positioning.ABSOLUTE);
		userMenu.setTop(70);
		userMenu.setSize("280px", "*");
		userMenu.setBackgroundColor("#FFFFFF");
		userMenu.setBorder("1px solid #E0E0E0");
		userMenu.setVisible(false);

		HLayout topUserLayout = new HLayout();
		topUserLayout.setSize("100%", "25px");
		topUserLayout.setBackgroundColor("#666666");

		userMenu.addMember(GHAUiHelper.verticalGraySeparator("25px"));
		
		return userMenu; 
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
