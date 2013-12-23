package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHATitleLabel extends Label {
	/**
	 * @param title
	 */
	public GHATitleLabel(String title) {
		super();
		setContents(title);
		setHeight("25px");
		setWidth100();
		setStyleName("gha-title-label");
	}
}