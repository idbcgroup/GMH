package org.fourgeeks.gha.webclient.client.login.ForgottenPassword;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;

import com.google.gwt.user.client.ui.RootPanel;

public class ForgottenPasswordPlace extends GHAPlace {

	private mailCheckPanel mailCheckPanel = new mailCheckPanel();
	
	
	public ForgottenPasswordPlace() {
	}

	@Override
	public void show() {
		RootPanel.get("main-content").clear();
		RootPanel.get("main-content").addStyleName("white-background");
		RootPanel.get("main-content").add(mailCheckPanel);
	}
}