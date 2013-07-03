package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class GHATab extends VLayout implements GHAClosable,
		GHAHideable {

	private String token = null;
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();

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

	@Override
	public void close() {
		for (GHAClosable closable : closables)
			closable.close();
		if (getHeader() != null)
			getHeader().removeFromParent();
		removeFromParent();
	}

	@Override
	public void hide() {
		for (GHAHideable hideable : hideables)
			hideable.hide();
		super.hide();
		getElement().addClassName("hidden");
	}

	@Override
	public void show() {
		super.show();
		getElement().removeClassName("hidden");
	}

	public void addClosableHandler(GHAClosable closable) {
		closables.add(closable);
	}

	public void addHideableHandler(GHAHideable hideable) {
		hideables.add(hideable);
	}
}