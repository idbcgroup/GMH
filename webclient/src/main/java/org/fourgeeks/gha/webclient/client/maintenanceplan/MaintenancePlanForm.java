/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class MaintenancePlanForm extends VLayout implements
		MaintenancePlanSelectionProducer {

	private List<MaintenancePlanSelectionListener> listeners;

	private GHATextItem nameItem, frequencyItem, descriptionItem;
	private GHASelectItem periodOfTimeItem;
	private Validator validator;

	private DynamicForm form;

	/**
	 * this is used to keep the id of the persistent entity in order to update,
	 * is only used with that purpose
	 */
	private MaintenancePlan updatePlan;

	{
		nameItem = new GHATextItem("Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		nameItem.setLength(100);
		nameItem.setRequired(true);
		frequencyItem = new GHATextItem("Frecuencia",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		frequencyItem.setRequired(true);
		frequencyItem.setLength(3);
		periodOfTimeItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		periodOfTimeItem.setRequired(true);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenancePlanSelectionListener>();

		form = new DynamicForm();
	}

	/**
	 * 
	 */
	public MaintenancePlanForm() {
		final HLayout mainPanel = new HLayout();
		form.setTitleOrientation(TitleOrientation.TOP);
		// form.setCellPadding(1);
		form.setNumCols(3);
		form.setItems(nameItem, frequencyItem, periodOfTimeItem,
				new GHASpacerItem(), descriptionItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
		fill();
	}

	/**
	 * Fill the form items that require to be filled
	 */
	private void fill() {
		periodOfTimeItem.setValueMap(TimePeriodEnum.toValueMap());
	}

	/**
	 * Clear the form
	 */
	public void cancel() {
		nameItem.clearValue();
		frequencyItem.clearValue();
		periodOfTimeItem.clearValue();
		descriptionItem.clearValue();
	}

	/**
	 * 
	 */
	public void save() {
		MaintenancePlan maintenancePlan = extract(false);

		// if validation fail
		if (maintenancePlan == null)
			return;

		MaintenancePlanModel.save(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlan>() {

					@Override
					public void onSuccess(MaintenancePlan result) {
						notifyMaintenancePlan(result);
						cancel();
					}
				});
	}

	/**
	 * @return
	 */
	private MaintenancePlan extract(boolean update) {
		final MaintenancePlan maintenancePlan = new MaintenancePlan();
		if (update) {
			maintenancePlan.setId(this.updatePlan.getId());
		}

		if (nameItem.getValue() != null) {
			maintenancePlan.setName(nameItem.getValueAsString());
		}
		if (descriptionItem.getValue() != null) {
			maintenancePlan.setDescription(descriptionItem.getValueAsString());
		}

		// TODO: handle number format exception
		if (frequencyItem.getValue() != null) {
			maintenancePlan.setFrequency(Integer.valueOf(frequencyItem
					.getValueAsString()));
		}
		if (periodOfTimeItem.getValue() != null) {
			maintenancePlan.setPot(TimePeriodEnum.valueOf(periodOfTimeItem
					.getValueAsString()));
		}

		Set<ConstraintViolation<MaintenancePlan>> violations = validator
				.validate(maintenancePlan);
		if (form.validate() && violations.isEmpty())
			return maintenancePlan;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaintenancePlan>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}
			GHANotification.alert(violationsList);
		}
		return null;
	}

	/**
	 * 
	 */
	public void update() {
		MaintenancePlan maintenancePlan = extract(true);

		// if validation fails
		if (maintenancePlan == null)
			return;

		MaintenancePlanModel.update(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlan>() {

					@Override
					public void onSuccess(MaintenancePlan result) {
						notifyMaintenancePlan(result);
					}
				});
	}

	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.updatePlan = maintenancePlan;
		nameItem.setValue(maintenancePlan.getName());
		descriptionItem.setValue(maintenancePlan.getDescription());
		frequencyItem.setValue(maintenancePlan.getFrequency());
		periodOfTimeItem.setValue(maintenancePlan.getPot().name());
	}

	/**
	 * @param activate
	 */
	public void activateForm(boolean activate) {
		nameItem.setDisabled(!activate);
		frequencyItem.setDisabled(!activate);
		periodOfTimeItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan plan) {
		GHANotification.alert("mplan-save-success");
		for (MaintenancePlanSelectionListener listener : listeners) {
			listener.select(plan);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #addMaintenancePlanSelectionListener(org.fourgeeks
	 * .gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #removeMaintenancePlanSelectionListener(org
	 * .fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

}
