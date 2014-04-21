package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author naramirez
 */
public class MaintenanceActivityAddForm extends GHAAddForm<MaintenanceActivity>
		implements MaintenanceActivitySelectionProducer {
	{
		form = new MaintenanceActivityForm();
	}

	/**
	 * @param title
	 */
	public MaintenanceActivityAddForm(String title) {
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
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener listener) {
		((MaintenanceActivitySelectionProducer) form)
				.addMaintenanceActivitySelectionListener(listener);
	}

	@Override
	public void notifyMaintenanceActivity(MaintenanceActivity activity) {
		return;
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener listener) {
		final MaintenanceActivitySelectionProducer producer = (MaintenanceActivitySelectionProducer) form;
		producer.removeMaintenanceActivitySelectionListener(listener);
	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<MaintenanceActivity>() {
			@Override
			public void onSuccess(MaintenanceActivity result) {
				GHAErrorMessageProcessor.alert("activity-save-success");
				hide();
			}
		});
	}
}
