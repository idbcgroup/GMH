package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret Subclase que representa un subtab de las pantallas
 * 
 */
public class GHASubTab extends Tab implements GHAClosable, GHAHideable {
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();

	/**
	 * @param title
	 * @param tab
	 */
	public GHASubTab(String title, GHATab tab) {
		tab.addGHAHideableHandler(this);
		tab.addGHAClosableHandler(this);
		setTitle(title);
		setPaneMargin(0);
	}

	@Override
	public void close() {
		for (GHAClosable closable : closables)
			closable.close();
	}

	@Override
	public void hide() {
		for (GHAHideable hideable : hideables)
			hideable.hide();
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
