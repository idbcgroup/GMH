package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class MaintenancePlanResultSet extends GHAResultSet<MaintenancePlan>
		implements MaintenancePlanSelectionProducer {
	private List<MaintenancePlanSelectionListener> listeners = new ArrayList<MaintenancePlanSelectionListener>();
	private MaintenancePlanGrid grid = new MaintenancePlanGrid();

	{
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifyMSelectedMaintenancePlan();
			}
		});
	}

	public MaintenancePlanResultSet() {
		super(GHAStrings.get("search-results"));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifyMSelectedMaintenancePlan();
					}
				})));
		addMember(gridPanel);
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);

	}

	@Override
	public void clean() {
		grid.setData(new MaintenancePlanRecord[] {});
		showResultsSize(null, true);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		for (MaintenancePlanSelectionListener listener : listeners)
			listener.select(maintenancePlan);
	}

	protected void notifyMSelectedMaintenancePlan() {
		GHAGridRecord<MaintenancePlan> selectedRecord = grid
				.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert("record-not-selected");
			return;
		}
		notifyMaintenancePlan(selectedRecord.toEntity());
		hide();
		grid.removeSelectedData();
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);

	}

	@Override
	public void setRecords(List<MaintenancePlan> records,
			boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyMaintenancePlan(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		ListGridRecord[] array = MaintenancePlanUtil.toGridRecords(records)
				.toArray(new MaintenancePlanRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

}
