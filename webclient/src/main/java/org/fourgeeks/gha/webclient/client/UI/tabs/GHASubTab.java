package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableProducer;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableProducer;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;

/**
 * @author alacret Subclase que representa un subtab de las pantallas
 * 
 */
public class GHASubTab extends Tab implements ClosableListener,
ClosableProducer, HideableProducer, HideableListener {
	private final List<ClosableListener> closables = new ArrayList<ClosableListener>();
	private final List<HideableListener> hideables = new ArrayList<HideableListener>();

	/**
	 * @param title
	 */
	public GHASubTab(String title) {
		setTitle(title);
		setPaneMargin(0);
		addTabDeselectedHandler(new TabDeselectedHandler() {

			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				canBeHidden(HideCloseAction.SAVE);
			}
		});
	}

	@Override
	public void close() {
		for (final ClosableListener closable : closables)
			closable.close();
	}

	@Override
	public void hide() {
		for (final HideableListener hideable : hideables)
			hideable.hide();
	}

	/**
	 * @param closable
	 */
	@Override
	public void addClosableListener(ClosableListener closable) {
		closables.add(closable);
	}

	/**
	 * @param hideable
	 */
	@Override
	public void addHideableListener(HideableListener hideable) {
		hideables.add(hideable);
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		for (final HideableListener hideable : hideables)
			if (!hideable.canBeHidden(hideAction))
				return false;
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		for (final ClosableListener closable : closables)
			if (!closable.canBeClosen(closeAction))
				return false;
		return true;
	}

	@Override
	public void removeHideableListener(HideableListener hideableListener) {
		hideables.remove(hideableListener);
	}

	@Override
	public void removeClosableListener(ClosableListener closableListener) {
		closables.remove(closableListener);
	}
}
