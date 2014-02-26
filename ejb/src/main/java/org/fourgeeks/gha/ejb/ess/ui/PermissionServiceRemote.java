package org.fourgeeks.gha.ejb.ess.ui;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.Permission;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface PermissionServiceRemote {

	/**
	 * @param permission
	 * @throws GHAEJBException
	 */
	public void delete(Permission permission) throws GHAEJBException;

	/**
	 * @param permission
	 * @return an instance
	 * @throws GHAEJBException
	 */
	public Permission save(Permission permission) throws GHAEJBException;
}
