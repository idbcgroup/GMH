package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.EiaTypeMaintenancePlanProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.EiaTypeMaintenancePlanificationListener;

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
		EiaTypeMaintenancePlanificationListener, EiaTypeMaintenancePlanProducer {

	private EiaTypeMaintenancePlan eiaTypeMplan;
	private List<EiaPlanificationEntity> eiaList;

	{
		form = new EiasPlanificationMaintenanceForm(this);
	}

	/**	 */
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

	// @Override
	// public void addMaintenancePlanificationSelectionListener(
	// MaintenancePlanificationSelectionListener
	// preventivePlanifSelectionListener) {
	// ((MaintenancePlanificationSelectionProducer) form)
	// .addMaintenancePlanificationSelectionListener(preventivePlanifSelectionListener);
	//
	// }

	@Override
	public void hide() {
		super.hide();
		form.hide();
	}

	// @Override
	// public void notifyMaintenancePlanification(
	// EiaMaintenancePlanification preventivePlanif) {
	// return;
	// }

	@Override
	public void open() {
		super.open();
		form.show();
	}

	// @Override
	// public void removeMaintenancePlanificationSelectionListener(
	// MaintenancePlanificationSelectionListener
	// eiaDamageReportSelectionListener) {
	// ((MaintenancePlanificationSelectionProducer) form)
	// .removeMaintenancePlanificationSelectionListener(eiaDamageReportSelectionListener);
	//
	// }

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<List<EiaMaintenancePlanification>>() {
			@Override
			public void onSuccess(List<EiaMaintenancePlanification> result) {
				form.clear();
				GHAErrorMessageProcessor
						.alert("eiaMaintenancePlanification-save-success");
				hide();
			}
		});
	}

	// @Override
	// public void select(EiaTypeMaintenancePlan plan) {
	// ((EiaTypeMaintenancePlanificationListener) form).select(plan);
	// }
	//
	// @Override
	// public void select(EiaType eiaType) {
	// ((EIATypeSelectionListener) form).select(eiaType);
	// }

	/**
	 * @return the eiaTypeMplan
	 */
	public EiaTypeMaintenancePlan getEiaTypeMplan() {
		return eiaTypeMplan;
	}

	/**
	 * @param eiaTypeMplan
	 *            the eiaTypeMplan to set
	 */
	public void setEiaTypeMplan(EiaTypeMaintenancePlan eiaTypeMplan) {
		this.eiaTypeMplan = eiaTypeMplan;

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
	public void addEiaTypeMaintenancePlanSelectionListener(
			EiaTypeMaintenancePlanificationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEiaTypeMaintenancePlanSelectionListener(
			EiaTypeMaintenancePlanificationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyEiaTypeMaintenancePlan(EiaTypeMaintenancePlan plan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyListEiaPlanificationEntity(
			List<EiaPlanificationEntity> eiaPlan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(EiaTypeMaintenancePlan plan) {
		// TODO Auto-generated method stub
		((EiaTypeMaintenancePlanificationListener) form).select(plan);

	}

	@Override
	public void select(List<EiaPlanificationEntity> listEiaPlan) {
		// TODO Auto-generated method stub
		((EiaTypeMaintenancePlanificationListener) form).select(listEiaPlan);
	}

}