package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;

/**
 * @author alacret Utilities for MaterialCategories
 */
public class MaterialCategoryUtil {

	private MaterialCategoryUtil() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}

	/**
	 * Converts a Material into a MaterialRecord
	 * 
	 * @param material
	 * @return the MaterialRecord that represents the Material
	 */
	public static MaterialCategoryRecord toGridRecord(
			ServicesResourceCategory servicesResourceCategory) {
		return new MaterialCategoryRecord(servicesResourceCategory);
	}

	/**
	 * Converts a Material list into a MaterialRecord list
	 * 
	 * @param materialsCategories
	 * @return the MaterialRecord list that represents the Material list
	 */
	public static List<MaterialCategoryRecord> toGridRecords(
			List<ServicesResourceCategory> materialsCategories) {
		List<MaterialCategoryRecord> list = new ArrayList<MaterialCategoryRecord>();
		for (ServicesResourceCategory servicesResourceCategory : materialsCategories)
			list.add(new MaterialCategoryRecord(servicesResourceCategory));
		return list;
	}

}
