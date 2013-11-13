package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAGrid extends GhaGrid<Eia> implements ResizeHandler {
	private EIADetailViewer detailViewer;

	/**
	 * 
	 */
	public EIAGrid() {
		setEmptyMessage("No existen Equipos para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");
		GHAGridField faiGridField = new GHAGridField("fai", "Id Activo Fijo");
		GHAGridField locationGridField = new GHAGridField("location",
				"Ubicación");
		GHAGridField bpiGridField = new GHAGridField("bpi", "Institución");
		GHAGridField statusGridField = new GHAGridField("state", "Estado");

		setFields(idGridField, serialGridField, faiGridField,
				locationGridField, bpiGridField, statusGridField);

		detailViewer = new EIADetailViewer();
		detailViewer.setWidth(350);

		setCanHover(true);
		setShowHover(true);
		setShowHoverComponents(true);
	}

	@Override
	protected Canvas getCellHoverComponent(Record record, Integer rowNum,
			Integer colNum) {

		detailViewer.setData(new EIARecord[] { (EIARecord) record });

		return detailViewer;
	}
}
