package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanRecord;

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
public class MaintenanceActivityResultSet extends
		GHAResultSet<MaintenanceActivity> implements
		MaintenanceActivitySelectionProducer {
	private final List<MaintenanceActivitySelectionListener> listeners = new ArrayList<MaintenanceActivitySelectionListener>();
	private final MaintenanceActivityGrid grid;
	private final ResultSetContainerType containerType;

	/**
	 * @param container
	 */
	public MaintenanceActivityResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		
		grid = new MaintenanceActivityGrid(){
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedMaintenanceActivity();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
		
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedMaintenanceActivity();
					}
				})));
//		if (containerType == ResultSetContainerType.SEARCH_FORM) {
//			setHeight(getHeight() - 42);
//		}

		addMember(gridPanel);
	}

	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);

	}

	@Override
	public void clean() {
		grid.setData(new MaintenancePlanRecord[] {});
		showResultsSize(null, true);
	}

	@Override
	public void notifyMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) {
		for (MaintenanceActivitySelectionListener listener : listeners)
			listener.select(maintenanceActivity);
	}

	protected void notifySelectedMaintenanceActivity() {
		GHAGridRecord<MaintenanceActivity> selectedRecord = grid
				.getSelectedRecord();
		if (selectedRecord == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}
		notifyMaintenanceActivity(selectedRecord.toEntity());
		hide();
		clean();
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener) {
		listeners.remove(selectionListener);

	}

	@Override
	public void setRecords(List<MaintenanceActivity> records,
			boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyMaintenanceActivity(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		ListGridRecord[] array = MaintenanceActivityUtil.toGridRecords(records)
				.toArray(new MaintenanceActivityGridRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

}
