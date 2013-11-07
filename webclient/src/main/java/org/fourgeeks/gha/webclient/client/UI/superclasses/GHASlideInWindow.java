package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.AnimationCallback;

/**
 * @author alacret a window that slide in
 */
public abstract class GHASlideInWindow extends GHAVerticalLayout implements
		ResizeHandler, ClosableListener, HideableListener {

	/**
	 * 
	 */
	public GHASlideInWindow() {
		setWidth100();
		setLeft(-5);
		setVisibility(Visibility.HIDDEN);
		setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
		GHAUiHelper.addGHAResizeHandler(this);
		setTop(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT);
		setHeight(GHAUiHelper.getTabHeight() - 5);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 5);
	}

	@Override
	public void close() {
		RootPanel.get("slideInWindowsBackDiv").removeStyleName("dim");
		int windowZIndex = getZIndex();
		RootPanel.get("slideInWindowsBackDiv").getElement().getStyle()
				.setZIndex(-80000);

		GHAUiHelper.removeGHAResizeHandler(this);
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
		RootPanel.get("slideInWindowsBackDiv").addStyleName("dim");
		int windowZIndex = getZIndex();
		RootPanel.get("slideInWindowsBackDiv").getElement().getStyle()
				.setZIndex(windowZIndex - 1);

		animateShow(AnimationEffect.FLY);
	}

	@Override
	public void hide() {
		RootPanel.get("slideInWindowsBackDiv").removeStyleName("dim");
		int windowZIndex = getZIndex();
		RootPanel.get("slideInWindowsBackDiv").getElement().getStyle()
				.setZIndex(-80000);

		animateHide(AnimationEffect.FLY);
	}

	/**
	 * @param callback
	 */
	public void hide(AnimationCallback callback) {
		RootPanel.get("slideInWindowsBackDiv").removeStyleName("dim");
		int windowZIndex = getZIndex();
		RootPanel.get("slideInWindowsBackDiv").getElement().getStyle()
				.setZIndex(-80000);

		animateHide(AnimationEffect.FLY, callback);
	}

}