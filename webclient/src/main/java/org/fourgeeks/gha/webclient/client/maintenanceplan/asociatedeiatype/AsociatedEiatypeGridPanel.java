package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.buttons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.SearchFormEiaListPlanification;

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
public class AsociatedEiatypeGridPanel extends GHAFormLayout implements
		ClosableListener, HideableListener, MaintenancePlanSelectionListener,
		MaintenancePlanSelectionProducer {

	private EIATypeSearchForm searchForm;
	private SearchFormEiaListPlanification searchFormEiaPlanification;
	private EiaTypeMaintenancePlanGrid grid;
	private MaintenancePlan maintenancePlan;
	{
		grid = new EiaTypeMaintenancePlanGrid();
		grid.setEiaTypeFields();

		searchForm = new EIATypeSearchForm(
				GHAStrings.get("search-component-eiatype"));

		searchFormEiaPlanification = new SearchFormEiaListPlanification(
				"Busqueda de Equipos Planificación");

		searchForm.addEiaTypeSelectionListener(new EIATypeSelectionListener() {
			@Override
			public void select(EiaType eiaType) {
				// clean the search form
				AsociatedEiatypeGridPanel.this.searchForm.clean();

				final EiaTypeMaintenancePlan eiaTypeMP = new EiaTypeMaintenancePlan();
				eiaTypeMP.setEiaType(eiaType);
				eiaTypeMP.setMaintenancePlan(maintenancePlan);
				EiaTypeMaintenancePlanModel.save(eiaTypeMP,
						new GHAAsyncCallback<EiaTypeMaintenancePlan>() {

							@Override
							public void onSuccess(EiaTypeMaintenancePlan result) {
								loadData();
							}
						});
			}
		});
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public AsociatedEiatypeGridPanel() {
		super();
		GHATopTitleLabel title = new GHATopTitleLabel(
				GHAStrings.get("eia-type-on-maintenance-plan"));
		addMember(title);
		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}), new GHANewButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				EiaTypeMaintenancePlan eiaTypeMaintenancePlan = grid
						.getSelectedEntity();
				if (eiaTypeMaintenancePlan == null) {
					GHAErrorMessageProcessor.alert("record-not-selected");
					return;
				} else {
					searchEia(eiaTypeMaintenancePlan);
				}
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
		EiaTypeMaintenancePlanModel.findByMaintenancePlan(maintenancePlan,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {

					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						ListGridRecord array[] = EiaTypeMaintenancePlanUtil
								.toEiaTypeGridRecords(result).toArray(
										new EiaTypeMaintenancePlanRecord[] {});
						grid.setData(array);
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
		List<EiaType> blackList = new ArrayList<EiaType>();

		for (int i = 0; i < records.length; i++) {
			blackList.add(((EiaTypeMaintenancePlanRecord) records[i])
					.toEntity().getEiaType());
		}

		searchForm.filterBy(blackList);
		searchForm.open();
	}

	private void searchEia(EiaTypeMaintenancePlan eiaTypeMaintenancePlan) {
		searchFormEiaPlanification
				.setEiaTypeMaintenancePlan(eiaTypeMaintenancePlan);
		searchFormEiaPlanification.open();
	}

	private void deleteSelected() {
		final List<EiaTypeMaintenancePlan> selectedEntities = grid
				.getSelectedEntities();

		if (selectedEntities == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		GHAErrorMessageProcessor.confirm(
				"maintenance-eiatype-associative-delete-confirm",
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {

							if (selectedEntities.size() == 1) {
								EiaTypeMaintenancePlanModel.delete(
										selectedEntities.get(0).getId(),
										new GHAAsyncCallback<Void>() {
											@Override
											public void onSuccess(Void result) {
												grid.removeSelectedData();
												GHAErrorMessageProcessor
														.alert("eiatype-delete-success");
											}

										});
							} else {
								if (selectedEntities.size() > 1) {
									EiaTypeMaintenancePlanModel.delete(
											selectedEntities,
											new GHAAsyncCallback<Void>() {
												@Override
												public void onSuccess(
														Void result) {
													grid.removeSelectedData();
													GHAErrorMessageProcessor
															.alert("eiatypes-delete-success");
												}

											});
								}

							}

						}

					}
				});

	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		searchFormEiaPlanification
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		searchFormEiaPlanification
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
	}
}
