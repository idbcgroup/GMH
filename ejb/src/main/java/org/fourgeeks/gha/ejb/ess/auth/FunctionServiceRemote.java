package org.fourgeeks.gha.ejb.ess.auth;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface FunctionServiceRemote {

	/**
	 * @param function
	 * @throws GHAEJBException
	 */
	public void delete(Function function) throws GHAEJBException;

	/**
	 * @param function
	 * @return an instance
	 * @throws GHAEJBException
	 */
	public Function save(Function function) throws GHAEJBException;
}
