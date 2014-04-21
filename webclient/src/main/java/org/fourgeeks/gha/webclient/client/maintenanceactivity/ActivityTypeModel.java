package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.List;

import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author naramirez
 */
public class ActivityTypeModel {
	private static final GWTActivityTypeServiceAsync service = GWT
			.create(GWTActivityTypeService.class);

	private ActivityTypeModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param id
	 * @param callback
	 */
	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	/**
	 * @param entities
	 * @param callback
	 */
	public static void delete(List<ActivityType> entities,
			GHAAsyncCallback<Void> callback) {
		delete(entities, callback);
	}

	/**
	 * @param callback
	 *            A list with all the Activity Types that dont have parents
	 */
	public static void getAllTypes(GHAAsyncCallback<List<ActivityType>> callback) {
		service.getAllTypes(callback);
	}

	/**
	 * @param callback
	 *            A list with all the Activity Types and Sub Types
	 */
	public static void getAll(GHAAsyncCallback<List<ActivityType>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param type
	 *            the Activity Type
	 * @param callback
	 *            A list of Activity Subtypes for the given type
	 */
	public static void getSubTypes(ActivityType type,
			GHAAsyncCallback<List<ActivityType>> callback) {
		service.getSubTypes(type, callback);
	}

	/**
	 * @param entity
	 *            the Activity Type or Subtype to save
	 * @param callback
	 *            the saved Activity Type or Subtype
	 */
	public static void save(ActivityType entity,
			GHAAsyncCallback<ActivityType> callback) {
		service.save(entity, callback);
	}

}
