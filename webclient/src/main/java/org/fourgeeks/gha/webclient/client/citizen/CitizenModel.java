package org.fourgeeks.gha.webclient.client.citizen;

import java.util.List;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class CitizenModel {
	private static final GWTCitizenServiceAsync service = GWT
			.create(GWTCitizenService.class);

	/**
	 * @param entities
	 * @param callback
	 */
	public static void delete(List<Citizen> entities,
			GHAAsyncCallback<Void> callback) {
		service.delete(entities, callback);
	}

	/**
	 * @param citizen
	 *            the ssoUser base to find
	 * @param callback
	 */
	public static void find(Citizen citizen,
			GHAAsyncCallback<List<Citizen>> callback) {
		service.find(citizen, callback);
	}

	/**
	 * @param id
	 *            the id of the citizen
	 * @param callback
	 */
	public static void find(long id, GHAAsyncCallback<Citizen> callback) {
		service.find(id, callback);
	}

	/**
	 * @param citizen
	 * @param callback
	 */
	public static void save(Citizen citizen, GHAAsyncCallback<Citizen> callback) {
		service.save(citizen, callback);
	}

	// /**
	// * @param entities
	// * @param callback
	// */
	// public static void delete(long Id, GHAAsyncCallback<Void> callback) {
	// ssoUservice.delete(Id, callback);
	// }
	//
	/**
	 * @param citizen
	 * @param callback
	 */
	public static void update(Citizen citizen,
			GHAAsyncCallback<Citizen> callback) {
		service.update(citizen, callback);
	}

	private CitizenModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

}