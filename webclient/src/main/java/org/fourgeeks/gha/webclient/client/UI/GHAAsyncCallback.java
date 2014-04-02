package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author alacret
 * 
 * @param <T>
 */
public abstract class GHAAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable t) {
		final String message = t.getMessage();
		if (t instanceof GHAEJBException) {
			GHAErrorMessageProcessor.alert(((GHAEJBException) t).getGhaMessage());
		} else if (message != null && message.trim().equals("0")) {
			GHAErrorMessageProcessor.alert("VEC-ERROR",GHAStrings.get("error"),GHAStrings.get("connection-problem"));
		} else {
			GHAErrorMessageProcessor.alert("server-error");
		}
	}
}
