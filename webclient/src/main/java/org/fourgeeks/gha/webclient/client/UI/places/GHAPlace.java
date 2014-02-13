package org.fourgeeks.gha.webclient.client.UI.places;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableProducer;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableProducer;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHAPlace extends VLayout implements HideableListener,
ClosableListener, HideableProducer, ClosableProducer, ResizeHandler {

	private final List<ClosableListener> closables = new ArrayList<ClosableListener>();
	private final List<HideableListener> hideables = new ArrayList<HideableListener>();
	protected String token;
	protected GHAPlaceHeader header;

	/**
	 * @param token
	 */
	public GHAPlace(String token) {
		this.token = token;
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setHeight(GHAUiHelper.getTabHeight());
		GHAUiHelper.addGHAResizeHandler(this);
	}

	@Override
	public void addClosableListener(ClosableListener closable) {
		closables.add(closable);
	}

	@Override
	public void addHideableListener(HideableListener hideable) {
		hideables.add(hideable);
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		for (ClosableListener closable : closables)
			if (!closable.canBeClosen(closeAction))
				return false;
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		for (HideableListener hideable : hideables)
			if (!hideable.canBeHidden(hideAction))
				return false;
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		for (ClosableListener closable : closables)
			try {
				closable.close();
			} catch (Exception e) {
				throw new UnavailableToCloseException(e);
			}
		GHAUiHelper.removeGHAResizeHandler(this);
		removeFromParent();
		destroy();
	}

	/**
	 * @return the Acronym for this tab. to be put in the eyelash
	 */
	public abstract String getAcronym();

	/**
	 * @return the header
	 */
	public GHAPlaceHeader getHeader() {
		return header;
	}

	// /**
	// * @param token
	// */
	// public void show(String token) {
	// show();
	// updateToken(token);
	// }

	/**
	 * @return the id of the tab
	 */
	public abstract String getId();

	@Override
	public void hide() throws UnavailableToHideException {
		for (HideableListener hideable : hideables)
			try {
				hideable.hide();
			} catch (UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}
		// super.hide();
		header.unMarkSelected();
		getElement().addClassName("hidden");
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

	@Override
	public void removeClosableListener(ClosableListener closable) {
		closables.remove(closable);
	}

	@Override
	public void removeHideableListener(HideableListener hideable) {
		hideables.remove(hideable);
	}

	@Override
	public void show() {
		header.markSelected();
		getElement().removeClassName("hidden");
	}

	/**
	 * @param token
	 */
	public void updateToken(String token) {
		this.token = token;
	}

}
