package org.fourgeeks.gha.webclient.client.eiatype.eiapreventivemaintenanceplanitification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EIADamageReportSearchForm;
import org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification.EIAPreventiveMaintenancePlanificationAddForm;
import org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification.EiaPreventiveMaintenancePlanificationModel;
import org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification.PreventivePlanificationSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAPreventiveMaintenancePlanificationGridPanel extends
		GHAVerticalLayout implements EIATypeSelectionListener,
		HideableListener, ClosableListener {

	private EIAPreventiveMaintenancePlanificationGrid grid;
	private EIADamageReportSearchForm searchForm;
	private EiaType eiaType;
	private EIAPreventiveMaintenancePlanificationAddForm addForm;

	{
		grid = new EIAPreventiveMaintenancePlanificationGrid();
		searchForm = new EIADamageReportSearchForm(GHAStrings.get("search-eia"));
		addForm = new EIAPreventiveMaintenancePlanificationAddForm();
		addForm.addPreventivePlanificationSelectionListener(new PreventivePlanificationSelectionListener() {
			@Override
			public void select(
					EiaPreventiveMaintenancePlanification preventivePlanif) {
				loadData();

			}
		});

		searchForm.addEiaSelectionListener(new EIASelectionListener() {
			@Override
			public void select(Eia eia) {
				searchForm.clean();
				addForm.select(eia);
				addForm.open();
			}
		});

	}

	public EIAPreventiveMaintenancePlanificationGridPanel() {
		super();
		setWidth("100%");

		VLayout sideButtons = GHAUiHelper.createBar(new GHANewButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		String title = GHAStrings
				.get("eia-preventive-maintenance-planification");
		addMembers(new GHALabel(title), mainPanel);
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
		hide();
		searchForm.close();
		addForm.close();
	}

	private void loadData() {
		EiaPreventiveMaintenancePlanificationModel
				.find(eiaType,
						new GHAAsyncCallback<List<EiaPreventiveMaintenancePlanification>>() {
							@Override
							public void onSuccess(
									List<EiaPreventiveMaintenancePlanification> result) {
								List<EIAPreventiveMaintenancePlanificationRecord> gridRecords = EIAPreventivePlanifUtil
										.toGridRecords(result);
								EIAPreventiveMaintenancePlanificationRecord[] array = gridRecords
										.toArray(new EIAPreventiveMaintenancePlanificationRecord[] {});
								grid.setData(array);
							}
						});
	}

	private void search() {
		EiaTypeMaintenancePlanModel.findByEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {
					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						if (!result.isEmpty())
							searchForm.open();
						else
							GHANotification
									.alert("no-eiatype-maintenance-plans");
					}
				});

	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		searchForm.select(eiaType);
		addForm.select(eiaType);
		loadData();
	}
}
