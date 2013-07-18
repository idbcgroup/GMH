package org.fourgeeks.gha.webclient.client.buildinglocation;

import java.util.List;

import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class BuildingLocationModel {
	private static final GWTBuildingLocationServiceAsync service = GWT
			.create(GWTBuildingLocationService.class);

	private BuildingLocationModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void getAll(GHAAsyncCallback<List<BuildingLocation>> callback) {
		service.getAll(callback);
	}

}
