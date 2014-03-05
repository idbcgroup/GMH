package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATextLabelSmallSize extends Label {
	/**
	 * @param title
	 */
	public GHATextLabelSmallSize(String title) {
		super();
		setContents(title);
		setHeight("20px");
		setAutoFit(true);
		setWrap(false);
		setStyleName("gha-small-text-label");
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabelSmallSize bold() {
		setStyleName("gha-small-text-label bold");
		return this;
	}
}