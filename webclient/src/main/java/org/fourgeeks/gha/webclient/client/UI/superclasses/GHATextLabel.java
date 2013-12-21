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
		setWidth100();
		setStyleName("gha-text-label");
	}
}