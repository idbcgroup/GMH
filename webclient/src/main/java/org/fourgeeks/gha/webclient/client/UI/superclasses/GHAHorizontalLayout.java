package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.HLayout;

/**
 * A vertical layout for put background color an styles
 * 
 * @author alacret
 */
public abstract class GHAHorizontalLayout extends HLayout {

	/**
	 * 
	 */
	public GHAHorizontalLayout() {
		super();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
	}

}