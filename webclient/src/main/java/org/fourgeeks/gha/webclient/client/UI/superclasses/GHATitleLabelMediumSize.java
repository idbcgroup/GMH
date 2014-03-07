package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATitleLabelMediumSize extends Label {
	/**
	 * @param title
	 */
	public GHATitleLabelMediumSize(String title) {
		super();
		setContents(title);
		setHeight("25px");
		setAutoFit(true);
		setWrap(false);
		setStyleName("gha-medium-title-label");
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATitleLabelMediumSize bold() {
		setStyleName("gha-medium-title-label bold");
		return this;
	}
}