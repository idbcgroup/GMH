package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

public class EIATypeComponentUtil {

	private EIATypeComponentUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIATypeComponentRecord toGridRecord(EiaTypeComponent eiaTypeComponent) {
		return new EIATypeComponentRecord(eiaTypeComponent);
	}

	public static List<EIATypeComponentRecord> toGridRecords(List<EiaTypeComponent> eiaTypeComponents) {
		List<EIATypeComponentRecord> list = new ArrayList<EIATypeComponentRecord>();
		for (EiaTypeComponent eiaTypeComponent : eiaTypeComponents)
			list.add(toGridRecord(eiaTypeComponent));
		return list;
	}

}