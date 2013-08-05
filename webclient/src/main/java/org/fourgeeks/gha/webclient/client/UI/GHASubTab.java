package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret Subclase que representa un subtab de las pantallas
 * 
 */
public class GHASubTab extends Tab implements GHAClosable, GHAHideable {
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();

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

	public void addGHAClosableHandler(GHAClosable closable) {
		closables.add(closable);
	}

	public void addGHAHideableHandler(GHAHideable hideable) {
		hideables.add(hideable);
	}

}
