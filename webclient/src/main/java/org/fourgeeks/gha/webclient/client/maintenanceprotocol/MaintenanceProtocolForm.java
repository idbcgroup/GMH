/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
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
public class MaintenanceProtocolForm extends VLayout implements
		MaintenanceProtocolSelectionProducer {
	private GHATextItem nameItem, descriptionItem;
	private List<MaintenanceProtocolSelectionListener> listeners;
	private Validator validator;

	private DynamicForm form;

	/**
	 * this is used to keep the id of the persistent entity in order to update,
	 * is only used with that purpose
	 */
	private MaintenanceProtocol updateProtocol;

	{
		nameItem = new GHATextItem("Nombre del Protocolo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		nameItem.setRequired(true);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		listeners = new ArrayList<MaintenanceProtocolSelectionListener>();
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		form = new DynamicForm();
	}

	/**
	 * 
	 */
	public MaintenanceProtocolForm() {
		final HLayout mainPanel = new HLayout();

		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setItems(nameItem, descriptionItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	public void cancel() {
		nameItem.clearValue();
		descriptionItem.clearValue();
	}

	public void save() {
		MaintenanceProtocol maintenanceProtocol = extract(false);

		// if validation fails
		if (maintenanceProtocol == null)
			return;
		MaintenanceProtocolModel.save(maintenanceProtocol,
				new GHAAsyncCallback<MaintenanceProtocol>() {

					@Override
					public void onSuccess(MaintenanceProtocol result) {
						notifyMaintenanceProtocol(result);
						cancel();
					}
				});
	}

	/**
	 * @param b
	 * @return
	 */
	private MaintenanceProtocol extract(boolean update) {
		final MaintenanceProtocol maintenanceProtocol = new MaintenanceProtocol();
		if (update) {
			maintenanceProtocol.setId(this.updateProtocol.getId());
		}

		if (nameItem.getValue() != null) {
			maintenanceProtocol.setName(nameItem.getValueAsString());
		}
		if (descriptionItem.getValue() != null) {
			maintenanceProtocol.setDescription(descriptionItem
					.getValueAsString());
		}

		Set<ConstraintViolation<MaintenanceProtocol>> violations = validator
				.validate(maintenanceProtocol);

		if (form.validate() && violations.isEmpty())
			return maintenanceProtocol;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaintenanceProtocol>> it = violations
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
			GHANotification.alert(violationsList);
		}
		return null;
	}

	public void update() {
		MaintenanceProtocol maintenanceProtocol = extract(true);

		// if validation fails
		if (maintenanceProtocol == null)
			return;

		MaintenanceProtocolModel.update(maintenanceProtocol,
				new GHAAsyncCallback<MaintenanceProtocol>() {

					@Override
					public void onSuccess(MaintenanceProtocol result) {
						notifyMaintenanceProtocol(result);
					}

				});
	}

	public void activateForm(boolean activate) {
		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
	}

	public void setMaintenanceProtocol(MaintenanceProtocol maintenanceProtocol) {
		this.updateProtocol = maintenanceProtocol;
		nameItem.setValue(maintenanceProtocol.getName());
		descriptionItem.setValue(maintenanceProtocol.getDescription());
	}

	// Producer stuff
	private void notifyMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) {
		GHANotification.alertMessage("mprot-save-success");
		for (MaintenanceProtocolSelectionListener listener : listeners) {
			listener.select(maintenanceProtocol);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionProducer
	 * #addMaintenanceProtocolSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocol.MaintenanceProtocolSelectionListener)
	 */
	@Override
	public void addMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener) {
		listeners.add(maintenanceProtocolSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionProducer
	 * #removeMaintenanceProtocolSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocol.MaintenanceProtocolSelectionListener)
	 */
	@Override
	public void removeMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener) {
		listeners.remove(maintenanceProtocolSelectionListener);
	}

}
