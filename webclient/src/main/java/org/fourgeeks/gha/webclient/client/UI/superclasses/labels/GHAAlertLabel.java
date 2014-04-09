package org.fourgeeks.gha.webclient.client.UI.superclasses.labels;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

/**
 * @author alacret
 * 
 */
public class GHAAlertLabel extends GHALabel {
	/**
	 * @param title
	 * @param backgroundColor
	 */
	public GHAAlertLabel(String title, String backgroundColor) {
		super();
		setBackgroundColor(backgroundColor);
		setContents(title);
		setHeight(15);
		setWidth100();
		setStyleName("alert-label");
	}
}