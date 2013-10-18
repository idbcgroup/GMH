package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
		if (caught instanceof GHAEJBException)
			GHANotification.alert(((GHAEJBException) caught).getGhaMessage());
		else
			GHANotification.alert(caught.getMessage());
	}
}
