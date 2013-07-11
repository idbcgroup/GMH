/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface BaseRoleServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param entity
	 * @return the list of the BaseRoles
	 * @throws EJBException
	 */
	public List<BaseRole> find(BaseRole entity) throws EJBException;

	/**
	 * @param Id
	 * @return the BaseRole
	 * @throws EJBException
	 */
	public BaseRole find(long Id) throws EJBException;

	/**
	 * @return the list of BaseRoles
	 * @throws EJBException
	 */
	public List<BaseRole> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved BaseRole
	 * @throws EJBException
	 */
	public BaseRole save(BaseRole entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated BaseRole
	 * @throws EJBException
	 */
	public BaseRole update(BaseRole entity) throws EJBException;
}
