/**
 * 
 */
package org.fourgeeks.gha.webclient.client.ssouser;

import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.EJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("ssouser")
public interface GWTSSOUserService extends RemoteService{
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param ssoUser
	 * @return the list of ssoUsers
	 * @throws EJBException
	 */
	public List<SSOUser> find(SSOUser ssoUser) throws EJBException;

	/**
	 * @param Id
	 * @return the ssoUser
	 * @throws EJBException
	 */
	public SSOUser find(long Id) throws EJBException;
	
	/**
	 * @return the list of ssoUsers
	 * @throws EJBException
	 */
	public List<SSOUser> getAll() throws EJBException;

	/**
	 * @param ssoUser
	 * @return the saved ssoUser
	 * @throws EJBException
	 */
	public SSOUser save(SSOUser ssoUser) throws EJBException;

	/**
	 * @param ssoUser
	 * @return the updated ssoUser
	 * @throws EJBException
	 */
	public SSOUser update(SSOUser ssoUser) throws EJBException;
}
