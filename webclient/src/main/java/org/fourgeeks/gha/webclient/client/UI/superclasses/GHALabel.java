package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

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
		setWrap(false);
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setStyleName("gha-label");
	}
}