package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class EiaTypeMaterialCategoryGrid extends
		GhaGrid<EiaTypeMaterialCategory> {

	public EiaTypeMaterialCategoryGrid() {
		setEmptyMessage("No existen Materiales para mostrar");

		GHAGridField codeGridField = new GHAGridField("code",
				GHAStrings.get("code"));
		GHAGridField nameGridField = new GHAGridField("name",
				GHAStrings.get("name"));
		GHAGridField descriptionGridField = new GHAGridField("description",
				GHAStrings.get("description"));
		GHAGridField modelGridField = new GHAGridField("model",
				GHAStrings.get("model"));
		GHAGridField extCodeGridField = new GHAGridField("extCode",
				GHAStrings.get("external-code"));
		GHAGridField typeGridField = new GHAGridField("type",
				GHAStrings.get("type"));

		setFields(codeGridField, nameGridField, descriptionGridField,
				modelGridField, extCodeGridField, typeGridField);
	}

}
