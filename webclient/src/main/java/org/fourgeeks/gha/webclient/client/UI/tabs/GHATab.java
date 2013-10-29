package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHATab extends VLayout implements GHAClosable,
		GHAHideable {

	private String token;
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
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		// verticalPanel.setBackgroundColor("#FFFFFF");
		// addMember(verticalPanel);
	}

	/**
	 * @return the id of the tab
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

	@Override
	public void close() throws UnavailableToCloseException {
		for (GHAClosable closable : closables)
			try {
				closable.close();
			} catch (Exception e) {
				throw new UnavailableToCloseException(e);
			}
		removeFromParent();
		destroy();
	}

	@Override
	public void hide() throws UnavailableToHideException {
		for (GHAHideable hideable : hideables)
			try {
				hideable.hide();
			} catch (UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}

		super.hide();
		getElement().addClassName("hidden");
	}

	@Override
	public boolean canBeHidden() {
		for (GHAHideable hideable : hideables)
			if (!hideable.canBeHidden())
				return false;
		return true;
	}

	@Override
	public boolean canBeClosen() {
		for (GHAClosable closable : closables)
			if (!closable.canBeClosen())
				return false;
		return true;
	}

	@Override
	public void show() {
		super.show();
		getElement().removeClassName("hidden");
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