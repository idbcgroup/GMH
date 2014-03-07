package org.fourgeeks.gha.webclient.client.eiatype.materialbrand;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialUtil {
	private EIATypeMaterialUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param eiaTypeMaterialBrand
	 * @return an EIATypeMaterialRecord that represents this eiatTypeMaterial
	 */
	public static EIATypeMateriaBrandRecord toGridRecord(
			EiaTypeMaterialBrand eiaTypeMaterialBrand) {
		return new EIATypeMateriaBrandRecord(eiaTypeMaterialBrand);
	}

	/**
	 * @param eiaTypeMaterials
	 * @return a List of EiaTypeMaterialRecord
	 */
	public static List<EIATypeMateriaBrandRecord> toGridRecords(
			List<EiaTypeMaterialBrand> eiaTypeMaterials) {
		List<EIATypeMateriaBrandRecord> list = new ArrayList<EIATypeMateriaBrandRecord>();
		for (EiaTypeMaterialBrand entity : eiaTypeMaterials)
			list.add(toGridRecord(entity));
		return list;
	}
}
