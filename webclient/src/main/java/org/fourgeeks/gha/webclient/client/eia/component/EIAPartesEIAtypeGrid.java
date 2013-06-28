package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIAPartesEIAtypeGrid extends ListGrid {

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
	
	public EIAPartesEIAtypeGrid() {
		setWidth100();
		setHeight("300px");
		setEmptyMessage("No existen partes de EIAtype para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField partnumGridField = new ListGridField("nParte", "No Parte EIA");
		partnumGridField.setAlign(Alignment.CENTER);
		
		ListGridField nameGridField = new ListGridField("nameParte", "Nombre Parte");
		nameGridField.setAlign(Alignment.CENTER);
		
		ListGridField usoparteGridField = new ListGridField("usoParte", "Uso Parte");
		usoparteGridField.setAlign(Alignment.CENTER);
		
		ListGridField typeGridField = new ListGridField("type", "Tipo");
		typeGridField.setAlign(Alignment.CENTER);

		ListGridField requiredGridField = new ListGridField("req", "Requerido");
		requiredGridField.setAlign(Alignment.CENTER);
		
		ListGridField sustGridField = new ListGridField("sust", "Sustituible");
		sustGridField.setAlign(Alignment.CENTER);
		
		ListGridField cantGridField = new ListGridField("cant", "Cantidad");
		cantGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, partnumGridField, nameGridField, usoparteGridField, typeGridField, requiredGridField, sustGridField, cantGridField);
	}

}
