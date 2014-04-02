package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeListSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityEiaTypeGridPanel extends GHAFormLayout
		implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener {

	private EIATypeSearchForm eiaTypeSearchForm;
	private MaintenanceActivityServiceAndResourceGrid grid;
	private MaintenanceActivity maintenanceActivity;

	{
		grid = new MaintenanceActivityServiceAndResourceGrid();
		grid.getField("quantity").addCellSavedHandler(new CellSavedHandler() {
			@Override
			public void onCellSaved(CellSavedEvent event) {
				final MaintenanceActivityRequiredResourcesRecord rec = (MaintenanceActivityRequiredResourcesRecord) grid
						.getRecord(event.getRowNum());
				final RequiredResources requiredR = rec
						.toRequiredResourcesEntity();
				requiredR.setQuantity((Integer) grid.getEditedCell(
						event.getRowNum(), event.getColNum()));
				RequiredResourcesModel.update(requiredR,
						new GHAAsyncCallback<RequiredResources>() {
							@Override
							public void onSuccess(RequiredResources result) {
								loadData();
							}
						});
			}
		});

		eiaTypeSearchForm = new EIATypeSearchForm(GHAStrings.get("eiatype"));
		eiaTypeSearchForm
				.addEiaTypeSelectionListener(new EIATypeSelectionListener() {
					@Override
					public void select(EiaType eiaType) {
						save(eiaType);
						eiaTypeSearchForm.clean();
					}
				});
		eiaTypeSearchForm
				.addEiaTypeListSelectionListener(new EiaTypeListSelectionListener() {
					@Override
					public void select(List<EiaType> eiaTypes) {
						save(eiaTypes);
						eiaTypeSearchForm.clean();
					}
				});
	}

	/**
	 * 
	 */
	public MaintenanceActivityEiaTypeGridPanel() {
		super();
		setWidth100();

		addMember(new GHALabel(GHAStrings.get("required-eiatype")));

		final GHANewButton addButton = new GHANewButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addResource();
			}
		});
		final GHADeleteButton deleteButton = new GHADeleteButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						delete();
					}
				});

		final VLayout sideButtons = GHAUiHelper.createBar(addButton,
				deleteButton);

		final VLayout gridLayout = new VLayout(10);
		gridLayout.addMembers(grid);

		final HLayout mainLayout = new HLayout();
		mainLayout.addMembers(gridLayout, sideButtons);

		addMember(mainLayout);
	}

	private void addResource() {
		final ListGridRecord records[] = grid.getRecords();
		final List<EiaType> blackList = new ArrayList<EiaType>();
		for (int i = 0; i < records.length; i++) {
			final MaintenanceActivityRequiredResourcesRecord record = (MaintenanceActivityRequiredResourcesRecord) records[i];
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

	private void loadData() {
		RequiredResourcesModel.findEiaTypeByActivity(
				maintenanceActivity.getActivity(),
				new GHAAsyncCallback<List<RequiredResources>>() {
					@Override
					public void onSuccess(List<RequiredResources> result) {
						final MaintenanceActivityRequiredResourcesRecord array[] = MaintenanceActivityRequiredResourcesUtil
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

	private void save(EiaType eiatype) {
		final RequiredResources entity = new RequiredResources();
		entity.setActivity(maintenanceActivity.getActivity());
		entity.setResource(eiatype);

		RequiredResourcesModel.save(entity,
				new GHAAsyncCallback<RequiredResources>() {
					@Override
					public void onSuccess(final RequiredResources result) {
						loadData();
					}
				});
	}

	private void save(List<EiaType> eiaTypes) {
		final List<RequiredResources> entities = new ArrayList<RequiredResources>();
		for (EiaType eiaType : eiaTypes) {
			RequiredResources entity = new RequiredResources();
			entity.setActivity(maintenanceActivity.getActivity());
			entity.setResource(eiaType);

			entities.add(entity);
		}

		RequiredResourcesModel.save(entities,
				new GHAAsyncCallback<List<RequiredResources>>() {
					@Override
					public void onSuccess(final List<RequiredResources> result) {
						loadData();
					}
				});
	}

	private void delete() {
		final List<RequiredResources> selectedEntities = new ArrayList<RequiredResources>();
		final ListGridRecord[] selectedRecords = grid.getSelectedRecords();
		for (final ListGridRecord lgr : selectedRecords) {
			final RequiredResources rr = ((MaintenanceActivityRequiredResourcesRecord) lgr)
					.toRequiredResourcesEntity();
			selectedEntities.add(rr);
		}
		if (selectedEntities.size() == 0) {
			GHAErrorMessageProcessor.alert("record-not-selected");
		} else {
			if(selectedEntities.size() == 1)
				GHAErrorMessageProcessor.confirm("resource-delete-confirm",
						new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value)
									deleteSelectedEntities(selectedEntities);
								grid.focus();
							}
						});
			else
				GHAErrorMessageProcessor.confirm("resources-delete-confirm",
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
				GHAErrorMessageProcessor.alert("delete-resources-success");
			}
		});
	}
}