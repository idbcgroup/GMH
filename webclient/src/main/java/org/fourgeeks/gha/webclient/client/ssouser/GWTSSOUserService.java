/**
 * 
 */
package org.fourgeeks.gha.webclient.client.ssouser;

import java.util.List;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("ssouser")
public interface GWTSSOUserService extends RemoteService {
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

	/**
	 */
	public BpuFunction save(BpuFunction bpuFunction) throws GHAEJBException;

	/**
	 */
	public void delete(BpuFunction bpuFunction) throws GHAEJBException;
}
