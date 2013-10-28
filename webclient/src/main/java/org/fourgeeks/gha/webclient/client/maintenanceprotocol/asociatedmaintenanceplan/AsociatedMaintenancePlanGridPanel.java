package org.fourgeeks.gha.webclient.client.maintenanceprotocol.asociatedmaintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanMaintenanceProtocolModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenancePlanMaintenanceProtocolGrid;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenancePlanMaintenanceProtocolGridRecord;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenancePlanMaintenanceProtocolUtil;
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
public class AsociatedMaintenancePlanGridPanel extends VLayout implements
		GHAClosable, GHAHideable, MaintenanceProtocolSelectionListener,
		MaintenancePlanSelectionListener {

	private MaintenancePlanMaintenanceProtocolGrid grid;
	private MaintenanceProtocol maintenanceProtocol;
	private MaintenancePlanSearchForm searchForm;

	{
		grid = new MaintenancePlanMaintenanceProtocolGrid();
		grid.setMaintenancePlanFields();
		searchForm = new MaintenancePlanSearchForm();
	}

	/**
	 * @param subTab
	 */
	public AsociatedMaintenancePlanGridPanel(
			AsociatedMaintenancePlanSubTab subTab) {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		Label title = new Label(
				"Planes de Mantenimiento contienen este Protocolo");
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
						MaintenancePlanMaintenanceProtocol entity = grid
								.getSelectedEntity();
						MaintenancePlanMaintenanceProtocolModel.delete(
								entity.getId(), new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loadData();
									}
								});
					}

				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);

		// register as listener to the mantenanceplansearchform
		this.searchForm.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void close() {
		searchForm.close();
	}

	@Override
	public void hide() {
		searchForm.hide();
	}

	/**
	 * @param eiaType
	 */
	private void loadData() {
		MaintenancePlanMaintenanceProtocolModel
				.findByMaintenanceProtocol(
						this.maintenanceProtocol,
						new GHAAsyncCallback<List<MaintenancePlanMaintenanceProtocol>>() {

							@Override
							public void onSuccess(
									List<MaintenancePlanMaintenanceProtocol> result) {
								ListGridRecord array[] = MaintenancePlanMaintenanceProtocolUtil
										.toPlanRecords(result)
										.toArray(
												new MaintenancePlanMaintenanceProtocolGridRecord[] {});
								grid.setData(array);
							}

						});
	}

	// Consumer stuff
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		final MaintenancePlanMaintenanceProtocol entity = new MaintenancePlanMaintenanceProtocol();
		entity.setMaintenancePlan(maintenancePlan);
		entity.setMaintenanceProtocol(this.maintenanceProtocol);
		MaintenancePlanMaintenanceProtocolModel.save(entity,
				new GHAAsyncCallback<MaintenancePlanMaintenanceProtocol>() {

					@Override
					public void onSuccess(
							MaintenancePlanMaintenanceProtocol result) {
						loadData();
					}
				});
	}

	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		this.maintenanceProtocol = maintenanceProtocol;
		loadData();
	}

	@Override
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}
}
