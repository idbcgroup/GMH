package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeHandler;

public class EIAComponentGrid extends GhaGrid<Eia> implements ResizeHandler {

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
}