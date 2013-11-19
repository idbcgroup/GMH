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
	public void onFailure(Throwable t) {
		String message = t.getMessage();
		if (t instanceof GHAEJBException)
			GHANotification.alert(((GHAEJBException) t).getGhaMessage());
		else if (message != null && message.trim().equals("0")) {
			GHANotification.alert("connection-problem");
		} else
			GHANotification.oldAlert(message);
	}
}
