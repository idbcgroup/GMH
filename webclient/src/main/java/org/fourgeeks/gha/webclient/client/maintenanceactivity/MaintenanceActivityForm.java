/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityCategorySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivitySubCategorySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHACurrencyTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author emiliot, naramirez
 * 
 */
public class MaintenanceActivityForm extends GHAForm<MaintenanceActivity>
		implements MaintenanceActivitySelectionProducer {
	private List<MaintenanceActivitySelectionListener> listeners;

	private GHATextItem codeTextItem, nameTextItem, estimatedTimeTextItem,
			estimatedCostTextItem, timesEffectuedTextItem,
			eiasWhitThisActivityTextItem, lastEffectuedByTextItem;
	private GHATextAreaItem instructionsAndObsTextAreaItem,
			descriptionTextItem;
	private GHAActivityStateSelectItem stateSelectItem;
	private GHAActivityCategorySelectItem categorySelectItem;
	private GHAActivitySubCategorySelectItem subCategorySelectItem;
	private GHACheckboxItem isSubProtocolCheckboxItem,
			materialsRequierdCheckboxItem, toolsRequierdCheckboxItem,
			equipsRequiredCheckboxItem;
	private GHAPeriodOfTimeSelectItem estimatedTimePoTSelectItem;
	private GHACurrencyTypeSelectItem estimatedCostCurrencySelectItem;
	private GHADateItem lastEffectuatedDateItem;

	// TODO evaluar si este dato: private GHARoleSelectItem roleSelectItem;
	// TODO evaluar si este dato: private GHAExternalProviderSelectItem
	// providerSelectItem;

	private Validator validator;

	private GHADynamicForm form;

	/**
	 * this is used to keep the id of the persistent entity in order to update,
	 * is only used with that purpose
	 */
	private MaintenanceActivity updateActivity;

	{
		codeTextItem = new GHATextItem(GHAStrings.get("code"), false);
		nameTextItem = new GHATextItem(GHAStrings.get("activity-name"), true,
				changedHandler);
		stateSelectItem = new GHAActivityStateSelectItem();
		stateSelectItem.setDisabled(true);
		categorySelectItem = new GHAActivityCategorySelectItem(true,
				changedHandler);
		subCategorySelectItem = new GHAActivitySubCategorySelectItem(true,
				changedHandler);
		descriptionTextItem = new GHATextAreaItem(
				GHAStrings.get("description"), changedHandler);
		descriptionTextItem.setColSpan(4);
		isSubProtocolCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("activity-is-subprotocol"), changedHandler);
		materialsRequierdCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("require-materials"), changedHandler);
		toolsRequierdCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("require-tools"), changedHandler);
		equipsRequiredCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("require-equips"), changedHandler);
		instructionsAndObsTextAreaItem = new GHATextAreaItem(
				GHAStrings.get("activity-instructions-and-observations"),
				changedHandler);
		estimatedTimeTextItem = new GHATextItem(
				GHAStrings.get("estimated-time"), true, changedHandler);
		estimatedTimePoTSelectItem = new GHAPeriodOfTimeSelectItem(true,
				changedHandler);
		estimatedCostTextItem = new GHATextItem(
				GHAStrings.get("estimated-cost"), true, changedHandler);
		estimatedCostCurrencySelectItem = new GHACurrencyTypeSelectItem(true,
				changedHandler);
		instructionsAndObsTextAreaItem.setColSpan(4);

		timesEffectuedTextItem = new GHATextItem(
				GHAStrings.get("times-effectuated"), false);
		eiasWhitThisActivityTextItem = new GHATextItem(
				GHAStrings.get("eias-with-this-activity"), false);
		lastEffectuatedDateItem = new GHADateItem(
				GHAStrings.get("last-effectuated-date"), false);
		lastEffectuedByTextItem = new GHATextItem(
				GHAStrings.get("last-effectuated-by"), false);

		timesEffectuedTextItem.setVisible(false);
		eiasWhitThisActivityTextItem.setVisible(false);
		lastEffectuatedDateItem.setVisible(false);
		lastEffectuedByTextItem.setVisible(false);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenanceActivitySelectionListener>();

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
	}

	/**
	 * 
	 */
	public MaintenanceActivityForm() {
		final HLayout mainPanel = new HLayout();

		form.setItems(codeTextItem, nameTextItem, stateSelectItem,
				new GHASpacerItem(), categorySelectItem, subCategorySelectItem,
				new GHASpacerItem(2), descriptionTextItem,
				isSubProtocolCheckboxItem, materialsRequierdCheckboxItem,
				toolsRequierdCheckboxItem, equipsRequiredCheckboxItem,
				estimatedTimeTextItem, estimatedTimePoTSelectItem,
				estimatedCostTextItem, estimatedCostCurrencySelectItem,
				instructionsAndObsTextAreaItem, timesEffectuedTextItem,
				eiasWhitThisActivityTextItem, lastEffectuatedDateItem,
				lastEffectuedByTextItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	@Override
	public void activate() {
		toogleForm(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #addMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.add(maintenanceActivitySelectionListener);

	}

	@Override
	public void clear() {
		super.clear();

		nameTextItem.clearValue();
		stateSelectItem.clearValue();
		categorySelectItem.clearValue();
		subCategorySelectItem.clearValue();
		descriptionTextItem.clearValue();
		isSubProtocolCheckboxItem.clearValue();
		materialsRequierdCheckboxItem.clearValue();
		toolsRequierdCheckboxItem.clearValue();
		equipsRequiredCheckboxItem.clearValue();
		instructionsAndObsTextAreaItem.clearValue();
		estimatedTimeTextItem.clearValue();
		estimatedTimePoTSelectItem.clearValue();
		estimatedCostTextItem.clearValue();
		estimatedCostCurrencySelectItem.clearValue();

		timesEffectuedTextItem.clearValue();
		eiasWhitThisActivityTextItem.clearValue();
		lastEffectuatedDateItem.clearValue();
		lastEffectuedByTextItem.clearValue();
	}

	@Override
	public void deactivate() {
		toogleForm(false);
	}

	/**
	 * @param b
	 * @return
	 */
	private MaintenanceActivity extract(boolean update) {
		final MaintenanceActivity entity = new MaintenanceActivity();
		Activity activity = new Activity();

		if (update) {
			entity.setId(this.updateActivity.getId());
			activity = this.updateActivity.getActivity();

			if (stateSelectItem.getValue() != null) {
				activity.setState(ActivityState.valueOf(stateSelectItem
						.getValueAsString()));
			}
		} else
			activity.setState(ActivityState.CREATED);

		if (nameTextItem.getValue() != null) {
			activity.setName(nameTextItem.getValueAsString());
		}
		if (descriptionTextItem.getValue() != null) {
			activity.setDescription(descriptionTextItem.getValueAsString());
		}

		if (categorySelectItem.getValue() != null) {
			activity.setCategory(ActivityCategoryEnum
					.valueOf(categorySelectItem.getValueAsString()));
		}
		if (subCategorySelectItem.getValue() != null) {
			activity.setSubCategory(ActivitySubCategoryEnum
					.valueOf(subCategorySelectItem.getValueAsString()));
		}
		if (estimatedTimeTextItem.getValue() != null) {
			activity.setEstimatedDuration(BigDecimal.valueOf(Double
					.valueOf(estimatedTimeTextItem.getValueAsString())));
		}
		if (estimatedTimePoTSelectItem.getValue() != null) {
			activity.setEstimatedDurationPoT(TimePeriodEnum
					.valueOf(estimatedTimePoTSelectItem.getValueAsString()));
		}
		if (estimatedCostTextItem.getValue() != null) {
			activity.setEstimatedCost(BigDecimal.valueOf(Double
					.valueOf(estimatedCostTextItem.getValueAsString())));
		}
		if (estimatedCostCurrencySelectItem.getValue() != null) {
			String value = estimatedCostCurrencySelectItem.getValueAsString();
			activity.setEstimatedCostCurrency(CurrencyTypeEnum.valueOf(value));
		}
		if (instructionsAndObsTextAreaItem.getValue() != null) {
			String value = instructionsAndObsTextAreaItem.getValueAsString();
			activity.setInstructionsAndObservations(value);
		}

		// TODO Agregar atributos para proveedor responsable
		// TODO Agregar atributos para cargo responsable

		activity.setIsSubProtocol(isSubProtocolCheckboxItem.getValueAsBoolean());
		activity.setMaterialsRequired(materialsRequierdCheckboxItem
				.getValueAsBoolean());
		activity.setEquipsRequired(equipsRequiredCheckboxItem
				.getValueAsBoolean());
		activity.setIsToolsRequired(toolsRequierdCheckboxItem
				.getValueAsBoolean());

		entity.setActivity(activity);

		Set<ConstraintViolation<MaintenanceActivity>> violations = validator
				.validate(entity);
		if (form.validate() && violations.isEmpty())
			return entity;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaintenanceActivity>> it = violations
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
			GHAAlertManager.alert(violationsList);
		}
		return null;
	}

	// Producer Stuff
	@Override
	public void notifyMaintenanceActivity(MaintenanceActivity activity) {
		GHAAlertManager.alert("mact-save-success");
		for (MaintenanceActivitySelectionListener listener : listeners) {
			listener.select(activity);
		}
	}

	@Override
	public void onResize(ResizeEvent event) {
		form.resize();
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void save(final GHAAsyncCallback<MaintenanceActivity> callback) {
		MaintenanceActivity maintenanceActivity = extract(false);

		if (maintenanceActivity == null)
			return;

		MaintenanceActivityModel.save(maintenanceActivity,
				new GHAAsyncCallback<MaintenanceActivity>() {
					@Override
					public void onSuccess(MaintenanceActivity result) {
						hasUnCommittedChanges = false;
						notifyMaintenanceActivity(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});

	}

	@Override
	public void set(MaintenanceActivity entity) {
		this.originalEntity = entity;
		final Activity activity = entity.getActivity();

		nameTextItem.setValue(activity.getName());
		stateSelectItem.setValue(activity.getState());
		categorySelectItem.setValue(activity.getCategory());
		subCategorySelectItem.setValue(activity.getSubCategory());
		descriptionTextItem.setValue(activity.getDescription());
		isSubProtocolCheckboxItem.setValue(activity.getIsSubProtocol());
		materialsRequierdCheckboxItem.setValue(activity
				.getIsMaterialsRequired());
		toolsRequierdCheckboxItem.setValue(activity.getIsToolsRequired());
		equipsRequiredCheckboxItem.setValue(activity.getIsEquipsRequired());
		instructionsAndObsTextAreaItem.setValue(activity
				.getInstructionsAndObservations());
		estimatedTimeTextItem.setValue(activity.getEstimatedDuration());
		estimatedTimePoTSelectItem.setValue(activity.getEstimatedDurationPoT());
		estimatedCostTextItem.setValue(activity.getEstimatedCost());
		estimatedCostCurrencySelectItem.setValue(activity
				.getEstimatedCostCurrency());

		showPlanStadisticsItems();
	}

	private void showPlanStadisticsItems() {
		timesEffectuedTextItem.show();
		eiasWhitThisActivityTextItem.show();
		lastEffectuatedDateItem.show();
		lastEffectuedByTextItem.show();
	}

	private void toogleForm(boolean activate) {
		nameTextItem.setDisabled(!activate);
		categorySelectItem.setDisabled(!activate);
		subCategorySelectItem.setDisabled(!activate);
		descriptionTextItem.setDisabled(!activate);
		isSubProtocolCheckboxItem.setDisabled(!activate);
		materialsRequierdCheckboxItem.setDisabled(!activate);
		toolsRequierdCheckboxItem.setDisabled(!activate);
		equipsRequiredCheckboxItem.setDisabled(!activate);
		instructionsAndObsTextAreaItem.setDisabled(!activate);
		estimatedTimeTextItem.setDisabled(!activate);
		estimatedTimePoTSelectItem.setDisabled(!activate);
		estimatedCostTextItem.setDisabled(!activate);
		estimatedCostCurrencySelectItem.setDisabled(!activate);
	}

	@Override
	public void update(final GHAAsyncCallback<MaintenanceActivity> callback) {
		MaintenanceActivity entity = extract(true);

		if (entity == null)
			return;

		MaintenanceActivityModel.update(entity,
				new GHAAsyncCallback<MaintenanceActivity>() {
					@Override
					public void onSuccess(MaintenanceActivity result) {
						hasUnCommittedChanges = false;
						notifyMaintenanceActivity(result);
						if (callback != null)
							callback.onSuccess(result);
					}
				});
	}

}
