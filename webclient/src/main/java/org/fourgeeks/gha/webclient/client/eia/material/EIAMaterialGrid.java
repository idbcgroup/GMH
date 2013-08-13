package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

public class EIAMaterialGrid extends GhaGrid<Material> implements ResizeHandler {

	public EIAMaterialGrid() {
		GHAUiHelper.addGHAResizeHandler(this);

		setWidth100();
		setHeight(GHAUiHelper.getGridSize(30));
		setEmptyMessage("No existe material para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		// setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField codMatGridField = new ListGridField("codMat",
				"Cód. Material");
		codMatGridField.setAlign(Alignment.CENTER);

		ListGridField nameGridField = new ListGridField("nameMaterial",
				"Nombre Material");
		nameGridField.setAlign(Alignment.CENTER);

		ListGridField usoMaterialGridField = new ListGridField("usoMaterial",
				"Uso del Material");
		usoMaterialGridField.setAlign(Alignment.CENTER);

		ListGridField typeGridField = new ListGridField("type", "Tipo");
		typeGridField.setAlign(Alignment.CENTER);

		ListGridField cantGridField = new ListGridField("cant", "Cantidad");
		cantGridField.setAlign(Alignment.CENTER);

		ListGridField facilityGridField = new ListGridField("facility",
				"Ubicación");
		facilityGridField.setAlign(Alignment.CENTER);

		ListGridField freqGridField = new ListGridField("freq", "Frecuencia");
		freqGridField.setAlign(Alignment.CENTER);

		ListGridField usesGridField = new ListGridField("uses", "Cant. Usos");
		usesGridField.setAlign(Alignment.CENTER);

		ListGridField availableGridField = new ListGridField("available",
				"Disponibles");
		availableGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, codMatGridField, nameGridField,
				usoMaterialGridField, typeGridField, cantGridField,
				facilityGridField, freqGridField, usesGridField,
				availableGridField);
	}

}