/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.InstanceLogon;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author emiliot
 * 
 */
@Remote
public interface InstanceLogonServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param instanceLogon
	 * @return the list of instanceLogons
	 * @throws GHAEJBException
	 */
	public List<InstanceLogon> find(InstanceLogon instanceLogon)
			throws GHAEJBException;

	/**
	 * @param Id
	 * @return the instanceLogon
	 * @throws GHAEJBException
	 */
	public InstanceLogon find(long Id) throws GHAEJBException;

	/**
	 * @return the list of instanceLogons
	 * @throws GHAEJBException
	 */
	public List<InstanceLogon> getAll() throws GHAEJBException;

	/**
	 * @param instanceLogon
	 * @return the saved instanceLogon
	 * @throws GHAEJBException
	 */
	public InstanceLogon save(InstanceLogon instanceLogon)
			throws GHAEJBException;

	/**
	 * @param instanceLogon
	 * @return the updated instanceLogon
	 * @throws GHAEJBException
	 */
	public InstanceLogon update(InstanceLogon instanceLogon)
			throws GHAEJBException;
}
