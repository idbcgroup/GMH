package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.EiaTypeMaintenancePlanificationListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.ResultSetEiaListPlanification;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author eguerere
 */
public class EiasPlanificationAddForm extends
		GHAAddForm<List<EiaMaintenancePlanification>> implements
		EiaTypeMaintenancePlanificationListener,
		MaintenancePlanSelectionProducer {

	private ResultSetEiaListPlanification resultset;
	private List<EiaPlanificationEntity> eiaList;

	{
		form = new EiasPlanificationMaintenanceForm();
	}

	public EiasPlanificationAddForm() {
		super(GHAStrings.get("new-eia-maintenance-planification"));
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACloseButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
	}

	@Override
	public void hide() {
		super.hide();
		form.hide();
	}

	@Override
	public void open() {
		super.open();
		form.show();
	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<List<EiaMaintenancePlanification>>() {
			@Override
			public void onSuccess(List<EiaMaintenancePlanification> result) {
				form.clear();
				GHAErrorMessageProcessor
						.alert("eiaMaintenancePlanification-save-success");
				hide();
				resultset.clean();
			}
		});
	}

	/**
	 * @return the eiaList
	 */
	public List<EiaPlanificationEntity> getEiaList() {
		return eiaList;
	}

	/**
	 * @param eiaList
	 *            the eiaList to set
	 */
	public void setEiaList(List<EiaPlanificationEntity> eiaList) {
		this.eiaList = eiaList;
	}

	@Override
	public void select(EiaTypeMaintenancePlan plan) {
		((EiaTypeMaintenancePlanificationListener) form).select(plan);

	}

	@Override
	public void select(List<EiaPlanificationEntity> listEiaPlan) {
		((EiaTypeMaintenancePlanificationListener) form).select(listEiaPlan);
	}

	public void setResultset(ResultSetEiaListPlanification resultset) {
		this.resultset = resultset;
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		((MaintenancePlanSelectionProducer) form)
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		((MaintenancePlanSelectionProducer) form)
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
	}
}