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
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsModel;

import com.google.gwt.user.client.Window;
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
		ClosableListener, HideableListener, MaintenancePlanSelectionListener {

	private MaintenanceActivitySearchForm activitySearchForm;
	private MaintenancePlanSearchForm planSearchForm;

	private MaintenanceProtocolsGrid grid;
	private MaintenancePlan maintenancePlan;

	{
		grid = new MaintenanceProtocolsGrid();

		planSearchForm = new MaintenancePlanSearchForm(
				GHAStrings.get("search-maintenance-plan"));
		planSearchForm
				.addMaintenancePlanSelectionListener(new MaintenancePlanSelectionListener() {
					@Override
					public void select(MaintenancePlan planFrom) {
						MaintenanceProtocolsModel.copyActivities(planFrom,
								maintenancePlan, new GHAAsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										loadData();
									}
								});

					}
				});

		activitySearchForm = new MaintenanceActivitySearchForm(
				GHAStrings.get("search-maintenance-activity"));
		activitySearchForm
				.addMaintenanceActivitySelectionListener(new MaintenanceActivitySelectionListener() {
					@Override
					public void select(MaintenanceActivity activity) {
						MaintenanceProtocolsModel.save(activity,
								maintenancePlan, 0,
								new GHAAsyncCallback<MaintenanceProtocols>() {
									@Override
									public void onSuccess(
											MaintenanceProtocols result) {
										loadData();
									}
								});
					}
				});
	}

	/**
	 * 
	 */
	public MaintenanceProtocolsGridPanel() {
		super();
		GHALabel title = new GHALabel(
				GHAStrings.get("eia-type-on-maintenance-plan"));
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}), new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deleteSelected();
			}

		}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);
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
						Window.alert("Hi bitch!!!");
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

	private void search() {
		ListGridRecord records[] = grid.getRecords();
		List<MaintenanceActivity> blackList = new ArrayList<MaintenanceActivity>();

		for (int i = 0; i < records.length; i++) {
			MaintenanceProtocolsRecord record = (MaintenanceProtocolsRecord) records[i];
			blackList.add(record.toEntity().getMaintenanceActivity());
		}

		activitySearchForm.filterBy(blackList);
		activitySearchForm.open();
	}

	private void deleteSelected() {
		MaintenanceProtocols entity = grid.getSelectedEntity();
		MaintenanceProtocolsModel.delete(entity.getId(),
				new GHAAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						grid.removeSelectedData();
					}
				});
	}
}
