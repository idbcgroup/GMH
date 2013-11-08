package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;
import org.fourgeeks.gha.webclient.client.eia.EIAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAInformationFormPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, EiaSelectionProducer,
		EIASelectionListener {

	/**
	 * @param eiaEquipmentSubTab
	 * 
	 */
	private EIAForm form;

	{
		form = new EIAForm();
	}

	/**
	 * 
	 */
	public EIAInformationFormPanel() {
		super();

		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
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

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);

		// register as eiaselected listener with eiaform
		// form.addEiaSelectionListener(this);

	}

	/**
	 * 
	 */
	public void activate() {
		form.activate();
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		form.addEiaSelectionListener(eiaSelectionListener);

	}

	// @Override
	// public boolean canBeClosen(HideCloseAction hideAction) { // TODO
	// if (form.hasUnCommittedChanges()) {
	// GHANotification.confirm(GHAStrings.get("information"),
	// GHAStrings.get("unsaved-changes"), new BooleanCallback() {
	//
	// @Override
	// public void execute(Boolean value) {
	// if (value) {
	// form.undo();
	// }
	// }
	// });
	// return false;
	// }
	// return true;
	// }
	//
	// @Override
	// public boolean canBeHidden(HideCloseAction hideAction) { // TODO
	// if (form.hasUnCommittedChanges()) {
	// GHANotification.confirm(GHAStrings.get("information"),
	// GHAStrings.get("unsaved-changes"), new BooleanCallback() {
	//
	// @Override
	// public void execute(Boolean value) {
	// if (value) {
	// form.undo();
	// }
	// }
	// });
	// return false;
	// }
	// return true;
	// }

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer#notifyEia
	 * (org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void notifyEia(Eia eia) {
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		form.removeEiaSelectionListener(eiaSelectionListener);
	}

	protected void save() {
		form.update();
	}

	@Override
	public void select(Eia eia) {
		notifyEia(eia);
	}

	/**
	 * @param eia
	 */
	public void setEia(Eia eia) {
		form.setEia(eia);

		activate();
	}

	protected void undo() {
		form.undo();
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

			GHANotification.askYesNoCancel(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.closeCurrentTab(HideCloseAction.SAVE);

						}
					}, new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.closeCurrentTab(HideCloseAction.DISCARD);

						}
					}, null);
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

			GHANotification.askYesNoCancel(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.hideCurrentTab(HideCloseAction.SAVE);

						}
					}, new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.hideCurrentTab(HideCloseAction.DISCARD);

						}
					}, null);
			return false;
		}
		return true;
	}

}