package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenanceActivityAddForm extends GHASlideInWindow implements
		MaintenanceActivitySelectionProducer,
		MaintenanceActivitySelectionListener {
	private MaintenanceActivityForm maintenanceActivityForm;
	{
		maintenanceActivityForm = new MaintenanceActivityForm();
	}

	public MaintenanceActivityAddForm() {
		super();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		GHALabel title = new GHALabel("Nueva Actividad");
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
		gridPanel.addMembers(maintenanceActivityForm, new LayoutSpacer(),
				sideButtons);
		addMember(gridPanel);

		// register as listener to the maintenanceActivityForm
		maintenanceActivityForm.addMaintenanceActivitySelectionListener(this);
	}

	protected void cancel() {
		maintenanceActivityForm.hide();
		super.hide();
	}

	private void save() {
		maintenanceActivityForm.save();
	}

	@Override
	public void show() {
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

	@Override
	public void open() {
		this.show();
		animateShow(AnimationEffect.FLY);
	}

	// Producer/Consumer stuff
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		cancel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #addMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		maintenanceActivityForm
				.addMaintenanceActivitySelectionListener(maintenanceActivitySelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #removeMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		maintenanceActivityForm
				.removeMaintenanceActivitySelectionListener(maintenanceActivitySelectionListener);
	}
}
