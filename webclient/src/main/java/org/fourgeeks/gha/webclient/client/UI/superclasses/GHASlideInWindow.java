package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret a window that slide in
 */
public abstract class GHASlideInWindow extends VLayout implements
		ResizeHandler, GHAClosable, GHAHideable {

	/**
	 * 
	 */
	public GHASlideInWindow() {
		setWidth100();
		setLeft(-5);
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top box");
		setAlign(Alignment.CENTER);
		setVisibility(Visibility.HIDDEN);
		setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
		GHAUiHelper.addGHAResizeHandler(this);
		setTop(95);
	}

	@Override
	public void close() {
		hide(new AnimationCallback() {

			@Override
			public void execute(boolean earlyFinish) {
				destroy();
			}
		});
	}

	/**
	 * 
	 */
	public void open() {
		animateShow(AnimationEffect.FLY);
	}

	@Override
	public void hide() {
		animateHide(AnimationEffect.FLY);
	}

	/**
	 * @param callback
	 */
	public void hide(AnimationCallback callback) {
		animateHide(AnimationEffect.FLY, callback);
	}

}