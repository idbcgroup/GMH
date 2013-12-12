package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.TabStatus;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableProducer;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableProducer;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public abstract class GHATab extends VLayout implements ClosableListener,
		HideableListener, ClosableProducer, HideableProducer {

	protected GHATabHeader header;
	protected VLayout verticalPanel = new VLayout();
	private final List<ClosableListener> closables = new ArrayList<ClosableListener>();
	private final List<HideableListener> hideables = new ArrayList<HideableListener>();
	protected TabStatus currentStatus = TabStatus.INIT;

	/**
	 * @param header
	 * @param token
	 * 
	 */
	public GHATab() {
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
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		for (HideableListener hideable : hideables)
			if (!hideable.canBeHidden(hideAction))
				return false;
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		for (ClosableListener closable : closables)
			if (!closable.canBeClosen(closeAction))
				return false;
		return true;
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
	public void addClosableListener(ClosableListener closable) {
		closables.add(closable);
	}

	@Override
	public void addHideableListener(HideableListener hideable) {
		hideables.add(hideable);
	}

	/**
	 * Futures refactor will remove this method from here
	 */
	@Deprecated
	public abstract void search();
}