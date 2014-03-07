package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATextLabelSmallSize extends Label {
	/**
	 * 
	 */
	public GHATextLabelSmallSize() {
		super();
		setHeight("20px");
		setAutoFit(true);
		setWrap(false);
		setStyleName("gha-small-text-label");
	}

	/**
	 * @param title
	 */
	public GHATextLabelSmallSize(String title) {
		this();
		setContents(title);
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