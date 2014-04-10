package org.fourgeeks.gha.webclient.client.UI.superclasses.labels;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

/**
 * @author alacret
 * 
 */
public class GHATopTitleLabel extends GHALabel {

	/**
	 * @param title
	 */
	public GHATopTitleLabel(String title) {
		super();
		setContents(title);
		setPadding(5);
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setStyleName("gha-top-label");
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	@Override
	public GHATopTitleLabel backgroundColored() {
		setBackgroundColor("#D0D0E0");
		return this;
	}
}