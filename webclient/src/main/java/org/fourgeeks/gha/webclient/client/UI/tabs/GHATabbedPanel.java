package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableProducer;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableProducer;

import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHATabbedPanel extends VLayout implements ClosableListener,
HideableListener, ClosableProducer, HideableProducer {

	private final List<ClosableListener> closables = new ArrayList<ClosableListener>();
	private final List<HideableListener> hideables = new ArrayList<HideableListener>();

	private final GHATabSet tabsetHeaderBar;
	private final Map<String, GHATab> tabMap = new HashMap<String, GHATab>();
	private GHATab currentTab;

	/**
	 * @param title
	 * 
	 */
	public GHATabbedPanel(String title) {
		setWidth100();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		tabsetHeaderBar = new GHATabSet(title, this);
		addMember(tabsetHeaderBar);
	}

	private void addTab(GHATab tab) {
		tabsetHeaderBar.add(tab.getHeader());
		addMember(tab);
		addHideableListener(tab);
		addClosableListener(tab);
		tabMap.put(tab.getId(), tab);
	}

	/**
	 * @param text
	 * @param clickHandler
	 * @param imgSrc
	 *            Known values: "limpiarButton", "agregarButton", "cerrarButton"
	 */
	public void addHeaderOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		tabsetHeaderBar.addOption(text, imgSrc, clickHandler);
	}

	/**
	 * @param id
	 * @return the tab with the id, or null if there is no tab with that id
	 */
	public GHATab getTabById(String id) {
		return tabMap.get(id);
	}

	/**
	 * @param id
	 */
	public void showTab(String id) {
		final GHATab tab = getTabById(id);
		if (tab == null)
			return;
		if (currentTab != null)
			currentTab.hide();
		tab.show();
		currentTab = tab;
	}

	/**
	 * 
	 */
	public void showCurrentTab(){
		showTab(currentTab.getId());
	}

	/**
	 * @param tab
	 */
	public void addAndShow(GHATab tab) {
		addTab(tab);
		showTab(tab.getId());
	}

	@Override
	public void close() throws UnavailableToCloseException {
		for (final ClosableListener closable : closables)
			try {
				closable.close();
			} catch (final Exception e) {
				throw new UnavailableToCloseException(e);
			}
		removeFromParent();
		destroy();
	}

	@Override
	public void hide() throws UnavailableToHideException {
		for (final HideableListener hideable : hideables)
			try {
				hideable.hide();
			} catch (final UnavailableToHideException e) {
				throw new UnavailableToHideException(e);
			}

		super.hide();
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
}