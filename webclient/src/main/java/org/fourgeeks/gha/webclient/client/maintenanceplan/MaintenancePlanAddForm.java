package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenancePlanAddForm extends GHAAddForm<MaintenancePlan>
		implements MaintenancePlanSelectionProducer,
		MaintenancePlanSelectionListener {
	{
		form = new MaintenancePlanForm();
	}

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

	private void save() {
		form.save();
	}

	// @Override
	// public void show() {
	// super.show();
	// maintenancePlanForm.show();
	// }
	//
	// @Override
	// public void close() {
	// maintenancePlanForm.destroy();
	// destroy();
	// }
	//
	// @Override
	// public void open() {
	// this.show();
	// animateShow(AnimationEffect.FLY);
	// }

	// Producer/Consumer stuff
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #addMaintenancePlanSelectionListener(org.fourgeeks
	 * .gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		((MaintenancePlanSelectionProducer) form)
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #removeMaintenancePlanSelectionListener(org
	 * .fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		((MaintenancePlanSelectionProducer) form)
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		// called when the maintenanceplanform finish saving new entity
		cancel();
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		// TODO Auto-generated method stub

	}

}
