package org.fourgeeks.gha.webclient.client.UI.panels;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;

/**
 * @author alacret
 * 
 */
public class GHAHeaderOption extends Label {
	private final String bgSrc;
	private final String bgSrcOver;
	private boolean selected = false;

	/**
	 * @param tabHeader
	 * @param width
	 * @param hoverable
	 * @param bgSrc
	 * @param bgSrcOver
	 */
	public GHAHeaderOption(int width, boolean hoverable, final String bgSrc,
			final String bgSrcOver) {
		super();
		this.bgSrc = bgSrc;
		this.bgSrcOver = bgSrcOver;
		setStyleName("tab-header-title");
		setWidth(width + "px");
		setHeight(GHAUiHelper.DEFAULT_HEADER_OPTION_HEIGHT + "px");

		// addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// tabHeader.unMarkAllButtons();
		// markSelected();
		// }
		// });

		if (hoverable) {
			setStyleName(getStyleName() + " button-pointer");
			setBackgroundImage(bgSrc);
			setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);

			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					if (!selected)
						setBackgroundImage(bgSrcOver);
				}
			});
			addMouseOutHandler(new MouseOutHandler() {

				@Override
				public void onMouseOut(MouseOutEvent event) {
					if (!selected)
						setBackgroundImage(bgSrc);
				}
			});
		}
	}

	/**
	 * @param tabHeader
	 * @param text
	 * @param width
	 * @param hoverable
	 * @param bg
	 * @param bgOver
	 */
	public GHAHeaderOption(String text, int width, boolean hoverable,
			String bg, String bgOver) {
		this(width, hoverable, bg, bgOver);
		setContents(text);
	}

	/**
	 * 
	 */
	public void unMarkSelected() {
		setBackgroundImage(bgSrc);
		selected = false;
	}

	/**
	 * mark the button as selected
	 */
	public void markSelected() {
		setBackgroundImage(bgSrcOver);
		selected = true;
	}

}