package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHATab extends VLayout implements GHAClosable,
		GHAHideable {

	private String token = null;
	protected GHATabHeader header;
	protected VLayout verticalPanel = new VLayout();
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();

	/**
	 * 
	 */
	public GHATab() {
		setWidth100();
		// setBackgroundColor("#E0E0E0");
		setBackgroundColor("#FFFFFF");
		header = new GHATabHeader(this);
		verticalPanel.setBackgroundColor("#FFFFFF");
		// addMember(verticalPanel);
	}

	/**
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * @return the header
	 */
	public GHATabHeader getHeader() {
		return header;
	}

	/**
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	};

	/**
	 * @return the token
	 */
	public String getToken() {
		if (token == null)
			GHANotification.alert("No token set for this tab");
		return token;
	};

	/**
	 * This method is call by the GHATAbSet to notyfy the close action
	 */
	@Override
	public void close() {
		for (GHAClosable closable : closables)
			closable.close();
		// if (getHeader() != null)
		// getHeader().removeFromParent();
		removeFromParent();
	}

	@Override
	public void hide() {
		for (GHAHideable hideable : hideables)
			hideable.hide();
		super.hide();
		getElement().addClassName("hidden");
		// Tab
		// getHeader().deselectTab();
	}

	@Override
	public void show() {
		super.show();
		getElement().removeClassName("hidden");
		// Tab
		// getHeader().selectTab();
	}

	/**
	 * @param closable
	 */
	public void addGHAClosableHandler(GHAClosable closable) {
		closables.add(closable);
	}

	/**
	 * @param hideable
	 */
	public void addGHAHideableHandler(GHAHideable hideable) {
		hideables.add(hideable);
	}
}