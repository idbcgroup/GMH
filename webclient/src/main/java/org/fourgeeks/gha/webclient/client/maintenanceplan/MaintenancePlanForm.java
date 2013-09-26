/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

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
	private GHASelectItem periodOfTime;
	private Validator validator;
	
	{
		nameItem = new GHATextItem("Name", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		frequencyItem = new GHATextItem("Frecuencia", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		periodOfTime = new GHASelectItem("Periodo de Tiempo", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenancePlanSelectionListener>();
	}
	
	public MaintenancePlanForm(){
		final HLayout mainPanel = new HLayout();
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		// form.setCellPadding(1);
		form.setNumCols(3);
		form.setItems(nameItem, frequencyItem, periodOfTime,new GHASpacerItem(),
				  descriptionItem);
		
		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
		fill();
	}
	
	/**
	 * Fill the form items that require to be filled
	 */
	private void fill() {
		// TODO Auto-generated method stub
	}
	/**
	 * Clear the form
	 */
	public void cancel(){
		// TODO Auto-generated method stub
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
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer#addMaintenancePlanSelectionListener(org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer#removeMaintenancePlanSelectionListener(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

}
