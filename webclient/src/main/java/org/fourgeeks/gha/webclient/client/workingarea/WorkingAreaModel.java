package org.fourgeeks.gha.webclient.client.workingarea;

import java.util.List;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author vivi.torresg
 * 
 */

public class WorkingAreaModel {
	private static final GWTWorkingAreaServiceAsync service = GWT
			.create(GWTWorkingAreaService.class);

	private WorkingAreaModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<WorkingArea>> callback) {
		service.getAll(callback);
	}
}
