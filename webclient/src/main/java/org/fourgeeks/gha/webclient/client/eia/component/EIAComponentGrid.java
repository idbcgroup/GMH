package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

public class EIAComponentGrid extends GhaGrid<EiaComponent> {
	
	private GHAGridField requiredField;
	private GHAGridField replaceableField;
	
	{
		requiredField = new GHAGridField("required", "Requerido");
		requiredField.setType(ListGridFieldType.BOOLEAN);
		requiredField.setCanEdit(true);
		replaceableField = new GHAGridField("replaceable", "Reemplazable");
		replaceableField.setCanEdit(true);
		replaceableField.setType(ListGridFieldType.BOOLEAN);
	}


	public EIAComponentGrid() {
	///////init
		super();
		setEmptyMessage("No existen partes de equipos para mostrar.");
	///////////		
		
		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codeGridField = new GHAGridField("eiaCode","Código");
		GHAGridField nameGridField = new GHAGridField("nameParte","Nombre Parte");
		GHAGridField brandGridField = new GHAGridField("brand", "Marca");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField requiredField = new GHAGridField("req", "Requerido");
		GHAGridField replaceableField = new GHAGridField("rep", "Reemplazable");
		 	
		setFields(idGridField, codeGridField, nameGridField, 
				  brandGridField,modelGridField, requiredField,
				  replaceableField);
	}
	
	public GHAGridField getRequiredField() {
		return requiredField;
	}
	
	public GHAGridField getReplaceableField() {
		return replaceableField;
	}

}