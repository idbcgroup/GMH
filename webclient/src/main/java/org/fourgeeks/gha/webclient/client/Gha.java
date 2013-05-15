package org.fourgeeks.gha.webclient.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gha implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Window.alert("gfhfgh");
		GWTUserServiceAsync service = GWT.create(GWTUserService.class);
		service.test(new AsyncCallback<Boolean>() {

			public void onSuccess(Boolean result) {
				Window.alert("" + result);
			}

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
}
