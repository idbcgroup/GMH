package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenancePlanAddForm extends GHASlideInWindow implements
		MaintenancePlanSelectionProducer, MaintenancePlanSelectionListener {
	private MaintenancePlanForm maintenancePlanForm;


	{
		maintenancePlanForm = new MaintenancePlanForm();
	}

	public MaintenancePlanAddForm() {
		super(2);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		Label title = new Label("<h3>Nuevo Plan de Mantenimiento</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						cancel();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(maintenancePlanForm, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
		
		//register as listener to the maintenancePlanForm
		maintenancePlanForm.addMaintenancePlanSelectionListener(this);
		
		fillExtras();
	}

	protected void cancel() {
		maintenancePlanForm.hide();
		super.hide();
	}
	
	private void save(){
		maintenancePlanForm.save();
	}

	private void fillExtras() {
		//TODO: 
	}
	
	public void show(){
		super.show();
		maintenancePlanForm.show();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		maintenancePlanForm.destroy();
		destroy();
	}

	//Producer/Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer#addMaintenancePlanSelectionListener(org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		maintenancePlanForm.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer#removeMaintenancePlanSelectionListener(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		maintenancePlanForm.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		//called when the maintenanceplanform finish saving new entity
		cancel();
	}
}
