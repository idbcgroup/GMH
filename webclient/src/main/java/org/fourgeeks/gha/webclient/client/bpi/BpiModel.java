/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpi;

import java.util.List;

import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 *
 */
public class BpiModel {
	private static final GWTBpiServiceAsync service = GWT.create(GWTBpiService.class);
	
	private BpiModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}
	
	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Bpi>> callback) {
		service.getAll(callback);
	}
}
