package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols.MaintenanceProtocolsRecord;

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
		GHAVerticalLayout implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionProducer,
		MaintenanceActivitySelectionListener {

	private MaintenanceActivitySearchForm activitySearchForm;
	private MaintenanceSubprotocolActivitiesGrid grid;
	private List<MaintenanceActivitySelectionListener> listeners;
	private MaintenanceActivity maintenanceActivity;

	{
		grid = new MaintenanceSubprotocolActivitiesGrid();
		activitySearchForm = new MaintenanceActivitySearchForm(
				GHAStrings.get("maintenance-activity"));

		activitySearchForm
				.addMaintenanceActivitySelectionListener(new MaintenanceActivitySelectionListener() {
					@Override
					public void select(MaintenanceActivity activity) {
						// TODO
					}
				});
	}

	/**
	 * 
	 */
	public MaintenanceSubprotocolActivitiesGridPanel() {
		super();
		setWidth100();

		addMember(new GHALabel("Actividades de subprotocolo"));

		GHANewButton addButton = new GHANewButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addActivity();
			}
		});

		VLayout sideButtons = GHAUiHelper.createBar(addButton);

		VLayout gridLayout = new VLayout(10);
		gridLayout.addMembers(grid);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(gridLayout, sideButtons);

		addMember(mainLayout);
	}

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

	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener) {
		listeners.add(selectionListener);
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
		// MaintenanceSubprotocolModel.find(maintenanceActivity,
		// new GHAAsyncCallback<List<MaintenanceActivity>>() {
		// @Override
		// public void onSuccess(List<MaintenanceActivity> result) {
		// MaintenanceActivityRecord array[] = MaintenanceProtocolsUtil
		// .toGridRecordsArray(result);
		// grid.setData(array);
		// }
		// });
	}

	@Override
	public void notifyMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) {
		for (MaintenanceActivitySelectionListener listener : listeners)
			listener.select(maintenanceActivity);
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener) {
		listeners.remove(selectionListener);
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
		loadData();
	}
}