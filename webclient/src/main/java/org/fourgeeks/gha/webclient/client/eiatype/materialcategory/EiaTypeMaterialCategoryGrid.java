package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

/**
 * @author alacret, emiliot
 * 
 */
public class EiaTypeMaterialCategoryGrid extends
		GhaGrid<EiaTypeMaterialCategory> {
	private GHAGridField codeGridField;
	private GHAGridField nameGridField;
	private GHAGridField descriptionGridField;
	private GHAGridField modelGridField;
	private GHAGridField extCodeGridField;
	private GHAGridField typeGridField;
	private GHAGridField amountGridField;

	{
		codeGridField = new GHAGridField("code", GHAStrings.get("code"));
		nameGridField = new GHAGridField("name", GHAStrings.get("name"));
		descriptionGridField = new GHAGridField("description",
				GHAStrings.get("description"));
		modelGridField = new GHAGridField("model", GHAStrings.get("model"));
		extCodeGridField = new GHAGridField("extCode",
				GHAStrings.get("external-code"));
		typeGridField = new GHAGridField("type", GHAStrings.get("type"));
		amountGridField = new GHAGridField("amount", GHAStrings.get("amount"));
		amountGridField.setCanEdit(true);
		amountGridField.setType(ListGridFieldType.INTEGER);
	}

	public EiaTypeMaterialCategoryGrid() {
		setEmptyMessage(GHAStrings.get("empty-materialcategory-grid"));

		setFields(codeGridField, nameGridField, descriptionGridField,
				modelGridField, extCodeGridField, typeGridField,
				amountGridField);
	}

	/**
	 * @return the amountGridField
	 */
	public GHAGridField getAmountGridField() {
		return amountGridField;
	}

}
