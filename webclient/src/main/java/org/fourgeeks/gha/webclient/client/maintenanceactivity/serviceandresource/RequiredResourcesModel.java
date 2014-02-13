package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import java.util.List;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * 
 * @author caparicio
 * 
 */
public class RequiredResourcesModel {
	private static final GWTRequiredResourcesServiceAsync service = GWT
			.create(GWTRequiredResourcesService.class);

	private RequiredResourcesModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * 
	 * @param requiredResources
	 * @param callback
	 */
	public static void save(RequiredResources requiredResources,
			GHAAsyncCallback<RequiredResources> callback) {
		service.save(requiredResources, callback);
	}

	/**
	 * 
	 * @param requiredResources
	 * @param callback
	 */
	public static void update(RequiredResources requiredResources,
			GHAAsyncCallback<RequiredResources> callback) {
		service.update(requiredResources, callback);
	}

	/**
	 * 
	 * @param id
	 * @param callback
	 */
	public static void delete(Long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	/**
	 * Delete a list of RequiredResources from database
	 * 
	 * @param entities
	 * @param callback
	 * 
	 */
	public static void delete(List<RequiredResources> entities,
			GHAAsyncCallback<Void> callback) {
		service.delete(entities, callback);
	}

	/**
	 * 
	 * @param resource
	 * @param callback
	 */
	public static void findByServiceAndResource(ServiceAndResource resource,
			GHAAsyncCallback<List<RequiredResources>> callback) {
		service.findByServiceAndResource(resource, callback);
	}

	/**
	 * 
	 * @param activity
	 * @param callback
	 */
	public static void findEiaTypeByActivity(Activity activity,
			GHAAsyncCallback<List<RequiredResources>> callback) {
		service.findEiaTypeByActivity(activity, callback);
	}

	/**
	 * 
	 * @param activity
	 * @param callback
	 */
	public static void findMaterialByActivity(Activity activity,
			GHAAsyncCallback<List<RequiredResources>> callback) {
		service.findMaterialByActivity(activity, callback);
	}
}