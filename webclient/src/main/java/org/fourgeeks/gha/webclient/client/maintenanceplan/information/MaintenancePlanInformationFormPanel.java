package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanInformationFormPanel extends GHAFormLayout
implements MaintenancePlanSelectionListener,
MaintenanceProtocolSelectionListener,
MaintenancePlanSelectionProducer, ClosableListener, HideableListener {
	private final MaintenancePlanForm form = new MaintenancePlanForm();

	/** */
	public MaintenancePlanInformationFormPanel() {

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
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener listener) {
		form.addMaintenancePlanSelectionListener(listener);
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

			//			GHAAlertManager.askYesNoCancel("unsaved-changes", new ClickHandler() {
			//				@Override
			//				public void onClick(ClickEvent event) {
			//					GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);
			//				}
			//			}, new ClickHandler() {
			//				@Override
			//				public void onClick(ClickEvent event) {
			//					GHAPlaceSet
			//					.closeCurrentPlace(HideCloseAction.DISCARD);
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
	public boolean canBeHidden(HideCloseAction hideAction) {
		if (hideAction.equals(HideCloseAction.DISCARD))
			return true;

		if (form.hasUnCommittedChanges()) {
			if (hideAction.equals(HideCloseAction.SAVE)) {
				form.update();
				return true;
			}

			//			GHAAlertManager.askYesNoCancel("unsaved-changes", new ClickHandler() {
			//				@Override
			//				public void onClick(ClickEvent event) {
			//					GHAPlaceSet.hideCurrentPlace(HideCloseAction.SAVE);
			//				}
			//			}, new ClickHandler() {
			//				@Override
			//				public void onClick(ClickEvent event) {
			//					GHAPlaceSet
			//					.hideCurrentPlace(HideCloseAction.DISCARD);
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
	public void close() throws UnavailableToCloseException {
		destroy();
		form.destroy();
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		return;
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener listener) {
		form.removeMaintenancePlanSelectionListener(listener);

	}

	private void save() {
		form.update(new GHAAsyncCallback<MaintenancePlan>() {
			@Override
			public void onSuccess(MaintenancePlan result) {
				GHAErrorMessageProcessor.alert("maintenance-plan-save-success");
			}
		});
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		form.set(maintenancePlan);
	}

	@Override
	public void select(MaintenanceProtocol entity) {
		form.select(entity);
	}

	protected void undo() {
		form.undo();
	}
}
