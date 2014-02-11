package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.domain.glm.Material;
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
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialSearchForm;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityMaterialGridPanel extends GHAVerticalLayout
		implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener {

	private MaterialSearchForm materialSearchForm;
	private MaintenanceActivityServiceAndResourceGrid grid;
	private ServiceAndResource serviceAndResource;
	private MaintenanceActivity maintenanceActivity;

	{
		grid = new MaintenanceActivityServiceAndResourceGrid();

		materialSearchForm = new MaterialSearchForm(GHAStrings.get("material"));
		materialSearchForm
				.addMaterialSelectionListener(new MaterialSelectionListener() {
					@Override
					public void select(Material material) {
						// TODO Auto-generated method stub
					}
				});
	}

	/**
* 
*/
	public MaintenanceActivityMaterialGridPanel() {
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
		// ListGridRecord records[] = grid.getRecords();
		// List<EiaType> blackList = new ArrayList<EiaType>();
		// for (int i = 0; i < records.length; i++) {
		// MaintenanceSubprotocolRecord record = (MaintenanceSubprotocolRecord)
		// records[i];
		// blackList.add(record.toEntity().getActivity());
		// }
		// blackList.add(maintenanceActivity.getActivity());

		// eiaTypeSearchForm.filterBy(blackList);
		materialSearchForm.open();
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
	// SubprotocolAndChecklistModel.findByParentActivity(
	// maintenanceActivity.getActivity(),
	// new GHAAsyncCallback<List<SubProtocolAndChecklist>>() {
	// @Override
	// public void onSuccess(List<SubProtocolAndChecklist> result) {
	// MaintenanceSubprotocolRecord array[] = MaintenanceSubprotocolUtil
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

	private void save(MaintenanceActivity activity) {

		RequiredResources entity = new RequiredResources();
		entity.setActivity(activity.getActivity());
		entity.setResource(serviceAndResource);

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