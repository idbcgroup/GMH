package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanMaintenanceProtocolModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class MaintenanceProtocolGridPanel extends VLayout implements GHAClosable, GHAHideable, MaintenancePlanSelectionListener, MaintenanceProtocolSelectionListener{

	private MaintenancePlanMaintenanceProtocolGrid grid = new MaintenancePlanMaintenanceProtocolGrid();
	private MaintenancePlan maintenancePlan;
	private MaintenanceProtocolSearchForm searchForm;

	{
		grid = new MaintenancePlanMaintenanceProtocolGrid();
		grid.setMaintenanceProtocolFields();
		searchForm = new MaintenanceProtocolSearchForm();
	}

	public MaintenanceProtocolGridPanel() {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		Label title = new Label("<h3>Protocolos del Plan</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");

		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						searchForm.open();
					}
				}),
				new GHAImgButton("../resources/icons/edit.png"),
				new GHAImgButton("../resources/icons/delete.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						MaintenancePlanMaintenanceProtocol entity = grid.getSelectedEntity();
						MaintenancePlanMaintenanceProtocolModel.delete(entity.getId(), new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								loadData();
							}
						});
					}
				})
				);

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(title,mainPanel);

		//register as listener to the maintenanceprotocol form
		this.searchForm.addMaintenanceProtocolSelectionListener(this);
	}

	@Override
	public void close() {
		//TODO:
	}

	@Override
	public void hide() {
		super.hide();
	}

	public void loadData(){
		MaintenancePlanMaintenanceProtocolModel.findByMaintenancePlan(maintenancePlan, new GHAAsyncCallback<List<MaintenancePlanMaintenanceProtocol>>() {

			@Override
			public void onSuccess(
					List<MaintenancePlanMaintenanceProtocol> result) {
				ListGridRecord array[] = MaintenancePlanMaintenanceProtocolUtil.toProtocolRecords(result).toArray(new MaintenancePlanMaintenanceProtocolRecord[]{});
				grid.setData(array);
			}

		});
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
		loadData();
	}

	//Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		final MaintenancePlanMaintenanceProtocol entity = new MaintenancePlanMaintenanceProtocol();
		entity.setMaintenancePlan(this.maintenancePlan);
		entity.setMaintenanceProtocol(maintenanceProtocol);
		MaintenancePlanMaintenanceProtocolModel.save(entity, new GHAAsyncCallback<MaintenancePlanMaintenanceProtocol>() {

			@Override
			public void onSuccess(MaintenancePlanMaintenanceProtocol result) {
				loadData();
			}
		});
	}
}
