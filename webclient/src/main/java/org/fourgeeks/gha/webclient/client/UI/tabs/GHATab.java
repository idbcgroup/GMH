package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHATab extends VLayout implements GHAClosable,
		GHAHideable {

	private String token;;
	protected GHATabHeader header;
	protected VLayout verticalPanel = new VLayout();
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();

	/**
	 * @param header
	 * @param token
	 * 
	 */
	public GHATab(String token) {
		this.token = token;
		setWidth100();
		setBackgroundColor(GHAUiHelper.BACKGROUND_COLOR);
		// verticalPanel.setBackgroundColor("#FFFFFF");
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
	 * @return the token
	 */
	public String getToken() {
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
		// TODO: test if the header can get remove from this point, and not form
		// ghatabset
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