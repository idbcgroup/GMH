package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATitleLabelSmallSize extends Label {
	/**
	 * @param title
	 */
	public GHATitleLabelSmallSize(String title) {
		super();
		setContents(title);
		setHeight("20px");
		setAutoFit(true);
		setWrap(false);
		setStyleName("gha-small-title-label");
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATitleLabelSmallSize bold() {
		setStyleName("gha-small-title-label bold");
		return this;
	}
}