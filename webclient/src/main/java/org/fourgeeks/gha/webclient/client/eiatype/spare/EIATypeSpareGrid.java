package org.fourgeeks.gha.webclient.client.eiatype.spare;

import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

public class EIATypeSpareGrid extends GhaGrid<EiaTypeSpare>{

	public EIATypeSpareGrid() {
		setEmptyMessage("No existen repuestos para mostrar.");
		
		setFields(new GHAGridField("id", "No"),
				new GHAGridField("code", "Codigo"),
				new GHAGridField("name", "Nombre"),
				new GHAGridField("brand", "Marca"),
				new GHAGridField("model", "Modelo"),
				new GHAGridField("use", "Uso")
		);
	}
}
