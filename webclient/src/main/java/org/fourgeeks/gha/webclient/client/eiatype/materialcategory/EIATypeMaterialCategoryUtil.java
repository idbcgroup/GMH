package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialCategoryUtil {

	private EIATypeMaterialCategoryUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param eiaTypeMaterial
	 * @return an EIATypeMaterialRecord that represents this eiatYpeMaterial
	 */
	public static EIATypeMaterialCategoryRecord toGridRecord(
			EiaTypeMaterialCategory eiaTypeMaterial) {
		return new EIATypeMaterialCategoryRecord(eiaTypeMaterial);
	}

	/**
	 * @param eiaTypeMaterials
	 * @return a List of EiaTypeMaterialRecord
	 */
	public static List<EIATypeMaterialCategoryRecord> toGridRecords(
			List<EiaTypeMaterialCategory> eiaTypeMaterials) {
		List<EIATypeMaterialCategoryRecord> list = new ArrayList<EIATypeMaterialCategoryRecord>();
		for (EiaTypeMaterialCategory eiaTypeMaterial : eiaTypeMaterials)
			list.add(toGridRecord(eiaTypeMaterial));
		return list;
	}

}
