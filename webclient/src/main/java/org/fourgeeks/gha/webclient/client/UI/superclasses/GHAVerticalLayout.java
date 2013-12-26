package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * A vertical layout for put background color an styles
 * 
 * @author alacret
 */
public abstract class GHAVerticalLayout extends VLayout {

	/**
	 * 
	 */
	public GHAVerticalLayout() {
		super();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setStyleName("sides-padding padding-top");
	}

}