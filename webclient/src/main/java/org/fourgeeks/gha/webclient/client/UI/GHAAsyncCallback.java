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
		String message = t.getMessage().trim();
		if (message.equals("0")) {
			GHANotification.oldAlert(GHAStrings.get("connection-problem"));
			return;
		}

		if (t instanceof GHAEJBException)
			GHANotification.alert(((GHAEJBException) t).getGhaMessage());
		else
			GHANotification.oldAlert(message);
	}
}
