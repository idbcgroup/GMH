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

import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author emiliot
 * 
 */
public class MaintenancePlanForm extends GHAForm<MaintenancePlan> implements
		MaintenancePlanSelectionProducer {

	private List<MaintenancePlanSelectionListener> listeners;

	private GHATextItem nameItem, frequencyItem;
	private GHATextAreaItem descriptionItem;
	private GHASelectItem periodOfTimeItem;
	private GHAMaintenancePlanTypeSelectItem typeItem;
	private GHAMaintenancePlanStateSelectItem stateItem;
	private Validator validator;

	private GHADynamicForm form;

	{
		nameItem = new GHATextItem(GHAStrings.get("name"), true, changedHandler);
		nameItem.setLength(100);
		frequencyItem = new GHATextItem(GHAStrings.get("frecuency"), true,
				changedHandler);
		frequencyItem.setLength(3);
		periodOfTimeItem = new GHAPeriodOfTimeSelectItem(true, changedHandler);
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				changedHandler);
		descriptionItem.setColSpan(3);
		typeItem = new GHAMaintenancePlanTypeSelectItem(true, changedHandler);
		stateItem = new GHAMaintenancePlanStateSelectItem(true, changedHandler);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenancePlanSelectionListener>();

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	/**
	 * 
	 */
	public MaintenancePlanForm() {
		final HLayout mainPanel = new HLayout();
		form.setItems(nameItem, frequencyItem, periodOfTimeItem,
				new GHASpacerItem(), typeItem, stateItem, descriptionItem,
				new GHASpacerItem());
		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
	}

	@Override
	public void clear() {
		super.clear();
		nameItem.clearValue();
		frequencyItem.clearValue();
		periodOfTimeItem.clearValue();
		descriptionItem.clearValue();
		stateItem.clearValue();
		typeItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private MaintenancePlan extract(boolean update) {
		final MaintenancePlan maintenancePlan = new MaintenancePlan();
		if (update) {
			maintenancePlan.setId(this.originalEntity.getId());
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
		if (periodOfTimeItem.getValue() != null)
			maintenancePlan.setPot(TimePeriodEnum.valueOf(periodOfTimeItem
					.getValueAsString()));

		if (typeItem.getValue() != null)
			maintenancePlan.setType(MaintenancePlanType.valueOf(typeItem
					.getValueAsString()));
		if (stateItem.getValue() != null)
			maintenancePlan.setState(MaintenancePlanState.valueOf(stateItem
					.getValueAsString()));

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

	@Override
	public void notifyMaintenancePlan(MaintenancePlan plan) {
		for (MaintenancePlanSelectionListener listener : listeners)
			listener.select(plan);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<MaintenancePlan> callback) {
		MaintenancePlan maintenancePlan = extract(false);
		if (maintenancePlan == null)
			return;

		MaintenancePlanModel.save(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlan>() {

					@Override
					public void onSuccess(MaintenancePlan result) {
						hasUnCommittedChanges = false;
						notifyMaintenancePlan(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});
	}

	@Override
	public void set(MaintenancePlan maintenancePlan) {
		this.originalEntity = maintenancePlan;
		nameItem.setValue(maintenancePlan.getName());
		descriptionItem.setValue(maintenancePlan.getDescription());
		frequencyItem.setValue(maintenancePlan.getFrequency());
		periodOfTimeItem.setValue(maintenancePlan.getPot().name());
		typeItem.setValue(maintenancePlan.getType().name());
		stateItem.setValue(maintenancePlan.getState().name());
	}

	private void toggleForm(boolean active) {
		nameItem.setDisabled(!active);
		frequencyItem.setDisabled(!active);
		periodOfTimeItem.setDisabled(!active);
		descriptionItem.setDisabled(!active);
		typeItem.setDisabled(!active);
		stateItem.setDisabled(!active);
	}

	@Override
	public void update(final GHAAsyncCallback<MaintenancePlan> callback) {
		MaintenancePlan maintenancePlan = extract(true);

		if (maintenancePlan == null)
			return;

		MaintenancePlanModel.update(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlan>() {

					@Override
					public void onSuccess(MaintenancePlan result) {
						hasUnCommittedChanges = false;
						notifyMaintenancePlan(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}

}
