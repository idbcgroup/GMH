/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

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
public class MaintenanceActivityForm extends VLayout implements
		MaintenanceActivitySelectionProducer {
	private List<MaintenanceActivitySelectionListener> listeners;
	private GHATextItem nameItem, descriptionItem;
	private Validator validator;
	
	{
		listeners = new ArrayList<MaintenanceActivitySelectionListener>();
		nameItem = new GHATextItem("Nombre de la Actividad", 150);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	public MaintenanceActivityForm(){
		final HLayout mainPanel = new HLayout();
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(nameItem, new GHASpacerItem(3),
			          descriptionItem);
		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
		fill();
	}
	
	/**
	 * 
	 */
	private void fill() {
		// TODO Auto-generated method stub
		
	}
	
	public void cancel(){
		//TODO:
	}

	/**
	 * 
	 */
	public void save(){
		// TODO Auto-generated method stub
	}
	
	/**
	 * 
	 */
	public void update(){
		// TODO Auto-generated method stub
	}
	
	/**
	 * @param activate
	 */
	public void activateForm(boolean activate){
		
	}
	
	//Producer Stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer#addMaintenanceActivitySelectionListener(org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer#removeMaintenanceActivitySelectionListener(org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		// TODO Auto-generated method stub
		
	}

}
