package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
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
public class MaintenancePlanAddForm extends GHAAddForm<MaintenancePlan>
		implements MaintenancePlanSelectionProducer {
	{
		form = new MaintenancePlanForm();
	}

	/**
	 * @param title
	 */
	public MaintenancePlanAddForm(String title) {
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
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		((MaintenancePlanSelectionProducer) form)
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		return;
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		((MaintenancePlanSelectionProducer) form)
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<MaintenancePlan>() {

			@Override
			public void onSuccess(MaintenancePlan arg0) {
				GHAErrorMessageProcessor.alert("maintenance-plan-save-success");
				hide();
			}
		});
	}

}
