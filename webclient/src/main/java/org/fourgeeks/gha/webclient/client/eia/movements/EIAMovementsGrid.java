package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.EiaSpare;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

public class EIAMovementsGrid extends GhaGrid<EiaSpare>{

	public EIAMovementsGrid() {
	////init
		super();
		setEmptyMessage("No existen movimientos para mostrar.");
	/////////
		
		GHAGridField idGridField = new GHAGridField("id", "No");
		
		setFields(idGridField);
	}

}
