package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialUtil {

	private EIATypeMaterialUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param eiaTypeMaterial
	 * @return an EIATypeMaterialRecord that represents this eiatYpeMaterial
	 */
	public static EIATypeMaterialRecord toGridRecord(
			EiaTypeMaterial eiaTypeMaterial) {
		return new EIATypeMaterialRecord(eiaTypeMaterial);
	}

	/**
	 * @param eiaTypeMaterials
	 * @return a List of EiaTypeMaterialRecord
	 */
	public static List<EIATypeMaterialRecord> toGridRecords(
			List<EiaTypeMaterial> eiaTypeMaterials) {
		List<EIATypeMaterialRecord> list = new ArrayList<EIATypeMaterialRecord>();
		for (EiaTypeMaterial eiaTypeMaterial : eiaTypeMaterials)
			list.add(toGridRecord(eiaTypeMaterial));
		return list;
	}

}
