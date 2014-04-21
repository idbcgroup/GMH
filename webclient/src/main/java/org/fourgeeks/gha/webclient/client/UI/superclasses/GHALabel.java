package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHALabel extends Label {

	/**
	 * 
	 */
	public GHALabel() {
		super();
		setHeight(20);
		setWrap(false);
	}
	/**
	 * @param title
	 */
	public GHALabel(String title) {
		this();
		setContents(title);
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHALabel backgroundColored() {
		setBackgroundColor("#D0D0E0");
		return this;
	}
}