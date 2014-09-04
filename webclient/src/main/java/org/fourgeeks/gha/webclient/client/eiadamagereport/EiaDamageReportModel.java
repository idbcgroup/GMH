package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EiaDamageReportModel {
	private static final GWTEiaDamageReportServiceAsync service = GWT
			.create(GWTEiaDamageReportService.class);

	public static void delete(long Id, GHAAsyncCallback<Boolean> callback) {
		service.delete(Id, callback);
	}

	public static void findByEiaType(EiaType eiaType,
			GHAAsyncCallback<List<GlaLog>> callback) {
		service.findByEiaType(eiaType, callback);
	}

	public static void getAll(GHAAsyncCallback<List<GlaLog>> callback) {
		service.getAll(callback);
	}

	public static void save(GlaLog glaLog,
			GHAAsyncCallback<GlaLog> callback) {
		service.save(glaLog, callback);
	}

	public static void update(GlaLog glaLog,
			GHAAsyncCallback<GlaLog> callback) {
		service.update(glaLog, callback);
	}
}
