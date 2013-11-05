package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret
 * 
 */
public abstract class GHAInternalTabSet extends TabSet implements
		ResizeHandler, GHAHideable, GHAClosable {

	protected List<GHAHideable> hideables = new ArrayList<GHAHideable>();
	protected List<GHAClosable> closables = new ArrayList<GHAClosable>();

	/**
	 * @param tab
	 */
	public GHAInternalTabSet(GHATab tab) {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setVisible(false);
	}

	@Override
	public boolean canBeClosen() {
		for (GHAClosable closable : closables)
			if (!closable.canBeClosen())
				return false;
		return true;
	}

	@Override
	public boolean canBeHidden() {
		for (GHAHideable hideable : hideables)
			if (!hideable.canBeHidden())
				return false;
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		for (GHAClosable closable : closables)
			closable.close();
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	@Override
	public void hide() {
		for (GHAHideable hideable : hideables)
			hideable.hide();
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}
