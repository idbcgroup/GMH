package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.menu.GHAMenu.GHAMenuOption;

import com.smartgwt.client.util.SC;

/**
 * @author alacret
 * 
 */
public abstract class GHAPlace {

	/**
	 * @param token
	 */
	public GHAPlace(String token) {
		setToken(token);
	}

	private String token = null;

	/**
	 * 
	 */
	public abstract void show();

	/**
	 * @param token
	 */
	protected void setToken(String token) {
		this.token = token;
	};

	/**
	 * @return the token
	 */
	public String getToken() {
		if (token == null)
			SC.say("No token set for this place");
		return token;
	};

	protected GHAMenuOption getGHAMenuOption() {
		return GHATabSet.getGHAMenuOptionByToken(getToken());
	}
}
