package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.webclient.client.UI.UIPlace;

import com.google.gwt.user.client.ui.RootPanel;

public class HomePlace implements UIPlace{
	
	public HomePlace(){
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		RootPanel.get("main-content").clear();
		
	}
}
