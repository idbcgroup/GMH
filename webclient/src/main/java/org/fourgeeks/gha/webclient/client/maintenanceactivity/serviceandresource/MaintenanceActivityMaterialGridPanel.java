package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialSearchForm;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;

import com.smartgwt.client.util.BooleanCallback;
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
public class MaintenanceActivityMaterialGridPanel extends GHAVerticalLayout
		implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener {

	private MaterialSearchForm materialSearchForm;
	private MaintenanceActivityServiceAndResourceGrid grid;
	private MaintenanceActivity maintenanceActivity;

	{
		grid = new MaintenanceActivityServiceAndResourceGrid();

		materialSearchForm = new MaterialSearchForm(GHAStrings.get("materials"));
		materialSearchForm
				.addMaterialSelectionListener(new MaterialSelectionListener() {
					@Override
					public void select(Material material) {
						save(material);
						materialSearchForm.clean();
					}
				});
	}

	/**
 * 
 */
	public MaintenanceActivityMaterialGridPanel() {
		super();
		setWidth100();

		addMember(new GHALabel(GHAStrings.get("required-materials")));

		GHANewButton addButton = new GHANewButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addResource();
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

	private void addResource() {
		ListGridRecord records[] = grid.getRecords();
		List<Material> blackList = new ArrayList<Material>();
		for (int i = 0; i < records.length; i++) {
			MaintenanceActivityRequiredResourcesRecord record = (MaintenanceActivityRequiredResourcesRecord) records[i];
			blackList.add((Material) record.toEntity());
		}

		materialSearchForm.filterBy(blackList);
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

	private void loadData() {
		RequiredResourcesModel.findMaterialByActivity(
				maintenanceActivity.getActivity(),
				new GHAAsyncCallback<List<RequiredResources>>() {
					@Override
					public void onSuccess(List<RequiredResources> result) {
						MaintenanceActivityRequiredResourcesRecord array[] = MaintenanceActivityRequiredResourcesUtil
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

	private void save(Material material) {
		RequiredResources entity = new RequiredResources();
		entity.setActivity(maintenanceActivity.getActivity());
		entity.setResource(material);

		RequiredResourcesModel.save(entity,
				new GHAAsyncCallback<RequiredResources>() {
					@Override
					public void onSuccess(final RequiredResources result) {
						loadData();
					}
				});
	}

	private void delete() {
		final List<RequiredResources> selectedEntities = new ArrayList<RequiredResources>();
		ListGridRecord[] selectedRecords = grid.getSelectedRecords();
		for (ListGridRecord lgr : selectedRecords) {
			RequiredResources rr = ((MaintenanceActivityRequiredResourcesRecord) lgr)
					.toRequiredResourcesEntity();
			selectedEntities.add(rr);
		}
		if (selectedEntities.size() == 0) {
			GHAAlertManager.alert("record-not-selected");
		} else {
			String message = selectedEntities.size() == 1 ? GHAStrings
					.get("resource-delete-confirm") : GHAStrings
					.get("resources-delete-confirm");

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

	private void deleteSelectedEntities(List<RequiredResources> selectedEntities) {
		RequiredResourcesModel.delete(selectedEntities,
				new GHAAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						loadData();
						GHAAlertManager.alert("delete-resources-success");
					}
				});
	}
}