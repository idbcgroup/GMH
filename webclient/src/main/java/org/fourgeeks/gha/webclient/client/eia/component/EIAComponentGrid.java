package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

public class EIAComponentGrid extends GhaGrid<EiaComponent> {
	
	private GHAGridField componentobsField;
	
	{
		componentobsField = new GHAGridField("componentobs", "Observaciones");
		componentobsField.setType(ListGridFieldType.TEXT);
		componentobsField.setCanEdit(true);
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

		 	
		setFields(idGridField, codeGridField, nameGridField, 
				  brandGridField,modelGridField, componentobsField);
	}


	public GHAGridField getComponentobsField() {
		return componentobsField;
	}


	public void setComponentobsField(GHAGridField componentobsField) {
		this.componentobsField = componentobsField;
	}
	
}