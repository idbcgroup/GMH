/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
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
public class MaintenanceProtocolForm extends VLayout implements
		MaintenanceProtocolSelectionProducer {
	private GHATextItem nameItem, descriptionItem;
	// TODO: Definir como se va a seleccionar el plan de mantenimiento
	private List<MaintenanceProtocolSelectionListener> listeners;
	private Validator validator;

	{
		listeners = new ArrayList<MaintenanceProtocolSelectionListener>();
		nameItem = new GHATextItem("Nombre del Protocolo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 
	 */
	public MaintenanceProtocolForm() {
		final HLayout mainPanel = new HLayout();

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setItems(nameItem, descriptionItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
		fill();
	}

	private void fill() {
		//TODO
	}

	public void cancel() {
		//TODO
	}

	public void save() {
		//TODO
	}

	public void update() {
		//TODO
	}

	public void activateForm(boolean activate) {
		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
	}

	// Producer stuff
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
