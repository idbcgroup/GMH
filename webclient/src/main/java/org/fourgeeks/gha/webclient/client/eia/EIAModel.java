package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class EIAModel {

	private static final GWTEiaServiceAsync eiaService = GWT
			.create(GWTEiaService.class);

	public static void find(EiaType eiaType, GHAAsyncCallback<List<Eia>> eias) {
		eiaService.find(eiaType, eias);
	}

	public static void save(Eia eia, GHAAsyncCallback<Eia> callback) {
		eiaService.save(eia, callback);

	}

	public static void delete(Long id, GHAAsyncCallback<Boolean> callback) {
		eiaService.delete(id, callback);

	}

	public static void find(Eia eia, GHAAsyncCallback<List<Eia>> callback) {
		eiaService.find(eia, callback);
	}

	public static void update(Eia eia, GHAAsyncCallback<Eia> callback) {
		eiaService.update(eia, callback);

	}

}