package org.fourgeeks.gha.webclient.client.obu;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author naramirez
 */
public class BspModel {
	private static final GWTBspServiceAsync service = GWT
			.create(GWTBspService.class);

	/**
	 * @param Id
	 * @param callback
	 */
	public static void delete(long Id, GHAAsyncCallback<Void> callback) {
		service.delete(Id, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 *            the list of Job
	 */
	public static void find(Bsp entity, GHAAsyncCallback<List<Bsp>> callback) {
		service.find(entity, callback);
	}

	/**
	 * @param Id
	 * @param callback
	 *            the Job
	 */
	public static void find(long Id, GHAAsyncCallback<Bsp> callback) {
		service.find(Id, callback);
	}

	/**
	 * @param callback
	 *            the list of jobs
	 */
	public static void getAll(GHAAsyncCallback<List<Bsp>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param entity
	 * @param callback
	 *            the saved Job
	 */
	public static void save(Bsp entity, GHAAsyncCallback<Bsp> callback) {
		service.save(entity, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 *            the updated Job
	 */
	public static void update(Bsp entity, GHAAsyncCallback<Bsp> callback) {
		service.update(entity, callback);
	}
}
