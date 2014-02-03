package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAAddForm<T> extends GHASlideInWindow {
	private final GHALabel label;
	protected GHAForm<T> form;

	/**
	 * @param title
	 */
	public GHAAddForm(String title) {
		super();
		label = new GHALabel(title);
		addMember(label);
	}

	/**
	 * cancel the add operation
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

	protected abstract void save();
}