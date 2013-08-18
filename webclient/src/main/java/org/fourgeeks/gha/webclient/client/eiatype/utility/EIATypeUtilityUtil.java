package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentRecord;

/**
 * @author sizturriaga
 * 
 */
public class EIATypeUtilityUtil {
	
	private EIATypeUtilityUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}
	
	public static EIATypeUtilityRecord toGridRecord(EiaTypeUtility eiaTypeUtility) {
		return new EIATypeUtilityRecord(eiaTypeUtility);
	}
	
	public static List<EIATypeUtilityRecord> toGridRecords(List<EiaTypeUtility> eiaTypeUtilityList) {
		
		List<EIATypeUtilityRecord> list = new ArrayList<EIATypeUtilityRecord>();
		for (EiaTypeUtility eiaTypeUtility : eiaTypeUtilityList)
			list.add(toGridRecord(eiaTypeUtility));
		
		return list;
	}

}
