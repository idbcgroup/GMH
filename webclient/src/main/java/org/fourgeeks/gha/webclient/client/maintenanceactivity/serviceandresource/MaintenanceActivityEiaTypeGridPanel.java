package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityEiaTypeGridPanel extends GHAVerticalLayout
		implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener {

	private EIATypeSearchForm eiaTypeSearchForm;
	private MaintenanceActivityServiceAndResourceGrid grid;
	private ServiceAndResource serviceAndResource;
	private MaintenanceActivity maintenanceActivity;

	{
		grid = new MaintenanceActivityServiceAndResourceGrid();

		eiaTypeSearchForm = new EIATypeSearchForm(GHAStrings.get("eiatype"));
		eiaTypeSearchForm
				.addEiaTypeSelectionListener(new EIATypeSelectionListener() {
					@Override
					public void select(EiaType eiaType) {
						save(eiaType);
					}
				});
	}

	/**
 * 
 */
	public MaintenanceActivityEiaTypeGridPanel() {
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
				// delete();
				Window.alert("Delete");
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
		List<EiaType> blackList = new ArrayList<EiaType>();
		for (int i = 0; i < records.length; i++) {
			MaintenanceActivityRequiredResourcesRecord record = (MaintenanceActivityRequiredResourcesRecord) records[i];
			blackList.add((EiaType) record.toEntity());
		}

		eiaTypeSearchForm.filterBy(blackList);
		eiaTypeSearchForm.open();
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

	// private void loadData() {
	// RequiredResourcesModel.findByActivity(
	// maintenanceActivity.getActivity(),
	// new GHAAsyncCallback<List<RequiredResources>>() {
	// @Override
	// public void onSuccess(List<RequiredResources> result) {
	// MaintenanceActivityRequiredResourcesRecord array[] =
	// MaintenanceActivityRequiredResourcesRecord
	// .toGridRecordsArray(result);
	// grid.setData(array);
	// }
	// });
	// }

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
		// loadData();
	}

	private void save(EiaType eiatype) {
		RequiredResources entity = new RequiredResources();
		entity.setActivity(maintenanceActivity.getActivity());
		entity.setResource(serviceAndResource);
		entity.setQuantity(5);

		RequiredResourcesModel.save(entity,
				new GHAAsyncCallback<RequiredResources>() {
					@Override
					public void onSuccess(final RequiredResources result) {
						// loadData();
						Window.alert("loadData");
					}
				});
	}

	// private void delete() {
	// final List<SubProtocolAndChecklist> selectedEntities = grid
	// .getSelectedEntities();
	// if (selectedEntities == null) {
	// GHAAlertManager.alert("record-not-selected");
	// } else {
	// String message = selectedEntities.size() == 1 ? GHAStrings
	// .get("activity-delete-confirm") : GHAStrings
	// .get("activities-delete-confirm");
	//
	// GHAAlertManager.confirm(GHAStrings.get("subprotocol"), message,
	// new BooleanCallback() {
	// @Override
	// public void execute(Boolean value) {
	// if (value)
	// deleteSelectedEntities(selectedEntities);
	// grid.focus();
	// }
	// });
	// }
	// }

	// private void deleteSelectedEntities(
	// List<SubProtocolAndChecklist> selectedEntities) {
	// SubprotocolAndChecklistModel.delete(selectedEntities,
	// new GHAAsyncCallback<Void>() {
	// @Override
	// public void onSuccess(Void result) {
	// loadData();
	// GHAAlertManager.alert("delete-activities-success");
	// }
	// });
	// }
}