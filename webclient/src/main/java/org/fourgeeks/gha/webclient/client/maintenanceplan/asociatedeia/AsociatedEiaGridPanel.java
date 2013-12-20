package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author caparicio
 * 
 */
public class AsociatedEiaGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, MaintenancePlanSelectionListener {

	private EiaMaintenancePlanGrid grid;
	private MaintenancePlan maintenancePlan;
	{
		grid = new EiaMaintenancePlanGrid();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public AsociatedEiaGridPanel() {
		super();
		GHALabel title = new GHALabel(GHAStrings.get("eia-on-maintenance-plan"));
		addMember(title);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);
	}

	private void loadData() {
		MaintenancePlanModel.findEiaByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<List<EiaMaintenancePlanification>>() {

					@Override
					public void onSuccess(
							List<EiaMaintenancePlanification> result) {

						List<Eia> equipmentList = new ArrayList<Eia>();
						for (EiaMaintenancePlanification eiaMaintenancePlanification : result) {
							equipmentList.add(eiaMaintenancePlanification
									.getEia());
						}

						ListGridRecord array[] = EiaMaintenancePlanUtil
								.toEiaMaintenancePlanGridRecords(equipmentList,
										result).toArray(
										new EiaMaintenancePlanRecord[] {});
						grid.setData(array);
					}
				});
	}

	@Override
	public void close() {
		grid.destroy();
		destroy();
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
		loadData();
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}
}
