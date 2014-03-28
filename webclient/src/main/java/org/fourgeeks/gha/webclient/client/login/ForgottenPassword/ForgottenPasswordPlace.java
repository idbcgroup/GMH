package org.fourgeeks.gha.webclient.client.login.ForgottenPassword;

import org.fourgeeks.gha.webclient.client.UI.places.GHAPlace;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author alacret
 * 
 */
public class ForgottenPasswordPlace extends GHAPlace {

	private final MailCheckPanel mailCheckPanel = new MailCheckPanel();

	/**
	 * @param token
	 */
	public ForgottenPasswordPlace(String token) {
		super(token);
	}

	@Override
	public void show() {
		super.show();
		RootPanel.get("main-content").clear();
		RootPanel.get("main-content").addStyleName("white-background");
		RootPanel.get("main-content").add(mailCheckPanel);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAcronym() {
		// TODO Auto-generated method stub
		return null;
	}
}