package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaintenanceActivityInformationFormPanel extends GHAVerticalLayout
		implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener,
		MaintenanceActivitySelectionProducer {

	private final MaintenanceActivityForm form = new MaintenanceActivityForm();

	/**
	 * 
	 */
	public MaintenanceActivityInformationFormPanel() {

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
	}

	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener listener) {
		form.addMaintenanceActivitySelectionListener(listener);
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
							GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);
						}
					}, new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							GHAPlaceSet
									.closeCurrentPlace(HideCloseAction.DISCARD);
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
							GHAPlaceSet.hideCurrentPlace(HideCloseAction.SAVE);
						}
					}, new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							GHAPlaceSet
									.hideCurrentPlace(HideCloseAction.DISCARD);
						}
					}, null);
			return false;
		}
		return true;
	}

	@Override
	public void close() {
		destroy();
		form.destroy();
	}

	@Override
	public void notifyMaintenanceActivity(MaintenanceActivity activity) {
		return;
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener listener) {
		form.removeMaintenanceActivitySelectionListener(listener);
	}

	private void save() {
		form.update(new GHAAsyncCallback<MaintenanceActivity>() {
			@Override
			public void onSuccess(MaintenanceActivity result) {
				GHANotification.alert("maintenance-activity-save-success");
			}
		});
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		form.set(maintenanceActivity);
	}

	protected void undo() {
		form.undo();
	}
}
