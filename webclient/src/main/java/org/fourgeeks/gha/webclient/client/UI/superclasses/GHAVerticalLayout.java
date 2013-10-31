package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret A vertical layout for put background color an styles
 */
public abstract class GHAVerticalLayout extends VLayout {

	/**
	 * 
	 */
	public GHAVerticalLayout() {
		super();
		setWidth100();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setAlign(Alignment.CENTER);
	}

}