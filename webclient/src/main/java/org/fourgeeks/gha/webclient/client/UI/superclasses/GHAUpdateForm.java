package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;

import com.smartgwt.client.util.BooleanCallback;

/**
 * @author alacret, naramirez
 * @param <T>
 * 
 */
public abstract class GHAUpdateForm<T> extends GHASlideInWindow {
	private final GHATopTitleLabel titleLabel;
	protected GHAForm<T> form;

	/**
	 * @param title
	 */
	public GHAUpdateForm(String title) {
		super();
		titleLabel = new GHATopTitleLabel(title);
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

		//		GHAAlertManager.askYesNoCancel("unsaved-changes", new ClickHandler() {
		//
		//			@Override
		//			public void onClick(ClickEvent event) {
		//				form.undo();
		//				hide();
		//			}
		//		}, new ClickHandler() {
		//
		//			@Override
		//			public void onClick(ClickEvent event) {
		//			}
		//		}, null);
		GHAErrorMessageProcessor.confirm("unsaved-changes", new BooleanCallback() {

			@Override
			public void execute(Boolean value) {
				if(value)
				{
					form.undo();
					hide();
				}
			}
		});

	}

	/**
	 * @param title
	 */
	public void setFormTitle(String title) {
		titleLabel.setContents(title);
	}

	protected abstract void update();
}