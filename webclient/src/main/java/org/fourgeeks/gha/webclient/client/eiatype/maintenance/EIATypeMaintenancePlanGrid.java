package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIATypeMaintenancePlanGrid extends ListGrid implements ResizeHandler{

	@Override
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  
		  
        String fieldName = this.getFieldName(colNum);  
        
        if (fieldName.equals("nParte")) {
        	DynamicForm recordform = new DynamicForm();
        	final GHATextItem textbox = new GHATextItem();
            recordform.setItems(textbox);
        	
            textbox.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					// TODO Auto-generated method stub
                    SC.say("Textbox value changed:"+textbox.getValue());  
				}
			});  
            return recordform;  
        } else {  
            return null;  
        }  

    }
	
	public EIATypeMaintenancePlanGrid() {
		GHAUiHelper.addResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getGridSize(60));
		setEmptyMessage("No existe material para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
//		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField typeMantGridField = new ListGridField("typeMant", "Tipo Mantenimiento");
		typeMantGridField.setAlign(Alignment.CENTER);
		
		ListGridField descriptionGridField = new ListGridField("description", "Descripción");
		descriptionGridField.setAlign(Alignment.CENTER);
		
		ListGridField freqGridField = new ListGridField("freq", "Frecuencia");
		freqGridField.setAlign(Alignment.CENTER);
		
		ListGridField usesGridField = new ListGridField("uses", "Usos");
		usesGridField.setAlign(Alignment.CENTER);
		
		ListGridField protocolGridField = new ListGridField("protocol", "Protocolo");
		protocolGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, typeMantGridField, descriptionGridField, freqGridField, usesGridField, protocolGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getGridSize(60));
	}

}
