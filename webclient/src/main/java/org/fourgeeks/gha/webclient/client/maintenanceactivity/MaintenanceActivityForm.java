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
import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivitySubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHACurrencyTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.MaintenanceActivitySubProtocolListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.MaintenanceActivitySubProtocolProducer;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author emiliot, naramirez
 * 
 */
public class MaintenanceActivityForm extends GHAForm<MaintenanceActivity>
		implements MaintenanceActivitySelectionProducer,
		MaintenanceActivitySubProtocolProducer {
	private List<MaintenanceActivitySelectionListener> listeners;
	private List<MaintenanceActivitySubProtocolListener> subProtocolListeners;

	private GHATextItem codeTextItem, nameTextItem, estimatedTimeTextItem,
			estimatedCostTextItem, timesEffectuedTextItem,
			eiasWhitThisActivityTextItem, lastEffectuedByTextItem;
	private GHATextAreaItem instructionsAndObsTextAreaItem,
			descriptionTextItem;
	private GHAActivityStateSelectItem stateSelectItem;
	private GHAActivityTypeSelectItem typeSelectItem;
	private GHAActivitySubTypeSelectItem subTypeSelectItem;
	private GHACheckboxItem isSubProtocolCheckboxItem;
	private GHAPeriodOfTimeSelectItem estimatedTimePoTSelectItem;
	private GHACurrencyTypeSelectItem estimatedCostCurrencySelectItem;
	private GHADateItem lastEffectuatedDateItem;
	private boolean isSubProtocol;

	private Validator validator;

	private GHADynamicForm form;

	{
		codeTextItem = new GHATextItem(GHAStrings.get("code"), false);
		nameTextItem = new GHATextItem(GHAStrings.get("activity-name"), true,
				changedHandler);
		nameTextItem.setKeyPressFilter("^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+$");

		stateSelectItem = new GHAActivityStateSelectItem();
		stateSelectItem.setDisabled(true);
		typeSelectItem = new GHAActivityTypeSelectItem(false, changedHandler);
		subTypeSelectItem = new GHAActivitySubTypeSelectItem(false,
				changedHandler);
		typeSelectItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				changedHandler.onChanged(event);
				subTypeSelectItem.clearValue();
				subTypeSelectItem.fill(typeSelectItem.getValueAsActivityType());

				if (typeSelectItem.getValue() == null)
					subTypeSelectItem.setDisabled(true);
				else
					subTypeSelectItem.setDisabled(false);
			}
		});

		descriptionTextItem = new GHATextAreaItem(
				GHAStrings.get("description"), changedHandler);
		descriptionTextItem.setColSpan(4);
		isSubProtocolCheckboxItem = new GHACheckboxItem(
				GHAStrings.get("activity-is-subprotocol"), changedHandler);
		instructionsAndObsTextAreaItem = new GHATextAreaItem(
				GHAStrings.get("activity-instructions-and-observations"),
				changedHandler);
		estimatedTimeTextItem = new GHATextItem(
				GHAStrings.get("activity-estimated-time"), false,
				changedHandler);
		estimatedTimeTextItem.setKeyPressFilter("^[0-9]+$");
		estimatedTimePoTSelectItem = new GHAPeriodOfTimeSelectItem(false,
				changedHandler);
		estimatedTimePoTSelectItem.setDefaultValue(TimePeriodEnum.HOURS.name());
		estimatedCostTextItem = new GHATextItem(
				GHAStrings.get("activity-estimated-cost"), false,
				changedHandler);
		estimatedCostTextItem.setKeyPressFilter("^[0-9]+$");
		estimatedCostCurrencySelectItem = new GHACurrencyTypeSelectItem(false,
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
		subProtocolListeners = new ArrayList<MaintenanceActivitySubProtocolListener>();

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);

		isSubProtocolCheckboxItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				isSubProtocol = (Boolean) event.getValue();
				notifyMaintenanceActivitySubProtocolSubTabs();
			}
		});
	}

	/**
	 * 
	 */
	public MaintenanceActivityForm() {
		final HLayout mainPanel = new HLayout();

		form.setItems(codeTextItem, nameTextItem, stateSelectItem,
				new GHASpacerItem(), typeSelectItem, subTypeSelectItem,
				isSubProtocolCheckboxItem, new GHASpacerItem(),
				descriptionTextItem, estimatedTimeTextItem,
				estimatedTimePoTSelectItem, estimatedCostTextItem,
				estimatedCostCurrencySelectItem,
				instructionsAndObsTextAreaItem, timesEffectuedTextItem,
				eiasWhitThisActivityTextItem, lastEffectuatedDateItem,
				lastEffectuedByTextItem);

		ActivityType type = new ActivityType(1);
		typeSelectItem.setValue(type.getId());
		subTypeSelectItem.fill(type);

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
		descriptionTextItem.clearValue();
		isSubProtocolCheckboxItem.clearValue();
		instructionsAndObsTextAreaItem.clearValue();
		estimatedTimeTextItem.clearValue();
		estimatedTimePoTSelectItem.clearValue();
		estimatedCostTextItem.clearValue();
		estimatedCostCurrencySelectItem.clearValue();

		timesEffectuedTextItem.clearValue();
		eiasWhitThisActivityTextItem.clearValue();
		lastEffectuatedDateItem.clearValue();
		lastEffectuedByTextItem.clearValue();

		ActivityType type = new ActivityType(1);
		typeSelectItem.setValue(type.getId());
		subTypeSelectItem.fill(type);
		subTypeSelectItem.clearValue();
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

			entity.setId(this.originalEntity.getId());

			// activity = this.originalEntity.getActivity();
			activity.setId(this.originalEntity.getId());

			if (stateSelectItem.getValue() != null) {

				activity.setState(ActivityState.valueOf(stateSelectItem
						.getValueAsString()));

			}
		} else {
			activity.setState(ActivityState.CREATED);
		}

		if (nameTextItem.getValue() != null) {
			activity.setName(nameTextItem.getValueAsString());
		}

		if (descriptionTextItem.getValue() != null) {
			activity.setDescription(descriptionTextItem.getValueAsString());
		}

		if (typeSelectItem.getValue() != null) {
			long value = Long.valueOf(typeSelectItem.getValueAsString());
			activity.setType(new ActivityType(value));
		}

		if (subTypeSelectItem.getValue() != null) {
			long value = Long.valueOf(subTypeSelectItem.getValueAsString());
			activity.setSubType(new ActivityType(value));
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
			final String value = estimatedCostCurrencySelectItem
					.getValueAsString();
			activity.setEstimatedCostCurrency(CurrencyTypeEnum.valueOf(value));

		}

		if (instructionsAndObsTextAreaItem.getValue() != null) {
			final String value = instructionsAndObsTextAreaItem
					.getValueAsString();
			activity.setInstructionsAndObservations(value);
		}

		activity.setIsSubProtocol(isSubProtocolCheckboxItem.getValueAsBoolean());
		entity.setActivity(activity);
		final Set<ConstraintViolation<Activity>> violations = validator
				.validate(activity);

		if (form.validate() && violations.isEmpty()) {
			return entity;

		} else {
			final List<String> violationsList = new ArrayList<String>();
			for (final Iterator<ConstraintViolation<Activity>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}
			String mensaje = "name-activity-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}
			mensaje = "category-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}
			mensaje = "sub-category-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}
			mensaje = "estimated-duration-time-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}
			mensaje = "time-period-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}
			mensaje = "estimated-cost-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}
			mensaje = "currency-not-null";
			if (violationsList.contains(mensaje)) {
				GHAErrorMessageProcessor.alert(mensaje);
				return null;
			}

		}
		return null;
	}

	// Producer Stuff
	@Override
	public void notifyMaintenanceActivity(MaintenanceActivity activity) {
		for (final MaintenanceActivitySelectionListener listener : listeners) {
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
		final MaintenanceActivity maintenanceActivity = extract(false);
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
		descriptionTextItem.setValue(activity.getDescription());
		isSubProtocolCheckboxItem.setValue(activity.getIsSubProtocol());
		instructionsAndObsTextAreaItem.setValue(activity
				.getInstructionsAndObservations());
		estimatedTimeTextItem.setValue(activity.getEstimatedDuration());
		estimatedTimePoTSelectItem.setValue(activity.getEstimatedDurationPoT());
		estimatedCostTextItem.setValue(activity.getEstimatedCost());
		estimatedCostCurrencySelectItem.setValue(activity
				.getEstimatedCostCurrency().name());
		isSubProtocol = entity.getActivity().getIsSubProtocol();

		ActivityType type = activity.getType();
		if (type != null) {
			typeSelectItem.setValue(type.getId());
			subTypeSelectItem.fill(type);
		} else
			typeSelectItem.clearValue();

		ActivityType subType = activity.getSubType();
		if (subType != null)
			subTypeSelectItem.setValue(subType.getId());
		else
			subTypeSelectItem.clearValue();

		notifyMaintenanceActivitySubProtocolSubTabs();
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
		typeSelectItem.setDisabled(!activate);
		subTypeSelectItem.setDisabled(!activate);
		descriptionTextItem.setDisabled(!activate);
		isSubProtocolCheckboxItem.setDisabled(!activate);
		instructionsAndObsTextAreaItem.setDisabled(!activate);
		estimatedTimeTextItem.setDisabled(!activate);
		estimatedTimePoTSelectItem.setDisabled(!activate);
		estimatedCostTextItem.setDisabled(!activate);
		estimatedCostCurrencySelectItem.setDisabled(!activate);
	}

	@Override
	public void update(final GHAAsyncCallback<MaintenanceActivity> callback) {

		if (!hasUnCommittedChanges)
			return;

		final MaintenanceActivity entity = extract(true);

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

	@Override
	public void addMaintenanceActivitySubProtocolListener(
			MaintenanceActivitySubProtocolListener listener) {
		subProtocolListeners.add(listener);
	}

	@Override
	public void removeMaintenanceActivitySubProtocolListener(
			MaintenanceActivitySubProtocolListener listener) {
		subProtocolListeners.remove(listener);
	}

	@Override
	public void notifyMaintenanceActivitySubProtocolSubTabs() {
		for (final MaintenanceActivitySubProtocolListener listener : subProtocolListeners) {
			listener.changeSubTabState(isSubProtocol);
		}
	}
}
