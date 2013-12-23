package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHALabel extends Label {

	/**
	 * @param title
	 */
	public GHALabel(String title) {
		super();
		setContents(title);
		setHeight("25px");
		setWidth100();
		setMinWidth(1024);
		setStyleName("gha-label");
	}
}