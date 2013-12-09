package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author alacret
 * 
 */
public class EIATypeUtil {

	private EIATypeUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param eiaType
	 * @return the grid record represented by this entity
	 */
	public static EIATypeRecord toGridRecord(EiaType eiaType) {
		return new EIATypeRecord(eiaType);
	}

	/**
	 * @param eiaTypes
	 * @return alist of grid records
	 */
	public static List<EIATypeRecord> toGridRecords(List<EiaType> eiaTypes) {
		List<EIATypeRecord> list = new ArrayList<EIATypeRecord>();
		for (EiaType eiaType : eiaTypes)
			list.add(new EIATypeRecord(eiaType));
		return list;
	}

	/**
	 * @param eiaTypes
	 * @param blackEiaType
	 * @return a list of eiatyperecords
	 */
	public static List<EIATypeRecord> toGridRecords(List<EiaType> eiaTypes,
			EiaType blackEiaType) {
		List<EIATypeRecord> list = new ArrayList<EIATypeRecord>();
		for (EiaType eiaType : eiaTypes) {
			if (!eiaType.getCode().equals(blackEiaType.getCode()))
				list.add(new EIATypeRecord(eiaType));
		}
		return list;
	}

}