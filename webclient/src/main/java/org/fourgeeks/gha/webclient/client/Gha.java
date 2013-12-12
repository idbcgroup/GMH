package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;
import org.fourgeeks.gha.webclient.client.login.GWTLoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Gha implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				final String historyToken = event.getValue();
				if (historyToken == null)
					return;
				GHAPlaceSet.showPlace(historyToken);
				// GHAPlacesFactory.showPlace(historyToken);
			}
		});

		final GWTLoginServiceAsync service = GWT.create(GWTLoginService.class);
		service.getLoggedUser(new GHAAsyncCallback<Bpu>() {
			@Override
			public void onSuccess(Bpu result) {
				// if (History.getToken().equals("")) {
				// History.newItem("home");
				// History.fireCurrentHistoryState();
				// }
				GHASessionData.setLoggedUser(result);
				if (!History.getToken().equals(""))
					History.fireCurrentHistoryState();
				// GHAPlacesFactory.showPlace(History.getToken());
			}
		});

		resize();

		GHAUiHelper.addGHAResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize();
			}
		});
	}

	private void resize() {
		RootPanel.get("main-content").setHeight(
				GHAUiHelper.getTabHeight() + "px");
	}

}