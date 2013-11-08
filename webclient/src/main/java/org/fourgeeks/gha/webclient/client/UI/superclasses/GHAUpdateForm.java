package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;



/**
 * @author alacret
 * 
 */
public abstract class GHAUpdateForm extends GHASlideInWindow {
	private GHALabel label;

	/**
	 * @param title
	 */
	public GHAUpdateForm(String title) {
		super();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT+GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT+GHAUiHelper.V_SEPARATOR_HEIGHT+1);
		label = new GHALabel(title);
		addMember(label);
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}