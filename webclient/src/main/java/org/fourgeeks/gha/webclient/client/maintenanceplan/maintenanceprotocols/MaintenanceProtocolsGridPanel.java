package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityModel;
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
	 * Create a {@link MaintenanceProtocolsGridPanel}
	 */
	public MaintenanceProtocolsGridPanel() {
		super();
		String stringKey = "maintenance-plan-associated-protocol-activities";
		addMember(new GHALabel(GHAStrings.get(stringKey)));

		GHANewButton addButton = new GHANewButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addActivity();
			}
		});
		GHASearchButton copyButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				coyActivities();
			}
		});
		GHADeleteButton deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		});

		VLayout sideButtons = GHAUiHelper.createBar(addButton, copyButton,
				deleteButton);

		VLayout gridLayout = new VLayout(10);
		gridLayout.addMembers(grid, stadisticDataLabel);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(gridLayout, sideButtons);

		addMember(mainLayout);
	}

	/**
	 * Add an activity to the maintenance plan protocol
	 */
	private void addActivity() {
		ListGridRecord records[] = grid.getRecords();
		List<MaintenanceActivity> blackList = new ArrayList<MaintenanceActivity>();

		for (int i = 0; i < records.length; i++) {
			MaintenanceProtocolsRecord record = (MaintenanceProtocolsRecord) records[i];
			blackList.add(record.toEntity().getMaintenanceActivity());
		}

		activitySearchForm.filterBy(blackList);
		activitySearchForm.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * MaintenanceProtocolsSelectionProducer
	 * #addMaintenanceProtocolsSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocols.MaintenanceProtocolsSelectionListener)
	 */
	@Override
	public void addMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener) {
		listeners.add(selectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener#canBeClosen
	 * (org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction)
	 */
	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener#canBeHidden
	 * (org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction)
	 */
	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener#close()
	 */
	@Override
	public void close() {
		grid.destroy();
		destroy();
	}

	/**
	 * Copy activites from other maintenance plan
	 */
	private void coyActivities() {
		ArrayList<MaintenancePlan> list = new ArrayList<MaintenancePlan>();
		list.add(maintenancePlan);

		planSearchForm.filterBy(list);
		planSearchForm.open();
	}

	/**
	 * Delete a selected record(s) or all the activities of the protocol
	 */
	private void delete() {
		final List<MaintenanceProtocols> selectedEntities = grid
				.getSelectedEntities();

		if (selectedEntities == null) {
			GHAAlertManager.confirm("maintenance-protocol-delete-confirm",
					new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value)
						deleteByMaintenancePlan();
					grid.focus();
				}
			});
		} else {
			if(selectedEntities.size() == 1){
				GHAAlertManager.confirm("activity-delete-confirm",
						new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value)
							deleteSelectedEntities(selectedEntities);
						grid.focus();
					}
				});
			}else{
				GHAAlertManager.confirm("activities-delete-confirm",
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
	}

	/**
	 * Delete all the activities of the protocol
	 */
	private void deleteByMaintenancePlan() {
		MaintenanceProtocolsModel.deleteByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				loadData();
				notifyMaintenanceProtocols(null);
				GHAAlertManager
				.alert("delete-protocol-activities-success");
			}
		});
	}

	/**
	 * Delete the selected entities
	 * 
	 * @param selectedEntities
	 *            the list of selected entities
	 */
	private void deleteSelectedEntities(
			final List<MaintenanceProtocols> selectedEntities) {
		MaintenanceProtocolsModel.delete(selectedEntities,
				new GHAAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
				notifyMaintenanceProtocols(null);
				GHAAlertManager.alert("delete-activities-success");
			}
		});
	}

	/**
	 * Load the data of the grid and other info of the form
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * MaintenanceProtocolsSelectionProducer
	 * #notifyMaintenanceProtocols(org.fourgeeks
	 * .gha.domain.gmh.MaintenanceProtocols)
	 */
	@Override
	public void notifyMaintenanceProtocols(MaintenanceProtocols entity) {
		for (MaintenanceProtocolsSelectionListener listener : listeners)
			listener.select(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * MaintenanceProtocolsSelectionProducer
	 * #removeMaintenanceProtocolsSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocols.MaintenanceProtocolsSelectionListener)
	 */
	@Override
	public void removeMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener) {
		listeners.remove(selectionListener);
	}

	/**
	 * save {@link MaintenanceProtocols} entity in the DB (associate an activity
	 * to the current maintenance plan)
	 * 
	 * @param activity
	 *            the activity to associate
	 */
	private void save(final MaintenanceActivity activity) {
		int ordinal = grid.getRecords().length + 1;

		MaintenanceProtocols entity = new MaintenanceProtocols();
		entity.setMaintenanceActivity(activity);
		entity.setMaintenancePlan(maintenancePlan);
		entity.setOrdinal(ordinal);

		MaintenanceProtocolsModel.save(entity,
				new GHAAsyncCallback<MaintenanceProtocols>() {
			@Override
			public void onSuccess(final MaintenanceProtocols result) {
				if (activity.getState() == ActivityState.CREATED)
					activity.setState(ActivityState.ACTIVE);

				MaintenanceActivityModel.update(activity,
						new GHAAsyncCallback<MaintenanceActivity>() {
					@Override
					public void onSuccess(
							MaintenanceActivity resultActivity) {
						loadData();
						notifyMaintenanceProtocols(result);
					}
				});
			}
		});
	}

	/**
	 * add in the DB the activities of a maintenance plan to the current
	 * maintenance plan
	 * 
	 * @param planFrom
	 *            the plan with the activities to copy
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
		loadData();
	}
}
