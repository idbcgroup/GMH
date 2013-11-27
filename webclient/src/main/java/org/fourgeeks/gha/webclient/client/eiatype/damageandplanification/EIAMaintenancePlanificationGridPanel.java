package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EIAMaintenancePlanificationUpdateForm;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EiaMaintenancePlanificationModel;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EiaMaintenancePlanificationSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAMaintenancePlanificationGridPanel extends GHAVerticalLayout
		implements EIATypeSelectionListener, HideableListener, ClosableListener {

	private EIAMaintenancePlanificationGrid grid;
	private EiaType eiaType;
	private EIAMaintenancePlanificationUpdateForm updateForm;

	{
		grid = new EIAMaintenancePlanificationGrid();
		updateForm = new EIAMaintenancePlanificationUpdateForm();
		updateForm
				.addEiaMaintenancePlanificationSelectionListener(new EiaMaintenancePlanificationSelectionListener() {
					@Override
					public void select(EiaMaintenancePlanification entity) {
						loadData();
					}
				});
	}

	public EIAMaintenancePlanificationGridPanel() {
		super();
		setWidth("100%");

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						view();
					}
				}), new GHAEditButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				update();
			}
		}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		String title = "Reporte de Mantenimiento Realizado, Cancelado o Diferido - Cronologia";
		// TODO agregar el titulo al ustring.csv (pensar en una buena clave)
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
		updateForm.close();
	}

	private void loadData() {
		EiaMaintenancePlanificationModel.find(eiaType,
				new GHAAsyncCallback<List<EiaMaintenancePlanification>>() {
					@Override
					public void onSuccess(
							List<EiaMaintenancePlanification> result) {
						List<EIAMaintenancePlanificationRecord> gridRecords = EIADamageAndPlanificationUtil
								.toMaintenanceGridRecords(result);
						EIAMaintenancePlanificationRecord[] array = gridRecords
								.toArray(new EIAMaintenancePlanificationRecord[] {});
						grid.setData(array);
					}
				});
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		updateForm.select(eiaType);
		loadData();
	}

	private void update() {
		GHAGridRecord<EiaMaintenancePlanification> selectedRecord = grid
				.getSelectedRecord();

		if (selectedRecord == null)
			GHANotification.alert("record-not-selected");
		else {
			EiaMaintenancePlanification entity = selectedRecord.toEntity();
			updateForm.select(entity);
			updateForm.activate();
			updateForm.open();
		}
	}

	private void view() {
		GHAGridRecord<EiaMaintenancePlanification> selectedRecord = grid
				.getSelectedRecord();

		if (selectedRecord == null)
			GHANotification.alert("record-not-selected");
		else {
			EiaMaintenancePlanification entity = selectedRecord.toEntity();
			updateForm.select(entity);
			updateForm.deactivate();
			updateForm.open();
		}

	}
}
