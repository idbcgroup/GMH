package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author sizturriaga
 * 
 */
public class EIAComponentUtil {

	private EIAComponentUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}
	
	public static EIAComponentRecord toGridRecord(EiaComponent eiaComponent) {
		return new EIAComponentRecord(eiaComponent);
	}

	public static List<EIAComponentRecord> toGridRecords(List<EiaComponent> eiaComponents) {
		List<EIAComponentRecord> list = new ArrayList<EIAComponentRecord>();
		for (EiaComponent eiaComponent : eiaComponents)
			list.add(toGridRecord(eiaComponent));
		return list;
	}

}
