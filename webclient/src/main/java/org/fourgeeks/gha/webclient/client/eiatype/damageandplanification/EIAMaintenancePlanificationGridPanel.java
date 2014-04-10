package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EIAMaintenancePlanificationAddForm;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EiaMaintenancePlanificationModel;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.MaintenancePlanificationSelectionListener;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.MaintenancePlanificationSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author naramirez
 * 
 */
public class EIAMaintenancePlanificationGridPanel extends VLayout implements
		EIATypeSelectionListener, MaintenancePlanificationSelectionProducer,
		HideableListener, ClosableListener {

	private EIAMaintenancePlanificationGrid grid;
	private EIADamageAndPlanificationSearchForm searchForm;
	private EiaType eiaType;
	private EIAMaintenancePlanificationAddForm addForm;

	{
		grid = new EIAMaintenancePlanificationGrid();
		searchForm = new EIADamageAndPlanificationSearchForm(
				GHAStrings.get("search-eia"));
		addForm = new EIAMaintenancePlanificationAddForm();
		addForm.addMaintenancePlanificationSelectionListener(new MaintenancePlanificationSelectionListener() {
			@Override
			public void select(EiaMaintenancePlanification preventivePlanif) {
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

	/**
	 * Constructor
	 */
	public EIAMaintenancePlanificationGridPanel() {
		super();
		setWidth100();

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
		addMembers(new GHATopTitleLabel(title), mainPanel);
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
		EiaMaintenancePlanificationModel.find(eiaType,
				new GHAAsyncCallback<List<EiaMaintenancePlanification>>() {
					@Override
					public void onSuccess(
							List<EiaMaintenancePlanification> result) {
						List<EIAMaintenancePlanificationRecord> gridRecords = EIADamageAndPlanificationUtil
								.toMaintenancePlanificationGridRecords(result);
						EIAMaintenancePlanificationRecord[] array = gridRecords
								.toArray(new EIAMaintenancePlanificationRecord[] {});
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
							GHAErrorMessageProcessor
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

	@Override
	public void addMaintenancePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener preventivePlanifSelectionListener) {
		addForm.addMaintenancePlanificationSelectionListener(preventivePlanifSelectionListener);

	}

	@Override
	public void removeMaintenancePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener preventivePlanifSelectionListener) {
		addForm.removeMaintenancePlanificationSelectionListener(preventivePlanifSelectionListener);

	}

	@Override
	public void notifyMaintenancePlanification(
			EiaMaintenancePlanification preventivePlanif) {
	}
}
