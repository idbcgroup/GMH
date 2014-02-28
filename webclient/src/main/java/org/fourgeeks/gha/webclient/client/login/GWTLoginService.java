package org.fourgeeks.gha.webclient.client.login;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret Gwt Login Service
 */
@RemoteServiceRelativePath("login")
public interface GWTLoginService extends RemoteService {
	/**
	 * 
	 * @return the Bpu represented by the username
	 * @throws GHAEJBException
	 */
	public Bpu getLoggedUser() throws GHAEJBException;

	/**
	 * @return true if the user has been authenticated
	 */
	public boolean isLogged();

	/**
	 * log the user data
	 */
	public void logOut();
}
