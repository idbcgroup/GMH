package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class EiaDamageReportGrid extends GhaGrid<GlaLog> {

	public EiaDamageReportGrid() {
		setEmptyMessage("No existen Equipos Dañados o con Fallas para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");
		GHAGridField faiGridField = new GHAGridField("fai", "Id Activo Fijo");
		GHAGridField locationGridField = new GHAGridField("location",
				"Ubicación");
		GHAGridField bpiGridField = new GHAGridField("bpi", "Institución");
		GHAGridField statusGridField = new GHAGridField("state", "Estado");

		GHAGridField damageReportIdGridField = new GHAGridField("reportId",
				"No. orden");

		setFields(idGridField, serialGridField, faiGridField,
				locationGridField, bpiGridField, statusGridField,
				damageReportIdGridField);
	}
}
