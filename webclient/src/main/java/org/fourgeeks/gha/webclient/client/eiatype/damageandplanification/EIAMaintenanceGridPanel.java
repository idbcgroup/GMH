package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
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
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiamaintenance.EIAMaintenanceUpdateForm;
import org.fourgeeks.gha.webclient.client.eiamaintenance.EiaMaintenanceDisplayForm;
import org.fourgeeks.gha.webclient.client.eiamaintenance.EiaMaintenanceModel;
import org.fourgeeks.gha.webclient.client.eiamaintenance.EiaMaintenanceSelectionListener;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.MaintenancePlanificationSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author naramirez
 * 
 */
public class EIAMaintenanceGridPanel extends VLayout implements
		EIATypeSelectionListener, EiaDamageReportSelectionListener,
		MaintenancePlanificationSelectionListener, HideableListener,
		ClosableListener {

	private EIAMaintenanceGrid grid;
	private EiaType eiaType;
	private EIAMaintenanceUpdateForm updateForm;
	private EiaMaintenanceDisplayForm displayForm;

	{
		grid = new EIAMaintenanceGrid();

		displayForm = new EiaMaintenanceDisplayForm();
		updateForm = new EIAMaintenanceUpdateForm();
		updateForm
				.addEiaMaintenanceSelectionListener(new EiaMaintenanceSelectionListener() {
					@Override
					public void select(EiaMaintenance entity) {
						loadData();
					}
				});
	}

	/**
	 * Constructor
	 */
	public EIAMaintenanceGridPanel() {
		super();
		setWidth100();

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display();
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
		EiaMaintenanceModel.find(eiaType,
				new GHAAsyncCallback<List<EiaMaintenance>>() {
					@Override
					public void onSuccess(List<EiaMaintenance> result) {
						List<EIAMaintenanceRecord> gridRecords = EIADamageAndPlanificationUtil
								.toMaintenanceGridRecords(result);
						EIAMaintenanceRecord[] array = gridRecords
								.toArray(new EIAMaintenanceRecord[] {});
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
		GHAGridRecord<EiaMaintenance> selectedRecord = grid.getSelectedRecord();

		if (selectedRecord == null)
			GHAErrorMessageProcessor.alert("record-not-selected");
		else {
			EiaMaintenance entity = selectedRecord.toEntity();
			updateForm.select(entity);
			updateForm.open();
		}
	}

	private void display() {
		GHAGridRecord<EiaMaintenance> selectedRecord = grid.getSelectedRecord();

		if (selectedRecord == null)
			GHAErrorMessageProcessor.alert("record-not-selected");
		else {
			EiaMaintenance entity = selectedRecord.toEntity();
			displayForm.select(entity);
			displayForm.open();
		}

	}

	@Override
	public void select(EiaDamageReport eiaDamageReport) {
		if (eiaType != null)
			loadData();
	}

	@Override
	public void select(EiaMaintenancePlanification preventivePlanif) {
		if (eiaType != null)
			loadData();
	}
}
