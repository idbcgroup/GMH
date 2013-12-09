package org.fourgeeks.gha.webclient.client.UI.places;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;

import com.smartgwt.client.types.BackgroundRepeat;
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
	 */
	public GHAPlaceHeader(final GHAPlace place, String title) {
		super();
		setContents(title);
		setWidth(GHAUiHelper.DEFAULT_PLACE_EYELASH_WIDTH);
		setStyleName("tab-header-title");
		setHeight(GHAUiHelper.DEFAULT_PLACE_EYELASH_HEIGHT);
		setStyleName(getStyleName() + " button-pointer");
		setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);

		addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				if (!selected)
					setBackgroundImage(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
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
					GHAPlaceSet.closePlace(place);
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
		setBackgroundImage(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		selected = true;
	}

}
