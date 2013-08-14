package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeEvent;

public class EIADeprecationGrid extends GhaGrid<Object>{

	public EIADeprecationGrid() {
	////init
		super();
		setHeight(GHAUiHelper.getGridSize(100));
		setEmptyMessage("No existen registros para mostrar.");
	/////////
		
		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField yearGridField = new GHAGridField("year", "Año");
		GHAGridField methodGridField = new GHAGridField("meth", "Método");
		GHAGridField costLibGridField = new GHAGridField("costLib", "Costo Libros");
		GHAGridField currLibGridField = new GHAGridField("currLib", "Moneda");
		GHAGridField deprecGridField = new GHAGridField("deprec", "Depreciación");
		GHAGridField currDeprecGridField = new GHAGridField("currDeprec", "Moneda");
		GHAGridField costDepGridField = new GHAGridField("costDep", "Costo Depreciado");
		GHAGridField currDepGridField = new GHAGridField("currDep", "Moneda");
		GHAGridField dateGridField = new GHAGridField("date", "Fecha");
		GHAGridField respGridField = new GHAGridField("resp", "Responsable");
		GHAGridField motiveGridField = new GHAGridField("motive", "Motivo");
		
		setFields(idGridField, yearGridField,methodGridField,
				  costLibGridField,currLibGridField,deprecGridField,
				  currDeprecGridField,costDepGridField,currDepGridField,
				  dateGridField,respGridField,motiveGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeight(GHAUiHelper.getGridSize(100));
	}
}
