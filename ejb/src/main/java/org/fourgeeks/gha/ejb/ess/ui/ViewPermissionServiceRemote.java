package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.ViewPermission;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface ViewPermissionServiceRemote {

	/**
	 * @return get all the functions
	 * @throws GHAEJBException
	 */
	public List<ViewPermission> getAll() throws GHAEJBException;

}