package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret Subclase que representa un subtab de las pantallas
 * 
 */
public class GHASubTab extends Tab implements ClosableListener,
		HideableListener {
	private List<ClosableListener> closables = new ArrayList<ClosableListener>();
	private List<HideableListener> hideables = new ArrayList<HideableListener>();

	/**
	 * @param title
	 * @param tab
	 */
	public GHASubTab(String title, GHATab tab) {
		tab.addHideableHandler(this);
		tab.addClosableHandler(this);
		setTitle(title);
		setPaneMargin(0);
	}

	@Override
	public void close() {
		for (ClosableListener closable : closables)
			closable.close();
	}

	@Override
	public void hide() {
		for (HideableListener hideable : hideables)
			hideable.hide();
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

}
