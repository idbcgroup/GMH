package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsModel;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsSelectionProducer;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class MaintenanceProtocolsGridPanel extends GHAVerticalLayout implements
		MaintenanceProtocolsSelectionProducer, ClosableListener,
		HideableListener, MaintenancePlanSelectionListener {

	private List<MaintenanceProtocolsSelectionListener> listeners;

	private MaintenanceActivitySearchForm activitySearchForm;
	private MaintenanceProtocolsGrid grid;
	private MaintenancePlan maintenancePlan;
	private MaintenancePlanSearchForm planSearchForm;

	private MaintenanceProtocolStadisticDataLabel stadisticDataLabel;

	{
		listeners = new ArrayList<MaintenanceProtocolsSelectionListener>();

		grid = new MaintenanceProtocolsGrid();
		stadisticDataLabel = new MaintenanceProtocolStadisticDataLabel();

		planSearchForm = new MaintenancePlanSearchForm(
				GHAStrings.get("maintenance-plan"));
		planSearchForm
				.addMaintenancePlanSelectionListener(new MaintenancePlanSelectionListener() {
					@Override
					public void select(MaintenancePlan planFrom) {
						save(planFrom);
					}

				});

		activitySearchForm = new MaintenanceActivitySearchForm(
				GHAStrings.get("maintenance-activity"));
		activitySearchForm
				.addMaintenanceActivitySelectionListener(new MaintenanceActivitySelectionListener() {
					@Override
					public void select(MaintenanceActivity activity) {
						save(activity);
					}
				});
	}

	/**
	 * 
	 */
	public MaintenanceProtocolsGridPanel() {
		super();
		String stringKey = "maintenance-plan-associated-protocol-activities";
		addMember(new GHALabel(GHAStrings.get(stringKey)));

		GHASearchButton searchButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				searchActivity();
			}
		});
		GHADeleteButton deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		});
		GHAEditButton copyButton = new GHAEditButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				searchOtherPlan();
			}
		});

		VLayout sideButtons = GHAUiHelper.createBar(searchButton, deleteButton,
				copyButton);

		VLayout gridLayout = new VLayout(10);
		gridLayout.addMembers(grid, stadisticDataLabel);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(gridLayout, sideButtons);

		addMember(mainLayout);
	}

	@Override
	public void addMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener) {
		listeners.add(selectionListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() {
		grid.destroy();
		destroy();
	}

	private void delete() {
		final List<MaintenanceProtocols> selectedEntities = grid
				.getSelectedEntities();

		if (selectedEntities == null) {
			GHANotification.confirm(GHAStrings.get("protocol"),
					GHAStrings.get("maintenance-protocol-delete-confirm"),
					new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value)
								deleteByMaintenancePlan();
							grid.focus();
						}
					});
		} else {
			String message = selectedEntities.size() == 1 ? GHAStrings
					.get("activity-delete-confirm") : GHAStrings
					.get("activities-delete-confirm");

			GHANotification.confirm(GHAStrings.get("protocol"), message,
					new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value)
								deleteSelectedEntities(selectedEntities);
							grid.focus();
						}
					});
		}
	}

	private void deleteByMaintenancePlan() {
		MaintenanceProtocolsModel.deleteByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						loadData();
						notifyMaintenanceProtocols(null);
						GHANotification
								.alert("delete-protocol-activities-success");
					}
				});
	}

	private void deleteSelectedEntities(
			final List<MaintenanceProtocols> selectedEntities) {
		MaintenanceProtocolsModel.delete(selectedEntities,
				new GHAAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						loadData();
						notifyMaintenanceProtocols(null);
						GHANotification.alert("delete-activities-success");
					}
				});
	}

	private void loadData() {
		MaintenanceProtocolsModel.findByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<List<MaintenanceProtocols>>() {
					@Override
					public void onSuccess(List<MaintenanceProtocols> result) {
						MaintenanceProtocolsRecord array[] = MaintenanceProtocolsUtil
								.toGridRecordsArray(result);
						grid.setData(array);
					}
				});

		MaintenanceProtocolsModel.getStadisticInfo(maintenancePlan,
				new GHAAsyncCallback<MaintenanceProtocolStadisticData>() {
					@Override
					public void onSuccess(
							MaintenanceProtocolStadisticData result) {
						stadisticDataLabel.setStadisticInfo(result);
					}
				});
	}

	@Override
	public void notifyMaintenanceProtocols(MaintenanceProtocols entity) {
		for (MaintenanceProtocolsSelectionListener listener : listeners)
			listener.select(entity);
	}

	@Override
	public void removeMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener) {
		listeners.remove(selectionListener);
	}

	private void save(MaintenanceActivity activity) {
		int ordinal = grid.getRecords().length + 1;

		MaintenanceProtocols entity = new MaintenanceProtocols();
		entity.setMaintenanceActivity(activity);
		entity.setMaintenancePlan(maintenancePlan);
		entity.setOrdinal(ordinal);

		MaintenanceProtocolsModel.save(entity,
				new GHAAsyncCallback<MaintenanceProtocols>() {
					@Override
					public void onSuccess(MaintenanceProtocols result) {
						loadData();
						notifyMaintenanceProtocols(result);
					}
				});
	}

	private void save(MaintenancePlan planFrom) {
		MaintenanceProtocolsModel.copyActivities(planFrom, maintenancePlan,
				new GHAAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						loadData();
						notifyMaintenanceProtocols(null);
					}
				});
	}

	private void searchActivity() {
		ListGridRecord records[] = grid.getRecords();
		List<MaintenanceActivity> blackList = new ArrayList<MaintenanceActivity>();

		for (int i = 0; i < records.length; i++) {
			MaintenanceProtocolsRecord record = (MaintenanceProtocolsRecord) records[i];
			blackList.add(record.toEntity().getMaintenanceActivity());
		}

		activitySearchForm.filterBy(blackList);
		activitySearchForm.open();
	}

	private void searchOtherPlan() {
		ArrayList<MaintenancePlan> list = new ArrayList<MaintenancePlan>();
		list.add(maintenancePlan);

		planSearchForm.filterBy(list);
		planSearchForm.open();
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
		loadData();
	}
}
