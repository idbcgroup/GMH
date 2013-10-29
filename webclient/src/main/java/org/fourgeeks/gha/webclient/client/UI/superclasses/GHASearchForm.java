package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;

public abstract class GHASearchForm extends GHASlideInWindow {
	private GHALabel label;

	public GHASearchForm(String title) {
		super();
		setHeight(GHAUiHelper.getTabHeight());
		setTop(GHAUiHelper.getTopSpace());
		label = new GHALabel(title);
		addMember(label);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

}
