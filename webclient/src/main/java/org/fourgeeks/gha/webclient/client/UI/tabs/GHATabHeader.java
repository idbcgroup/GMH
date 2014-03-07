package org.fourgeeks.gha.webclient.client.UI.tabs;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

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
public class GHATabHeader extends Label {

	private static final String STYLE = "tab-header-title button-pointer";
	private boolean selected = false;

	/**
	 * @param tab
	 */
	public GHATabHeader(final GHATab tab) {
		super();
		setContents(tab.getTitleForHeader());
		setWidth(GHAUiHelper.DEFAULT_PLACE_EYELASH_WIDTH);
		setHeight(GHAUiHelper.DEFAULT_TAB_EYELASH_HEIGHT + "px");
		setStyleName(STYLE);
		setBaseStyle(STYLE);

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
					setBackgroundColor(GHAUiHelper.DEFAULT_PANEL_BAR_BACKGROUND_COLOR);
			}
		});

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem(tab.getId());
			}
		});
	}

	/**
	 * 
	 */
	public void unMarkSelected() {
		setBackgroundColor(GHAUiHelper.DEFAULT_PANEL_BAR_BACKGROUND_COLOR);
		setStyleName(STYLE);
		setBaseStyle(STYLE);
		selected = false;
	}

	/**
	 * mark the button as selected
	 */
	public void markSelected() {
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setStyleName(STYLE + "tab-header-title-selected");
		setBaseStyle(STYLE + "tab-header-title-selected");
		selected = true;
	}

}
