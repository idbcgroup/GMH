/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

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

	{
		nameItem = new GHATextItem("Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		frequencyItem = new GHATextItem("Frecuencia",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		periodOfTimeItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenancePlanSelectionListener>();
	}

	public MaintenancePlanForm() {
		final HLayout mainPanel = new HLayout();
		final DynamicForm form = new DynamicForm();
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
		
		//if validation fail
		if(maintenancePlan == null)
			return;
		
		MaintenancePlanModel.save(maintenancePlan, new GHAAsyncCallback<MaintenancePlan>() {
			
			@Override
			public void onSuccess(MaintenancePlan result) {
				notifyMaintenancePlan(result);
				cancel();
			}
		});
	}

	/**
	 * @param b
	 * @return
	 */
	private MaintenancePlan extract(boolean update) {
		final MaintenancePlan maintenancePlan = new MaintenancePlan();

		maintenancePlan.setName(nameItem.getValueAsString());
		maintenancePlan.setDescription(descriptionItem.getValueAsString());
		//TODO: handle number format exception
		maintenancePlan.setFrequency(Integer.parseInt(frequencyItem
				.getValueAsString()));
		if (periodOfTimeItem.getValue() != null) {
			maintenancePlan.setPot(TimePeriodEnum.valueOf(periodOfTimeItem
					.getValueAsString()));
		}
		
		//TODO: validate
		return maintenancePlan;
	}

	/**
	 * 
	 */
	public void update() {
		// TODO Auto-generated method stub
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

	// Producer Stuff
	private void notifyMaintenancePlan(MaintenancePlan plan) {
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
