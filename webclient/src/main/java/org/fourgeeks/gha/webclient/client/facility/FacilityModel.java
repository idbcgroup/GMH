package org.fourgeeks.gha.webclient.client.facility;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author vivi.torresg
 * 
 */

public class FacilityModel {
	private static final GWTFacilityServiceAsync service = GWT
			.create(GWTFacilityService.class);

	private FacilityModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Facility>> callback) {
		service.getAll(callback);
	}
}
