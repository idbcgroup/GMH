package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATextLabel extends Label {
	/**
	 * @param title
	 */
	public GHATextLabel(String title) {
		super();
		setContents(title);
		setHeight("25px");
		setAutoFit(true);
		setStyleName("gha-text-label");
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabel bold() {
		addStyleName("bold");
		return this;
	}
}