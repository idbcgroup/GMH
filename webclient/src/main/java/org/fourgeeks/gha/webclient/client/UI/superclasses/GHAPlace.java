package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.util.SC;

public abstract class GHAPlace {
	private String token = null;

	public abstract void show();

	public void setToken(String token) {
		this.token = token;
	};

	public String getToken() {
		if (token == null)
			SC.say("No token set for this place");
		return token;
	};
}
