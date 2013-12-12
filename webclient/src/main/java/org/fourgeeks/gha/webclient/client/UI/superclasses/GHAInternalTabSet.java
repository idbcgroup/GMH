package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret
 * 
 */
public abstract class GHAInternalTabSet extends TabSet implements
		ResizeHandler, HideableListener, ClosableListener {

	protected List<HideableListener> hideables = new ArrayList<HideableListener>();
	protected List<ClosableListener> closables = new ArrayList<ClosableListener>();

	/**
	 * @param tab
	 */
	public GHAInternalTabSet(GHAPanel tab) {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setVisible(false);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		for (ClosableListener closable : closables)
			if (!closable.canBeClosen(hideAction))
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
			closable.close();
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	@Override
	public void hide() {
		for (HideableListener hideable : hideables)
			hideable.hide();
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}
