package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author naramirez
 */
public class MaintenancePlanResultSet extends GHAResultSet<MaintenancePlan>
		implements MaintenancePlanSelectionProducer {
	private final List<MaintenancePlanSelectionListener> listeners = new ArrayList<MaintenancePlanSelectionListener>();
	private final MaintenancePlanGrid grid = new MaintenancePlanGrid();
	private final ResultSetContainerType containerType;

	{
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifyMSelectedMaintenancePlan();
			}
		});
	}

	/**
	 * @param container
	 */
	public MaintenancePlanResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifyMSelectedMaintenancePlan();
					}
				})));
		if (containerType == ResultSetContainerType.SEARCH_FORM) {
			setHeight(getHeight() - 42);
		}

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
			GHAAlertManager.alert("record-not-selected");
			return;
		}
		notifyMaintenancePlan(selectedRecord.toEntity());
		hide();
		clean();
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

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		if (containerType == ResultSetContainerType.SEARCH_FORM) {
			setHeight(getHeight() - 35);
		}
	}

}
