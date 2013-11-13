package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.grids.GHADetailViewerField;

import com.smartgwt.client.widgets.viewer.DetailViewer;

public class EIADetailViewer extends DetailViewer {

	public EIADetailViewer() {
		setWidth100();
		setMinWidth(1024);
		setCellStyle("detail-gha");
		setLabelStyle("detailLabel-gha");

		GHADetailViewerField obuGridField = new GHADetailViewerField("obu",
				"Dept. Responsable");
		GHADetailViewerField roleGridField = new GHADetailViewerField("role",
				"Rol Responsable");

		GHADetailViewerField typeFiled = new GHADetailViewerField("type",
				"Tipo de equipo");
		GHADetailViewerField brandFiled = new GHADetailViewerField("brand",
				"Marca");
		GHADetailViewerField modelFiled = new GHADetailViewerField("model",
				"Modelo");

		setFields(obuGridField, roleGridField, typeFiled, brandFiled,
				modelFiled);
	}
}
