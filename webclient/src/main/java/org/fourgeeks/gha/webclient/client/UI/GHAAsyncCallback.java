package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;

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
		if (t instanceof GHAEJBException) {
			GHAAlertManager.alert(((GHAEJBException) t).getGhaMessage());
		} else if (message != null && message.trim().equals("0")) {
			GHAAlertManager.alert("ERROR-HARD",GHAStrings.get("hard-error"),GHAStrings.get("connection-problem"));
		} else {
			GHAAlertManager.alert("ERROR-HARD",GHAStrings.get("hard-error"),message);
		}
	}
}
