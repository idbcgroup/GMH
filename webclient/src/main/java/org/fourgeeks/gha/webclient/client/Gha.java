package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.domain.mix.LegalEntity;

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
		final GWTUserServiceAsync service = GWT.create(GWTUserService.class);
		service.test(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				Window.alert("" + result);
				service.test2(new AsyncCallback<LegalEntity>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(LegalEntity result) {
						if (result != null)
							Window.alert("success");
						else
							Window.alert("error");

					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
}
