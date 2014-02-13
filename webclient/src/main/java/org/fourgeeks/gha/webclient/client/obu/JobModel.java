package org.fourgeeks.gha.webclient.client.obu;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author naramirez
 */
public class JobModel {
	private static final GWTJobServiceAsync service = GWT
			.create(GWTJobService.class);

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
	public static void find(Job entity, GHAAsyncCallback<List<Job>> callback) {
		service.find(entity, callback);
	}

	/**
	 * @param Id
	 * @param callback
	 *            the Job
	 */
	public static void find(long Id, GHAAsyncCallback<Job> callback) {
		service.find(Id, callback);
	}

	/**
	 * @param callback
	 *            the list of jobs
	 */
	public static void getAll(GHAAsyncCallback<List<Job>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param entity
	 * @param callback
	 *            the saved Job
	 */
	public static void save(Job entity, GHAAsyncCallback<Job> callback) {
		service.save(entity, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 *            the updated Job
	 */
	public static void update(Job entity, GHAAsyncCallback<Job> callback) {
		service.update(entity, callback);
	}
}
