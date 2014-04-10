package org.fourgeeks.gha.webclient.client.eiatype.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeForm;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeInformationFormPanel extends GHAFormLayout implements
EIATypeSelectionListener, EiaTypeSelectionProducer, HideableListener,
ClosableListener {

	private final EiaTypeForm form = new EiaTypeForm();

	/**
	 * 
	 */
	public EIATypeInformationFormPanel() {
		super();
		final VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAUndoButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						undo();
					}
				}));

		final HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);
	}

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		form.addEiaTypeSelectionListener(eIATypeSelectionListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		if (hideAction.equals(HideCloseAction.DISCARD))
			return true;

		if (form.hasUnCommittedChanges()) {
			if (hideAction.equals(HideCloseAction.SAVE)) {
				form.update();
				return true;
			}

			//			GHAAlertManager.askYesNoCancel("unsaved-changes",
			//					new ClickHandler() {
			//
			//						@Override
			//						public void onClick(ClickEvent event) {
			//							GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);
			//
			//						}
			//					}, new ClickHandler() {
			//
			//						@Override
			//						public void onClick(ClickEvent event) {
			//							GHAPlaceSet
			//									.closeCurrentPlace(HideCloseAction.DISCARD);
			//
			//						}
			//					}, null);
			GHAErrorMessageProcessor.confirm("unsaved-changes", new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					if(value)
					{
						GHAPlaceSet.closeCurrentPlace(HideCloseAction.DISCARD);
					}
				}
			});
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		if (hideAction.equals(HideCloseAction.DISCARD))
			return true;

		if (form.hasUnCommittedChanges()) {
			if (hideAction.equals(HideCloseAction.SAVE)) {
				form.update();
				return true;
			}

			//			GHAAlertManager.askYesNoCancel("unsaved-changes",
			//					new ClickHandler() {
			//
			//				@Override
			//				public void onClick(ClickEvent event) {
			//					GHAPlaceSet.hideCurrentPlace(HideCloseAction.SAVE);
			//
			//				}
			//			}, new ClickHandler() {
			//
			//				@Override
			//				public void onClick(ClickEvent event) {
			//					GHAPlaceSet
			//					.hideCurrentPlace(HideCloseAction.DISCARD);
			//
			//				}
			//			}, null);
			GHAErrorMessageProcessor.confirm("unsaved-changes", new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					if(value)
					{
						GHAPlaceSet.closeCurrentPlace(HideCloseAction.DISCARD);
					}
				}
			});
			return false;
		}
		return true;
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		return;
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		form.removeEiaTypeSelectionListener(eIATypeSelectionListener);
	}

	private void save() {
		form.update(new GHAAsyncCallback<EiaType>() {

			@Override
			public void onSuccess(EiaType result) {
				GHAErrorMessageProcessor.alert("eiatype-save-success");
			}
		});
	}

	@Override
	public void select(EiaType eiaType) {
		form.set(eiaType);
	}

	protected void undo() {
		form.undo();
	}
}
