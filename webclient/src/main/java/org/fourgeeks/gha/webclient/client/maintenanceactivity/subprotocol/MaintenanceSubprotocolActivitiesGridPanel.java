package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesGridPanel extends
		GHAFormLayout implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener {

	private MaintenanceActivitySearchForm activitySearchForm;
	private MaintenanceSubprotocolActivitiesGrid grid;
	private MaintenanceActivity maintenanceActivity;

	{
		grid = new MaintenanceSubprotocolActivitiesGrid();

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
	public MaintenanceSubprotocolActivitiesGridPanel() {
		super();
		setWidth100();

		addMember(new GHALabel(GHAStrings.get("subprotocol-activities")));

		GHANewButton addButton = new GHANewButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addActivity();
			}
		});
		GHADeleteButton deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		});

		VLayout sideButtons = GHAUiHelper.createBar(addButton, deleteButton);

		VLayout gridLayout = new VLayout(10);
		gridLayout.addMembers(grid);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(gridLayout, sideButtons);

		addMember(mainLayout);
	}

	private void addActivity() {
		ListGridRecord records[] = grid.getRecords();
		List<Activity> blackList = new ArrayList<Activity>();
		for (int i = 0; i < records.length; i++) {
			MaintenanceActivitySubprotocolRecord record = (MaintenanceActivitySubprotocolRecord) records[i];
			blackList.add(record.toEntity().getActivity());
		}
		blackList.add(maintenanceActivity.getActivity());

		activitySearchForm.filterBy(blackList);
		activitySearchForm.open();
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		grid.destroy();
		destroy();
	}

	private void loadData() {
		SubprotocolAndChecklistModel.findByParentActivity(
				maintenanceActivity.getActivity(),
				new GHAAsyncCallback<List<SubProtocolAndChecklist>>() {
					@Override
					public void onSuccess(List<SubProtocolAndChecklist> result) {
						MaintenanceActivitySubprotocolRecord array[] = MaintenanceSubprotocolUtil
								.toGridRecordsArray(result);
						grid.setData(array);
					}
				});
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
		loadData();
	}

	private void save(MaintenanceActivity activity) {
		int ordinal = grid.getRecords().length + 1;

		SubProtocolAndChecklist entity = new SubProtocolAndChecklist();
		entity.setParentActivity(maintenanceActivity.getActivity());
		entity.setActivity(activity.getActivity());
		entity.setOrdinal(ordinal);

		SubprotocolAndChecklistModel.save(entity,
				new GHAAsyncCallback<SubProtocolAndChecklist>() {
					@Override
					public void onSuccess(final SubProtocolAndChecklist result) {
						loadData();
					}
				});
	}

	private void delete() {
		final List<SubProtocolAndChecklist> selectedEntities = grid
				.getSelectedEntities();
		if (selectedEntities == null) {
			GHAAlertManager.alert("record-not-selected");
		} else {
			String message = selectedEntities.size() == 1 ? GHAStrings
					.get("activity-delete-confirm") : GHAStrings
					.get("activities-delete-confirm");

			GHAAlertManager.confirm(GHAStrings.get("subprotocol"), message,
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

	private void deleteSelectedEntities(
			List<SubProtocolAndChecklist> selectedEntities) {
		SubprotocolAndChecklistModel.delete(selectedEntities,
				new GHAAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						loadData();
						GHAAlertManager.alert("delete-activities-success");
					}
				});
	}
}