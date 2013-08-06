/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface RoleBaseServiceRemote {
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
	public List<RoleBase> find(RoleBase entity) throws EJBException;

	/**
	 * @param Id
	 * @return the BaseRole
	 * @throws EJBException
	 */
	public RoleBase find(long Id) throws EJBException;

	/**
	 * @return the list of BaseRoles
	 * @throws EJBException
	 */
	public List<RoleBase> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved BaseRole
	 * @throws EJBException
	 */
	public RoleBase save(RoleBase entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated BaseRole
	 * @throws EJBException
	 */
	public RoleBase update(RoleBase entity) throws EJBException;
}
