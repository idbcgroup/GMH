package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACopyButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySearchForm;
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
public class MaintenanceActivityDefinitionFormPanel extends VLayout implements
		ClosableListener, HideableListener,
		MaintenanceActivitySelectionProducer {

	private final MaintenanceActivityForm form = new MaintenanceActivityForm();
	private MaintenanceActivitySearchForm activitySearchForm;

	{
		activitySearchForm = new MaintenanceActivitySearchForm(
				GHAStrings.get("maintenance-activity"));

		activitySearchForm
				.addMaintenanceActivitySelectionListener(new MaintenanceActivitySelectionListener() {
					@Override
					public void select(MaintenanceActivity activity) {
						form.set(maintenanceActivity);
					}
				});
	}

	/**
	 * 
	 */
	public MaintenanceActivityDefinitionFormPanel() {
		setWidth100();

		GHASaveButton saveButton = new GHASaveButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				save();
			}
		});
		GHAUndoButton undoButton = new GHAUndoButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				undo();
			}
		});
		GHADeleteButton deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});
		GHAEditButton editButton = new GHAEditButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				searchActivity();
			}
		});
		GHAImgButton copyButton = new GHACopyButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});

		VLayout sideButtons = GHAUiHelper.createBar(saveButton, undoButton,
				deleteButton, editButton, copyButton);

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);
	}

	protected void searchActivity() {
		activitySearchForm.open();
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

			GHAAlertManager.askYesNoCancel(GHAStrings.get("information"),
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

			GHAAlertManager.askYesNoCancel(GHAStrings.get("information"),
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
				GHAAlertManager.alert("maintenance-activity-save-success");
			}
		});
	}

	protected void undo() {
		form.undo();
	}
}
