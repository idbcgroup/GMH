package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author sizturriaga, emiliot
 * 
 */
public class EIATypeUtilityModel {

	private static final GWTEiaTypeUtilityServiceAsync service = GWT
			.create(GWTEiaTypeUtilityService.class);

	EIATypeUtilityModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	public static void findByEiaType(EiaType eiaType,
			AsyncCallback<List<EiaTypeUtility>> callback) {
		service.findByEiaType(eiaType, callback);
	}

	public static void save(EiaTypeUtility eiaTypeUtility,
			GHAAsyncCallback<EiaTypeUtility> callback) {
		service.save(eiaTypeUtility, callback);
	}

	public static void update(EiaTypeUtility eiaTypeUtility,
			GHAAsyncCallback<EiaTypeUtility> callback) {
		service.update(eiaTypeUtility, callback);
	}

}
