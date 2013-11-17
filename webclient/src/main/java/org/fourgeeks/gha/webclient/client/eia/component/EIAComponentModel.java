package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author sizturriaga
 * 
 */
public class EIAComponentModel {

	public static final GWTEiaComponentServiceAsync service = GWT
			.create(GWTEiaComponentService.class);

	private EIAComponentModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	public static void delete(List<EiaComponent> eiaComponents,
			GHAAsyncCallback<Void> callback) {
		service.delete(eiaComponents, callback);
	}

	public static void find(Eia eia, AsyncCallback<EiaComponent> callback) {
		service.find(eia.getId(), callback);
	}

	public static void findByEiaId(Eia eia,
			AsyncCallback<List<EiaComponent>> callback) {
		service.findByParentEia(eia, callback);
	}

	public static void save(EiaComponent eiaComponent,
			GHAAsyncCallback<EiaComponent> callback) {
		service.save(eiaComponent, callback);
	}

	public static void update(EiaComponent eiaComponent,
			GHAAsyncCallback<EiaComponent> callback) {
		service.update(eiaComponent, callback);
	}
}
