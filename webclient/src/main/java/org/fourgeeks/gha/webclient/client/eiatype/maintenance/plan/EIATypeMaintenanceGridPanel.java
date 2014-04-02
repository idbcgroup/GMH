package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanAddForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanGrid;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanRecord;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanUtil;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaintenanceGridPanel extends GHAFormLayout implements
		ClosableListener, HideableListener, EIATypeSelectionListener {

	private EiaTypeMaintenancePlanGrid grid;
	private EiaType eiaType;
	private MaintenancePlanSearchForm searchForm;
	private MaintenancePlanAddForm addForm;

	private final MaintenancePlanSelectionListener maintenancePlanSelectionListener = new MaintenancePlanSelectionListener() {

		@Override
		public void select(MaintenancePlan maintenancePlan) {
			EIATypeMaintenanceGridPanel.this.searchForm.clean();

			EiaTypeMaintenancePlan entity = new EiaTypeMaintenancePlan();

			entity.setEiaType(EIATypeMaintenanceGridPanel.this.eiaType);
			entity.setMaintenancePlan(maintenancePlan);
			EiaTypeMaintenancePlanModel.save(entity,
					new GHAAsyncCallback<EiaTypeMaintenancePlan>() {

						@Override
						public void onSuccess(EiaTypeMaintenancePlan result) {
							loadData();
						}
					});
		}
	};

	{
		grid = new EiaTypeMaintenancePlanGrid();
		grid.setMaintenancePlanFields();
		searchForm = new MaintenancePlanSearchForm(
				GHAStrings.get("maintenance-plan"));
		searchForm
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
		addForm = new MaintenancePlanAddForm(
				GHAStrings.get("new-maintenance-plan"));
		addForm.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	/**
	 * 
	 */
	public EIATypeMaintenanceGridPanel() {
		super();
		GHALabel title = new GHALabel(GHAStrings.get("maintenance-plan"));
		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						search();

					}
				}),/*
					 * new GHANewButton(new ClickHandler() {
					 * 
					 * @Override public void onClick(ClickEvent event) {
					 * addForm.open();
					 * 
					 * } }),
					 */new GHADeleteButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				delete();

			}
		}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(title, mainPanel);
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
		hide();
		searchForm.close();
		addForm.close();
	}

	private void delete() {
		final EiaTypeMaintenancePlan entity = grid.getSelectedEntity();
		if (entity == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}
		GHAErrorMessageProcessor.confirm("eiatype-maintenance-plan-delete-confirm",
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {
							EiaTypeMaintenancePlanModel.delete(entity.getId(),
									new GHAAsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											grid.removeSelectedData();
										}
									});
						}

					}
				});
	}

	@Override
	public void hide() {
		if (searchForm.isVisible())
			searchForm.hide();
		if (addForm.isVisible())
			addForm.hide();
	}

	private void loadData() {
		EiaTypeMaintenancePlanModel.findByEiaType(this.eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {

					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						ListGridRecord array[] = EiaTypeMaintenancePlanUtil
								.toMaintenancePlanGridRecords(result).toArray(
										new EiaTypeMaintenancePlanRecord[] {});
						grid.setData(array);
					}
				});
	}

	private void search() {
		ListGridRecord records[] = grid.getRecords();
		List<MaintenancePlan> blackList = null;
		if (records.length != 0) {
			blackList = new ArrayList<MaintenancePlan>();
			for (int i = 0; i < records.length; ++i) {
				blackList.add(((EiaTypeMaintenancePlanRecord) records[i])
						.toEntity().getMaintenancePlan());
			}
		}
		searchForm.filterBy(blackList);
		searchForm.open();
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
	}
}
