package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class GHATab extends VLayout {

	private String token = null;

	public GHATab() {
		setWidth100();
		setHeight("75%");
		setBackgroundColor("#E0E0E0");
	}

	public abstract void close();

	public abstract String getId();

	public abstract GHATabHeader getHeader();

	public void setToken(String token) {
		this.token = token;
	};

	public String getToken() {
		if (token == null)
			SC.say("No token set for this tab");
		return token;
	};

}
