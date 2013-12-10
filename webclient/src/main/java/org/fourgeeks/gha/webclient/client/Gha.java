package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAPlacesFactory;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;
import org.fourgeeks.gha.webclient.client.login.GWTLoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

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

				if (GHASessionData.getLoggedUser() != null)
					GHAPlacesFactory.createPlace(historyToken);
			}
		});

		final GWTLoginServiceAsync service = GWT.create(GWTLoginService.class);
		service.getLoggedUser(new GHAAsyncCallback<Bpu>() {
			@Override
			public void onSuccess(Bpu result) {
				if (History.getToken().equals("")) {
					History.newItem("home");
					History.fireCurrentHistoryState();
				}

				if (result != null) {
					GHASessionData.setLoggedUser(result);

					// Cuando se recarga la pagina, se debe crear de nuevo el
					// "home" antes de crear el "place" actual.
					if (!History.getToken().equals("home"))
						GHAPlacesFactory.createPlace("home");
					GHAPlacesFactory.createPlace(History.getToken());
				}
			}
		});

		// final GWTLoginServiceAsync service =
		// GWT.create(GWTLoginService.class);
		// service.isLogged(new GHAAsyncCallback<Boolean>() {
		// @Override
		// public void onSuccess(Boolean result) {
		// String token = History.getToken();
		//
		// if (!result) {
		// if (token.equals("login"))
		// History.fireCurrentHistoryState();
		// else
		// History.newItem("login");
		// } else {
		// History.fireCurrentHistoryState();
		// }
		// }
		// });

		RootPanel.get("main-content").setHeight(
				GHAUiHelper.getTabHeight() + "px");

		GHAUiHelper.addGHAResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				if(Window.getClientWidth()<1024)
					RootPanel.get().setWidth("1024px");
				else
					RootPanel.get().setWidth(Window.getClientWidth()+"px");
				if(Window.getClientHeight()<768)
					RootPanel.get().setHeight("768px");
				else
					RootPanel.get().setHeight(Window.getClientHeight()+"px");
				
				RootPanel.get("main-content").setHeight(
						GHAUiHelper.getTabHeight() + "px");
			}
		});

		// Window.addResizeHandler(new ResizeHandler() {
		// @Override
		// public void onResize(ResizeEvent event) {
		// RootPanel.get("main-content").setHeight(
		// GHAUiHelper.getTabHeight() + "px");
		// }
		// });
	}
}