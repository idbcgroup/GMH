package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author alacret
 * 
 * @param <T>
 */
public abstract class GHAAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		GHANotification.alert(caught.getMessage());
	}
}
