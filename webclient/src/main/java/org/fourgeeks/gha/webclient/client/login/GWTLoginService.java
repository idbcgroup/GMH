package org.fourgeeks.gha.webclient.client.login;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface GWTLoginService extends RemoteService {
	public boolean isLogged();

	public boolean login(String user, String password)
			throws IllegalArgumentException;
}
