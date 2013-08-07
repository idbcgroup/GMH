package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

public class GHANotification {
	
	/*public GHANotification() {
		setPosition(Positioning.ABSOLUTE);
		setBottom(Window.getClientHeight()-20);
		setSize("280px", "*");
		setBackgroundColor("#FFFFFF");
		setBorder("1px solid #E0E0E0");
		setVisibility(Visibility.HIDDEN);

		HLayout topUserLayout = new HLayout();
		topUserLayout.setSize("100%", "25px");
		topUserLayout.setBackgroundColor("#666666");

		addMember(GHAUiHelper.verticalGraySeparator("25px"));
	}*/

	public static void alert(String message){
		SC.say(message);
	}
	
	public static void confirm(String title, String message, BooleanCallback callback){
		SC.ask(title, message, callback);
	}
}
