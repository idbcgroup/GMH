package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHATab extends VLayout implements ClosableListener,
		HideableListener {

	private String token;
	protected GHATabHeader header;
	protected VLayout verticalPanel = new VLayout();
	private List<ClosableListener> closables = new ArrayList<ClosableListener>();
	private List<HideableListener> hideables = new ArrayList<HideableListener>();

	/**
	 * @param header
	 * @param token
	 * 
	 */
	public GHATab(String token) {
		this.token = token;
		setWidth100();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
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
		for (ClosableListener closable : closables)
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
		for (HideableListener hideable : hideables)
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
		for (HideableListener hideable : hideables)
			if (!hideable.canBeHidden())
				return false;
		return true;
	}

	@Override
	public boolean canBeClosen() {
		for (ClosableListener closable : closables)
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
	public void addGHAClosableHandler(ClosableListener closable) {
		closables.add(closable);
	}

	/**
	 * @param hideable
	 */
	public void addGHAHideableHandler(HideableListener hideable) {
		hideables.add(hideable);
	}

	/**
	 * 
	 */
	public abstract void search();
}