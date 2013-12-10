package org.fourgeeks.gha.webclient.client.UI.places;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;

import com.google.gwt.user.client.History;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;

/**
 * @author alacret
 * 
 */
public class GHAPlaceHeader extends Label {

	private boolean selected = false;

	/**
	 * @param place
	 * @param title
	 * @param token
	 */
	public GHAPlaceHeader(final GHAPlace place, String title, final String token) {
		super();
		setContents(title);
		setWidth(GHAUiHelper.DEFAULT_PLACE_EYELASH_WIDTH);
		// setStyleName("tab-header-title");
		setHeight(GHAUiHelper.DEFAULT_PLACE_EYELASH_HEIGHT);
		setStyleName("tab-header-title button-pointer");

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

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					History.newItem(token);
				} catch (UnavailableToCloseException e) {
					return;
				}
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
