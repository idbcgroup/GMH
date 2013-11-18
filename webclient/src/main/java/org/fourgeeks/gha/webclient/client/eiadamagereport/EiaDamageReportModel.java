package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EiaDamageReportModel {
	private static final GWTEiaDamageReportServiceAsync service = GWT
			.create(GWTEiaDamageReportService.class);

	public void delete(long Id, GHAAsyncCallback<Boolean> callback) {
		service.delete(Id, callback);
	}

	public void findByEiaType(EiaType eiaType,
			GHAAsyncCallback<List<EiaDamageReport>> callback) {
		service.findByEiaType(eiaType, callback);
	}

	public void getAll(GHAAsyncCallback<List<EiaDamageReport>> callback) {
		service.getAll(callback);
	}

	public void save(EiaDamageReport eiaDamageReport,
			GHAAsyncCallback<EiaDamageReport> callback) throws GHAEJBException {
		service.save(eiaDamageReport, callback);
	}

	public void update(EiaDamageReport eiaDamageReport,
			GHAAsyncCallback<EiaDamageReport> callback) throws GHAEJBException {
		service.update(eiaDamageReport, callback);
	}
}
