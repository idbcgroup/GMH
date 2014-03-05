package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret, naramirez
 * @param <T>
 * 
 */
public abstract class GHAUpdateForm<T> extends GHASlideInWindow {
	private final GHALabel titleLabel;
	protected GHAForm<T> form;

	/**
	 * @param title
	 */
	public GHAUpdateForm(String title) {
		super();
		titleLabel = new GHALabel(title);
		addMember(titleLabel);
	}

	/**
	 * cancel the update operation
	 */
	public void cancel() {
		if (!form.hasUnCommittedChanges()) {
			hide();
			return;
		}

		GHAAlertManager.askYesNoCancel("unsaved-changes", new ClickHandler() {

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

	/**
	 * @param title
	 */
	public void setFormTitle(String title) {
		titleLabel.setContents(title);
	}

	protected abstract void update();
}