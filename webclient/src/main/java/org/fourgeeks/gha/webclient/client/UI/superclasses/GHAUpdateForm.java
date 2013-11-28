package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public abstract class GHAUpdateForm<T> extends GHASlideInWindow {
	private GHALabel label;
	protected GHAForm<T> form;

	/**
	 * @param title
	 */
	public GHAUpdateForm(String title) {
		super();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT
				+ GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ GHAUiHelper.V_SEPARATOR_HEIGHT + 1);
		label = new GHALabel(title);
		addMember(label);
	}

	/**
	 * cancel the update operation
	 */
	public void cancel() {
		if (!form.hasUnCommittedChanges()) {
			hide();
			return;
		}

		GHANotification.askYesNoCancel(GHAStrings.get("information"),
				GHAStrings.get("unsaved-changes"), new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						form.undo();
						hide();
					}
				}, new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
					}
				}, null);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	protected abstract void update();
}