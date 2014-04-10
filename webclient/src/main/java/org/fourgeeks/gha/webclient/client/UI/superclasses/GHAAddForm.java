package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;

import com.smartgwt.client.util.BooleanCallback;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAAddForm<T> extends GHASlideInWindow {
	private final GHATopTitleLabel titleLabel;
	protected GHAForm<T> form;

	/**
	 * @param title
	 */
	public GHAAddForm(String title) {
		super();
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		titleLabel = new GHATopTitleLabel(title);
		addMember(titleLabel);
	}

	/**
	 * cancel the add operation
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

	protected abstract void save();
}