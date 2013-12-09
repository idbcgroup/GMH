/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface RoleServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of the BaseRoles
	 * @throws GHAEJBException
	 */
	public List<Role> find(Role entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the BaseRole
	 * @throws GHAEJBException
	 */
	public Role find(long Id) throws GHAEJBException;

	/**
	 * @return the list of BaseRoles
	 * @throws GHAEJBException
	 */
	public List<Role> getAll();

	/**
	 * @param entity
	 * @return the saved BaseRole
	 * @throws GHAEJBException
	 */
	public Role save(Role entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated BaseRole
	 * @throws GHAEJBException
	 */
	public Role update(Role entity) throws GHAEJBException;
}
