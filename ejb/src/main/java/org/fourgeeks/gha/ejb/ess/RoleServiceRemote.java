/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface RoleServiceRemote {
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
	public List<Role> find(Role entity) throws EJBException;

	/**
	 * @param Id
	 * @return the BaseRole
	 * @throws EJBException
	 */
	public Role find(long Id) throws EJBException;

	/**
	 * @return the list of BaseRoles
	 * @throws EJBException
	 */
	public List<Role> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved BaseRole
	 * @throws EJBException
	 */
	public Role save(Role entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated BaseRole
	 * @throws EJBException
	 */
	public Role update(Role entity) throws EJBException;
}
