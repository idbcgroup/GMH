package org.fourgeeks.gha.webclient.client.UI.tabs;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;

/**
 * @author alacret
 * 
 */
public class GHATabHeader extends Label {

	private boolean selected = false;

	/**
	 * @param tab
	 * @param title
	 */
	public GHATabHeader(final GHATab tab) {
		super();
		setContents(tab.getTitleForHeader());
		setWidth(GHAUiHelper.DEFAULT_PLACE_EYELASH_WIDTH);
		setHeight(GHAUiHelper.DEFAULT_PLACE_EYELASH_HEIGHT);
		setStyleName("place-header-title button-pointer");

		addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				if (!selected)
					setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
			}
		});
		addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				if (!selected)
					setBackgroundColor(GHAUiHelper.DEFAULT_PLACES_BAR_BACKGROUND_COLOR);
			}
		});
	}

	/**
	 * 
	 */
	public void unMarkSelected() {
		setBackgroundColor(GHAUiHelper.DEFAULT_PLACES_BAR_BACKGROUND_COLOR);
		selected = false;
	}

	/**
	 * mark the button as selected
	 */
	public void markSelected() {
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		selected = true;
	}
}
