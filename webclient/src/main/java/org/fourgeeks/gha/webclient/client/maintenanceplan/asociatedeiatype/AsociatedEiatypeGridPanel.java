package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeGrid;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeUtil;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;

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
public class AsociatedEiatypeGridPanel extends VLayout implements GHAClosable, GHAHideable, MaintenancePlanSelectionListener, EIATypeSelectionListener {

	private EIATypeGrid grid;
	private MaintenancePlan maintenancePlan;
	private EIATypeSearchForm searchForm;
	
	{
		grid = new EIATypeGrid();
		searchForm = new EIATypeSearchForm();
		searchForm.addEiaTypeSelectionListener(new EIATypeSelectionListener() {
			
			@Override
			public void select(EiaType eiaType) {
				final EiaTypeMaintenancePlan eiaTypeMaintenancePlan = new EiaTypeMaintenancePlan();
				eiaTypeMaintenancePlan.setEiaType(eiaType);
				eiaTypeMaintenancePlan.setMaintenancePlan(AsociatedEiatypeGridPanel.this.maintenancePlan);
				
				//TODO: save the relation
				EiaTypeMaintenancePlanModel.save(eiaTypeMaintenancePlan, new GHAAsyncCallback<EiaTypeMaintenancePlan>() {
					
					@Override
					public void onSuccess(EiaTypeMaintenancePlan result) {
						loadData();
					}
				});
			}
		});
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public AsociatedEiatypeGridPanel(
			AsociatedEiaTypeSubTab eIATypeEquipmentSubTab) {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		Label title = new Label("<h3>Tipos de Equipo que estan asociados al Plan de Mantenimiento</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						searchForm.open();
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						EiaType eiaType = grid.getSelectedEntity();
						//TODO
					}

				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);
	}

	/**
	 * @param eiaType
	 */
	private void loadData() {
		EiaTypeMaintenancePlanModel.findByMaintenancePlan(this.maintenancePlan, new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> result) {
				ListGridRecord array[] = EIATypeUtil.toGridRecords(result).toArray(new EIATypeRecord[]{});
				grid.setData(array);
			}
		});
	}

	@Override
	public void close() {
		//Close the search/select form
	}

	@Override
	public void hide() {
		//Hide the search/select form
		// super.hide();
	}

	//Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
		loadData();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
