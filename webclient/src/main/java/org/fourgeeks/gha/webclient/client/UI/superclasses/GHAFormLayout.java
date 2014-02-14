package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

/**
 * A vertical layout for put background color an styles
 * 
 * @author alacret
 */
public abstract class GHAFormLayout extends GHAVerticalLayout {

	/**
	 * 
	 */
	public GHAFormLayout() {
		super();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setStyleName("sides-padding padding-top");
	}

}