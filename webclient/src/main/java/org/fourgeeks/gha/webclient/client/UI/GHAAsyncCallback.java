package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class GHAAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		GHANotification.alert(caught.getMessage());
	}
}
