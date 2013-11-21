package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class AsociatedEiatypeGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, MaintenancePlanSelectionListener {

	private EiaTypeMaintenancePlanGrid grid;
	{
		grid = new EiaTypeMaintenancePlanGrid();
		grid.setEiaTypeFields();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public AsociatedEiatypeGridPanel() {
		super();
		GHALabel title = new GHALabel("eia-type-on-maintenance-plan");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHADeleteButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						deleteSelected();
					}

				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);
	}

	private void loadData(MaintenancePlan maintenancePlan) {
		EiaTypeMaintenancePlanModel.findByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {

					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						ListGridRecord array[] = EiaTypeMaintenancePlanUtil
								.toEiaTypeGridRecords(result).toArray(
										new EiaTypeMaintenancePlanRecord[] {});
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
		loadData(maintenancePlan);
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	private void deleteSelected() {
		EiaTypeMaintenancePlan entity = grid.getSelectedEntity();
		EiaTypeMaintenancePlanModel.delete(entity.getId(),
				new GHAAsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						grid.removeSelectedData();
					}

				});
	}
}
