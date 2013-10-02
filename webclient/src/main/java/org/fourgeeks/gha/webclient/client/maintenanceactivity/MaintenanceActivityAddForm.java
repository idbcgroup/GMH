package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
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

public class MaintenanceActivityAddForm extends GHASlideInWindow implements
		MaintenanceActivitySelectionProducer, MaintenanceActivitySelectionListener {
	private MaintenanceActivityForm maintenanceActivityForm;
	{
		maintenanceActivityForm = new MaintenanceActivityForm();
	}

	public MaintenanceActivityAddForm() {
		super(2);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		Label title = new Label("<h3>Nuevo Actividad</h3>");
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
		gridPanel.addMembers(maintenanceActivityForm, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
		
		fillExtras();
	}

	protected void cancel() {
		maintenanceActivityForm.hide();
		super.hide();
	}

	private void fillExtras() {
		//TODO: 
	}

	private void save() {
		
	}
	
	public void show(){
		super.show();
		maintenanceActivityForm.show();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		destroy();
	}

	//Producer/Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer#addMaintenanceActivitySelectionListener(org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		maintenanceActivityForm.addMaintenanceActivitySelectionListener(maintenanceActivitySelectionListener);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer#removeMaintenanceActivitySelectionListener(org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		maintenanceActivityForm.removeMaintenanceActivitySelectionListener(maintenanceActivitySelectionListener);
	}
}
