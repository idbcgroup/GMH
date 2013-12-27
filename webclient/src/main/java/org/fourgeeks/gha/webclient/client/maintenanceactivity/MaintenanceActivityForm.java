/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHACurrencyTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenanceActivitySubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenanceActivityTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
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
public class MaintenanceActivityForm extends GHAForm<MaintenanceActivity>
		implements MaintenanceActivitySelectionProducer {
	private List<MaintenanceActivitySelectionListener> listeners;

	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			estimatedTimeTextItem, estimatedCostTextItem,
			timesEffectuedTextItem, eiasWhitThisActivityTextItem,
			lastEffectuedByTextItem;
	private GHATextAreaItem instructionsAndObservationsTextAreaItem;
	private GHAMaintenanceActivityStateSelectItem stateSelectItem;
	private GHAMaintenanceActivityTypeSelectItem typeSelectItem;
	private GHAMaintenanceActivitySubTypeSelectItem subTypeSelectItem;
	private GHACheckboxItem isSubProtocolCheckboxItem,
			materialsRequierdCheckboxItem, toolsRequierdCheckboxItem,
			equipsRequiredCheckboxItem;
	private GHAEiaTypeSelectItem eiaTypeSelectItem;
	private GHAPeriodOfTimeSelectItem estimatedTimePoTSelectItem;
	private GHACurrencyTypeSelectItem estimatedCostCurrencySelectItem;
	private GHARoleSelectItem roleSelectItem;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHADateItem lastEffectuatedDateItem;

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
		stateSelectItem = new GHAMaintenanceActivityStateSelectItem(true,
				changedHandler);
		typeSelectItem = new GHAMaintenanceActivityTypeSelectItem(true,
				changedHandler);
		subTypeSelectItem = new GHAMaintenanceActivitySubTypeSelectItem(true,
				changedHandler);
		eiaTypeSelectItem = new GHAEiaTypeSelectItem(false, changedHandler);
		eiaTypeSelectItem.setTitle("Aplicar a tipo de equipo");
		descriptionTextItem = new GHATextItem(GHAStrings.get("description"),
				false, changedHandler);
		descriptionTextItem.setColSpan(4);
		isSubProtocolCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("activity-is-subprotocol"), changedHandler);
		materialsRequierdCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("require-materials"), changedHandler);
		toolsRequierdCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("require-tools"), changedHandler);
		equipsRequiredCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("require-equips"), changedHandler);
		instructionsAndObservationsTextAreaItem = new GHATextAreaItem(
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
		instructionsAndObservationsTextAreaItem.setColSpan(4);
		providerSelectItem = new GHAExternalProviderSelectItem(false,
				changedHandler);
		roleSelectItem = new GHARoleSelectItem(false, changedHandler);

		timesEffectuedTextItem = new GHATextItem(
				GHAStrings.get("times-effectuated"), false);
		eiasWhitThisActivityTextItem = new GHATextItem(
				GHAStrings.get("eias-with-this-activity"), false);
		lastEffectuatedDateItem = new GHADateItem(
				GHAStrings.get("last-effectuated-date"), false);
		lastEffectuedByTextItem = new GHATextItem(
				GHAStrings.get("last-effectuated-by"), false);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenanceActivitySelectionListener>();

		form = new GHADynamicForm(4, FormType.SECTIONFORM_FORM);
	}

	/**
	 * 
	 */
	public MaintenanceActivityForm() {
		final HLayout mainPanel = new HLayout();

		form.setItems(codeTextItem, nameTextItem, stateSelectItem,
				new GHASpacerItem(), typeSelectItem, subTypeSelectItem,
				eiaTypeSelectItem, new GHASpacerItem(), descriptionTextItem,
				isSubProtocolCheckboxItem, materialsRequierdCheckboxItem,
				toolsRequierdCheckboxItem, equipsRequiredCheckboxItem,
				estimatedTimeTextItem, estimatedTimePoTSelectItem,
				estimatedCostTextItem, estimatedCostCurrencySelectItem,
				instructionsAndObservationsTextAreaItem, providerSelectItem,
				roleSelectItem, new GHASpacerItem(2), timesEffectuedTextItem,
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
		descriptionTextItem.clearValue();
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
		final MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
		if (update) {
			maintenanceActivity.setId(this.updateActivity.getId());
		}

		if (nameTextItem.getValue() != null) {
			maintenanceActivity.setName(nameTextItem.getValueAsString());
		}
		if (descriptionTextItem.getValue() != null) {
			maintenanceActivity.setDescription(descriptionTextItem
					.getValueAsString());
		}

		Set<ConstraintViolation<MaintenanceActivity>> violations = validator
				.validate(maintenanceActivity);
		if (form.validate() && violations.isEmpty())
			return maintenanceActivity;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaintenanceActivity>> it = violations
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
			GHANotification.alert(violationsList);
		}
		return null;
	}

	// Producer Stuff
	@Override
	public void notifyMaintenanceActivity(MaintenanceActivity activity) {
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

		nameTextItem.setValue(entity.getName());
		descriptionTextItem.setValue(entity.getDescription());
	}

	private void toogleForm(boolean activate) {
		nameTextItem.setDisabled(!activate);
		descriptionTextItem.setDisabled(!activate);
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
