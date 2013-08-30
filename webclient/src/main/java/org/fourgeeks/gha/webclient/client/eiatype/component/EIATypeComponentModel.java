package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EIATypeComponentModel {

	private static final GWTEiaTypeComponentServiceAsync service = GWT
			.create(GWTEiaTypeComponentService.class);
	
	private EIATypeComponentModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}
	
	public static void delete(long id,
			GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}
	
	public static void findByParentEiaType(EiaType eiaType,
			GHAAsyncCallback<List<EiaTypeComponent>> callback) {
		service.findByParentEiaType(eiaType, callback);
	}
	
	public static void save(EiaTypeComponent eiaTypeComponent,
			GHAAsyncCallback<EiaTypeComponent> callback) {
		service.save(eiaTypeComponent, callback);
	}
	
	public static void update(EiaTypeComponent eiaTypeComponent,
			GHAAsyncCallback<EiaTypeComponent> callback) {
		service.update(eiaTypeComponent, callback);
	}
	
}
