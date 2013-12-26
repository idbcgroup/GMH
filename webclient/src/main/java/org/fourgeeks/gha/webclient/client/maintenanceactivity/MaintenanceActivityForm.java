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
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenanceActivitySubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenanceActivityTypeSelectItem;
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

	private GHATextItem codeTextItem, nameTextItem;
	private GHATextAreaItem descriptionTextAreaItem;
	private GHAMaintenanceActivityStateSelectItem stateSelectItem;
	private GHAMaintenanceActivityTypeSelectItem typeSelectItem;
	private GHAMaintenanceActivitySubTypeSelectItem subTypeSelectItem;
	private GHACheckboxItem isSubProtocolCheckboxItem;

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
		descriptionTextAreaItem = new GHATextAreaItem(
				GHAStrings.get("description"), changedHandler);
		descriptionTextAreaItem.setColSpan(4);
		stateSelectItem = new GHAMaintenanceActivityStateSelectItem(true,
				changedHandler);
		typeSelectItem = new GHAMaintenanceActivityTypeSelectItem(true,
				changedHandler);
		subTypeSelectItem = new GHAMaintenanceActivitySubTypeSelectItem(true,
				changedHandler);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenanceActivitySelectionListener>();

		form = new GHADynamicForm(4, FormType.SECTIONFORM_FORM);
	}

	/**
	 * 
	 */
	public MaintenanceActivityForm() {
		final HLayout mainPanel = new HLayout();
		form.setItems(nameTextItem, new GHASpacerItem(3),
				descriptionTextAreaItem);

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
		descriptionTextAreaItem.clearValue();
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
		if (descriptionTextAreaItem.getValue() != null) {
			maintenanceActivity.setDescription(descriptionTextAreaItem
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
		descriptionTextAreaItem.setValue(entity.getDescription());
	}

	private void toogleForm(boolean activate) {
		nameTextItem.setDisabled(!activate);
		descriptionTextAreaItem.setDisabled(!activate);
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
