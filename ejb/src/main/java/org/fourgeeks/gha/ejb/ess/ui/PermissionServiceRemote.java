package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.ess.ui.Permission;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

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
	 * @param bpu
	 * @return a list of appviews for a user
	 * @throws GHAEJBException
	 */
	public List<AppView> getAppViewsByBpu(Bpu bpu) throws GHAEJBException;

	/**
	 * @param permission
	 * @return an instance
	 * @throws GHAEJBException
	 */
	public Permission save(Permission permission) throws GHAEJBException;
}
