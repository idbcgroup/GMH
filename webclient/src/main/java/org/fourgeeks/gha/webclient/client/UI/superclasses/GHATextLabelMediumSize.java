package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATextLabelMediumSize extends Label {
	/**
	 * @param title
	 */
	public GHATextLabelMediumSize(String title) {
		super();
		setContents(title);
		setHeight("25px");
		setAutoFit(true);
		setWrap(false);
		setStyleName("gha-medium-text-label");
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabelMediumSize bold() {
		setStyleName("gha-medium-text-label bold");
		return this;
	}
}