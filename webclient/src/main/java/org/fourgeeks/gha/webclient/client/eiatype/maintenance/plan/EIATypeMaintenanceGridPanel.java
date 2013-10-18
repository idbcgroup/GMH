package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanGrid;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanRecord;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanUtil;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaintenanceGridPanel extends VLayout implements
		GHAClosable, GHAHideable, EIATypeSelectionListener,
		MaintenancePlanSelectionListener {

	private EiaTypeMaintenancePlanGrid grid;
	private EiaType eiaType;
	private MaintenancePlanSearchForm searchForm;

	{
		grid = new EiaTypeMaintenancePlanGrid();
		grid.setMaintenancePlanFields();
		searchForm = new MaintenancePlanSearchForm();
	}

	public EIATypeMaintenanceGridPanel() {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		GHALabel title = new GHALabel("Planes de Mantenimiento asociados al tipo de equipo");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						searchForm.open();
					}
				}), new GHAImgButton("../resources/icons/edit.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EiaTypeMaintenancePlan entity = grid
								.getSelectedEntity();
						EiaTypeMaintenancePlanModel.delete(entity.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loadData();
									}

								});

					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/set.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// mainenanceProtocolForm.animateShow(AnimationEffect.FLY);
					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(mainPanel);

		// register as listener to the maintenance plan search form
		this.searchForm.addMaintenancePlanSelectionListener(this);
	}

	private void loadData() {
		EiaTypeMaintenancePlanModel.findByEiaType(this.eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {

					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						ListGridRecord array[] = EiaTypeMaintenancePlanUtil
								.toMaintenancePlanGridRecords(result).toArray(
										new EiaTypeMaintenancePlanRecord[] {});
						grid.setData(array);
					}
				});
	}

	@Override
	public void close() {
		searchForm.close();
	}

	@Override
	public void hide() {
		searchForm.hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
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
		final EiaTypeMaintenancePlan eiaTypeMaintenancePlan = new EiaTypeMaintenancePlan();
		eiaTypeMaintenancePlan.setEiaType(this.eiaType);
		eiaTypeMaintenancePlan.setMaintenancePlan(maintenancePlan);

		EiaTypeMaintenancePlanModel.save(eiaTypeMaintenancePlan,
				new GHAAsyncCallback<EiaTypeMaintenancePlan>() {

					@Override
					public void onSuccess(EiaTypeMaintenancePlan result) {
						loadData();
					}
				});
	}

}
