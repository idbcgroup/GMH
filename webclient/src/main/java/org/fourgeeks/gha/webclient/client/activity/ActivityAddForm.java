package org.fourgeeks.gha.webclient.client.activity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityForm;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @author caparicio
 * 
 */
public class ActivityAddForm extends GHAAddForm<MaintenanceActivity> implements
		ActivitySelectionProducer {
	{
		form = new MaintenanceActivityForm();
	}

	/**
	 * @param title
	 */
	public ActivityAddForm(String title) {
		super(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACloseButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
	}

	@Override
	protected void save() {
		// form.save(new GHAAsyncCallback<MaintenancePlan>() {
		//
		// @Override
		// public void onSuccess(MaintenancePlan arg0) {
		// GHAAlertManager.alert("maintenance-plan-save-success");
		// hide();
		// }
		// });
	}

	@Override
	public void addActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener) {
		((ActivitySelectionProducer) form)
				.addActivitySelectionListener(activitySelectionListener);
	}

	@Override
	public void removeActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener) {
		((ActivitySelectionProducer) form)
				.removeActivitySelectionListener(activitySelectionListener);
	}

	@Override
	public void notifyActivity(MaintenanceActivity activity) {
		return;
	}

}
