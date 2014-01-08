package org.fourgeeks.gha.webclient.client.maintenanceplan.eianoservice;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
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

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author caparicio
 * 
 */
public class EiaNoServiceMaintenanceGridPanel extends GHAVerticalLayout
		implements ClosableListener, HideableListener,
		MaintenancePlanSelectionListener {

	private EiaNoServiceMaintenanceGrid grid;
	private MaintenancePlan maintenancePlan;
	{
		grid = new EiaNoServiceMaintenanceGrid();
	}

	/**
	 * 
	 */
	public EiaNoServiceMaintenanceGridPanel() {
		super();
		GHALabel title = new GHALabel(
				GHAStrings.get("eia-on-pending-maintenance"));
		addMember(title);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);
	}

	private void loadData() {
		MaintenancePlanModel.findDamageEiaByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<List<Eia>>() {
					@Override
					public void onSuccess(List<Eia> result) {
						try {
							List<EiaNoServiceMaintenanceRecord> list = EiaNoServiceMaintenanceUtil
									.toEiaNoServiceMaintenancePlanGridRecords(result);
							ListGridRecord array[] = list
									.toArray(new EiaNoServiceMaintenanceRecord[] {});
							grid.setData(array);
						} catch (Exception e) {
							Window.alert("Excepción: " + e);
						}
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
