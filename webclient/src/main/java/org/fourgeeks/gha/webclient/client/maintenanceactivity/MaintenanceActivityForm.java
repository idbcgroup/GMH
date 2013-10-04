/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
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
public class MaintenanceActivityForm extends VLayout implements
		MaintenanceActivitySelectionProducer {
	private List<MaintenanceActivitySelectionListener> listeners;
	private GHATextItem nameItem, descriptionItem;
	private Validator validator;

	/**
	 * this is used to keep the id of the persistent entity in order to update,
	 * is only used with that purpose
	 */
	private MaintenanceActivity updateActivity;

	{
		nameItem = new GHATextItem("Nombre de la Actividad", 150);
		nameItem.setLength(100);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenanceActivitySelectionListener>();
	}

	public MaintenanceActivityForm() {
		final HLayout mainPanel = new HLayout();

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(nameItem, new GHASpacerItem(3), descriptionItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	public void cancel() {
		nameItem.clearValue();
		descriptionItem.clearValue();
	}

	/**
	 * 
	 */
	public void save() {
		MaintenanceActivity maintenanceActivity = extract(false);

		if (maintenanceActivity == null)
			return;
		MaintenanceActivityModel.save(maintenanceActivity,
				new GHAAsyncCallback<MaintenanceActivity>() {

					@Override
					public void onSuccess(MaintenanceActivity result) {
						notifyMaintenanceActivity(result);
						cancel();
					}
				});
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

		if (nameItem.getValue() != null) {
			maintenanceActivity.setName(nameItem.getValueAsString());
		}
		if (descriptionItem.getValue() != null) {
			maintenanceActivity.setDescription(descriptionItem
					.getValueAsString());
		}

		Set<ConstraintViolation<MaintenanceActivity>> violations = validator
				.validate(maintenanceActivity);
		if (!violations.isEmpty()) {
			GHANotification.alert(GHAStrings.get(violations.iterator().next()
					.getMessage()));
			return null;
		}
		return maintenanceActivity;
	}

	/**
	 * 
	 */
	public void update() {
		MaintenanceActivity maintenanceActivity = extract(true);
		if (maintenanceActivity == null)
			return;
		MaintenanceActivityModel.update(maintenanceActivity,
				new GHAAsyncCallback<MaintenanceActivity>() {

					@Override
					public void onSuccess(MaintenanceActivity result) {
						notifyMaintenanceActivity(result);
					}
				});
	}

	/**
	 * @param activate
	 */
	public void activateForm(boolean activate) {
		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
	}

	public void setMaintenanceActivity(MaintenanceActivity maintenanceActivity) {
		this.updateActivity = maintenanceActivity;
		nameItem.setValue(maintenanceActivity.getName());
		descriptionItem.setValue(maintenanceActivity.getDescription());
	}

	// Producer Stuff
	public void notifyMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) {
		for (MaintenanceActivitySelectionListener listener : listeners) {
			listener.select(maintenanceActivity);
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #removeMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.remove(maintenanceActivitySelectionListener);
	}

}
