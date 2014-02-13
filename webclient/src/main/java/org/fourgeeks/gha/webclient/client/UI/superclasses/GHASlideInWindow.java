package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableProducer;

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
ResizeHandler, ClosableListener, HideableListener, HideableProducer {
	//	/**
	//	 *
	//	 */
	//	public enum SlideInWindowType {
	//		/**
	//		 * Type for a Search Window
	//		 */
	//		SEARCH,
	//		/**
	//		 * Type for an Add Window
	//		 */
	//		ADD,
	//		/**
	//		 * Type for an Update Window
	//		 */
	//		UPDATE;
	//	}

	List<HideableListener> listeners = new ArrayList<HideableListener>();

	public GHASlideInWindow() {
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getBottomSectionHeight()-5);
		setLeft(-5);
		setVisibility(Visibility.HIDDEN);
		setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
		GHAUiHelper.addGHAResizeHandler(this);
	}

	@Override
	public void addHideableListener(HideableListener hideableListener) {
		listeners.add(hideableListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public void close() {
		RootPanel.get("slideInWindowsBackDiv").removeStyleName("dim");
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

	@Override
	public void hide() {
		for (HideableListener listener : listeners)
			listener.hide();
		RootPanel.get("slideInWindowsBackDiv").removeStyleName("dim");
		RootPanel.get("slideInWindowsBackDiv").getElement().getStyle()
		.setZIndex(-80000);
		animateHide(AnimationEffect.FLY);
	}

	/**
	 * @param callback
	 */
	public void hide(AnimationCallback callback) {
		for (HideableListener listener : listeners)
			listener.hide();
		RootPanel.get("slideInWindowsBackDiv").removeStyleName("dim");
		RootPanel.get("slideInWindowsBackDiv").getElement().getStyle()
		.setZIndex(-80000);
		animateHide(AnimationEffect.FLY, callback);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight()-5);
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
	public void removeHideableListener(HideableListener hideableListener) {
		listeners.remove(hideableListener);
	}

}