package org.fourgeeks.gha.webclient.client.eia.spares;

import org.fourgeeks.gha.domain.gmh.EiaSpare;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class EIASpareGrid extends GhaGrid<EiaSpare>{

	public EIASpareGrid() {
	////init
		super();
		setEmptyMessage("No existen repuestos para mostrar.");
	/////////
		
		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codRepGridField = new GHAGridField("codRep", "Cód. Repuesto");
		GHAGridField nameGridField = new GHAGridField("nameRepuesto", "Nombre Repuesto");
		GHAGridField brandGridField = new GHAGridField("Brand", "Marca");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField usoRepuestoGridField = new GHAGridField("usoRepuesto", "Uso del Repuesto");
		
		setFields(idGridField, codRepGridField, nameGridField,
				  brandGridField,modelGridField, usoRepuestoGridField);
	}

}
