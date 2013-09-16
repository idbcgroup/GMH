/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import org.fourgeeks.gha.domain.ess.InstanceLogon;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author emiliot
 *
 */
public interface InstanceLogonServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param instanceLogon
	 * @return the list of instanceLogons
	 * @throws EJBException
	 */
	public List<InstanceLogon> find(InstanceLogon instanceLogon) throws EJBException;

	/**
	 * @param Id
	 * @return the instanceLogon
	 * @throws EJBException
	 */
	public InstanceLogon find(long Id) throws EJBException;
	
	/**
	 * @return the list of instanceLogons
	 * @throws EJBException
	 */
	public List<InstanceLogon> getAll() throws EJBException;

	/**
	 * @param instanceLogon
	 * @return the saved instanceLogon
	 * @throws EJBException
	 */
	public InstanceLogon save(InstanceLogon instanceLogon) throws EJBException;

	/**
	 * @param instanceLogon
	 * @return the updated instanceLogon
	 * @throws EJBException
	 */
	public InstanceLogon update(InstanceLogon instanceLogon) throws EJBException;
}
