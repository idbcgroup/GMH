package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eia.EIAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;

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
public class EIAInformationFormPanel extends GHAVerticalLayout implements
		GHAClosable, GHAHideable, EiaSelectionProducer, EIASelectionListener {

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

	@Override
	public boolean canBeClosen() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeHidden() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
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
}