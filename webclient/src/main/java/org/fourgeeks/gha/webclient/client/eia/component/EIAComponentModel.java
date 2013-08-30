package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;


/**
 * @author sizturriaga
 * 
 */
public class EIAComponentModel {
	
	public static final GWTEiaComponentServiceAsync service = 
			GWT.create(GWTEiaComponentService.class);
	
	private EIAComponentModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}
	
	public static void delete(long id,
			GHAAsyncCallback<Void> callback) {
		Window.alert("delete 1");
		service.delete(id, callback);
		Window.alert("delete 2");
	}

	public static void find(Eia eia,
			GHAAsyncCallback<List<EiaComponent>> callback) {
		Window.alert("find 1");
		service.find(eia, callback);
		Window.alert("find 2");
	}
	
	public static void findByEiaId(Eia eia, GHAAsyncCallback<List<EiaComponent>> callback) {
		
		Window.alert("en findByEiaId");
		Window.alert("findByEiaId eia: "+eia.getId());
		
		
		service.findByEiaId(eia.getId(), callback);
	}
	
	public static void save(EiaComponent eiaComponent,
			GHAAsyncCallback<EiaComponent> callback) {
		Window.alert("save 1");
		service.save(eiaComponent, callback);
		Window.alert("save 2");
	}

	public static void update(EiaComponent eiaComponent,
			GHAAsyncCallback<EiaComponent> callback) {
		service.update(eiaComponent, callback);
	}
}
