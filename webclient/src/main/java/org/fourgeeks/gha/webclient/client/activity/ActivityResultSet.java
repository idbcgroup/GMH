package org.fourgeeks.gha.webclient.client.activity;

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
 * 
 * @author caparicio
 * 
 */
public class ActivityResultSet extends GHAResultSet<MaintenanceActivity>
		implements ActivitySelectionProducer {
	private final List<ActivitySelectionListener> listeners = new ArrayList<ActivitySelectionListener>();
	private final ActivityGrid grid;
	private final ResultSetContainerType containerType;

	/**
	 * @param container
	 */
	public ActivityResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;

		grid = new ActivityGrid() {
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifyMSelectedActivity();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifyMSelectedActivity();
					}
				})));

		addMember(gridPanel);
	}

	@Override
	public void clean() {
		grid.setData(new MaintenancePlanRecord[] {});
		showResultsSize(null, true);
	}

	protected void notifyMSelectedActivity() {
		GHAGridRecord<MaintenanceActivity> selectedRecord = grid
				.getSelectedRecord();
		if (selectedRecord == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}
		notifyActivity(selectedRecord.toEntity());
		hide();
		clean();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

	@Override
	public void addActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener) {
		listeners.add(activitySelectionListener);
	}

	@Override
	public void removeActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener) {
		listeners.remove(activitySelectionListener);
	}

	@Override
	public void notifyActivity(MaintenanceActivity activity) {
		for (ActivitySelectionListener listener : listeners)
			listener.select(activity);
	}

	@Override
	public void setRecords(List<MaintenanceActivity> records,
			boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyActivity(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		ListGridRecord[] array = ActivityUtil.toGridRecords(records).toArray(
				new MaintenancePlanRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

}
