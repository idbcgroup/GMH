package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolGrid;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolModel;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolRecord;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolUtil;

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

	private MaintenanceProtocolGrid grid = new MaintenanceProtocolGrid();
	private MaintenancePlan maintenancePlan;
	private MaintenanceProtocolSearchForm searchForm;
	
	{
		grid = new MaintenanceProtocolGrid();
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
						MaintenanceProtocol maintenanceProtocol = grid.getSelectedEntity();
						maintenanceProtocol.setMaintenancePlan(null);
						MaintenanceProtocolModel.update(maintenanceProtocol, new GHAAsyncCallback<MaintenanceProtocol>() {

							@Override
							public void onSuccess(MaintenanceProtocol result) {
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
		MaintenanceProtocolModel.findByMaintenancePlan(this.maintenancePlan, new GHAAsyncCallback<List<MaintenanceProtocol>>() {
			
			@Override
			public void onSuccess(List<MaintenanceProtocol> result) {
				ListGridRecord array[] = MaintenanceProtocolUtil.toGridRecords(result).toArray(new MaintenanceProtocolRecord[]{});
				grid.setData(array);
			}
		});
	}

	//Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		maintenanceProtocol.setMaintenancePlan(this.maintenancePlan);
		MaintenanceProtocolModel.update(maintenanceProtocol, new GHAAsyncCallback<MaintenanceProtocol>() {

			@Override
			public void onSuccess(MaintenanceProtocol result) {
				loadData();
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
}
