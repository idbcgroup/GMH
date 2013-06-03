package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHAPlacesFactory;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;
import org.fourgeeks.gha.webclient.client.login.GWTLoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Gha implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				final String historyToken = event.getValue();
				if (historyToken == null)
					return;
				// Window.alert("Showing place:"+historyToken);
				// UIPlace place = UIPlacesFactory.createPlace(historyToken);
				// place.show();
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onSuccess() {
						GHAPlace place = GHAPlacesFactory
								.createPlace(historyToken);
						place.show();
					}

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

		final GWTLoginServiceAsync service = GWT.create(GWTLoginService.class);
		service.isLogged(new GHAAsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				// Window.alert("Success. Result:" + result);
				if (!result) {
					String token = History.getToken();
					// Window.alert("Token:" + token);
					if (token.equals("login"))
						History.fireCurrentHistoryState();
					else
						History.newItem("login");
				} else
					History.fireCurrentHistoryState();
			}
		});

		// final GWTEiaTypeServiceAsync eiaService =
		// GWT.create(GWTEiaTypeService.class);
	}

}
