package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class EIATypePictureModel {

	private static final GWTEiaTypePictureServiceAsync service = GWT
			.create(GWTEiaTypePictureService.class);

	private EIATypePictureModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaTypePicture>> ghaAsyncCallback) {
		service.find(eiaType, ghaAsyncCallback);

	}

	public static void find(long id,
			GHAAsyncCallback<EiaTypePicture> ghaAsyncCallback) {
		service.find(id, ghaAsyncCallback);

	}
	
	public static void save(EiaType eiaType, GHAAsyncCallback<Void> callback) {
		service.save(eiaType, callback);

	}

	public static void update(EiaTypePicture eiaTypePicture,
			GHAAsyncCallback<Boolean> callback) {
		service.update(eiaTypePicture, callback);
	}
	
	public static void update(EiaType eiaType, int[] noDeletePicture,
			GHAAsyncCallback<Boolean> callback) {
		service.update(eiaType, noDeletePicture, callback);
	}
	
	public static void deletePictureFromSession(String pictureName, 
			GHAAsyncCallback<Void> callback){ 
		service.deletePictureFromSession(pictureName, callback);
	}
}