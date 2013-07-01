package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class GHATab extends VLayout implements GHAClosable {

	private String token = null;
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();

	public GHATab() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
	}

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

	// @Override
	// public void hide() {//TODO
	// for (GHAClosable closable : closables)
	// closable.close();
	// removeFromParent();
	// }

	@Override
	public void close() {
		for (GHAClosable closable : closables)
			closable.close();
		if (getHeader() != null)
			getHeader().removeFromParent();
		removeFromParent();
	}

	public void addClosableHandler(GHAClosable closable) {
		closables.add(closable);
	}
}