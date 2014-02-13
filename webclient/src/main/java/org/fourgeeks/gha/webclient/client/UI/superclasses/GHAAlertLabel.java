package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.Label;

/**
 * @author alacret
 * 
 */
public class GHAAlertLabel extends Label {
	/**
	 * @param title
	 * @param backgroundColor
	 */
	public GHAAlertLabel(String title, String backgroundColor) {
		super();
		setBackgroundColor(backgroundColor);
		setContents(title);
		setHeight("15px");
		setWidth100();
		setStyleName("gha-alert-label");
	}
}