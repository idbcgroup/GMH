/**
 * 
 */
package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author emiliot
 * 
 */
@Remote
public interface SSOUserServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param ssoUser
	 * @return the list of ssoUsers
	 * @throws GHAEJBException
	 */
	public List<SSOUser> find(SSOUser ssoUser) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the ssoUser
	 * @throws GHAEJBException
	 */
	public SSOUser find(long Id) throws GHAEJBException;

	/**
	 * @param userName
	 * @return the SSOUser associated with this username
	 * @throws GHAEJBException
	 */
	public SSOUser findByUsername(String userName) throws GHAEJBException;

	/**
	 * @return the list of ssoUsers
	 * @throws GHAEJBException
	 */
	public List<SSOUser> getAll() throws GHAEJBException;

	/**
	 * @param ssoUser
	 * @return the saved ssoUser
	 * @throws GHAEJBException
	 */
	public SSOUser save(SSOUser ssoUser) throws GHAEJBException;

	/**
	 * @param ssoUser
	 * @return the updated ssoUser
	 * @throws GHAEJBException
	 */
	public SSOUser update(SSOUser ssoUser) throws GHAEJBException;
}
