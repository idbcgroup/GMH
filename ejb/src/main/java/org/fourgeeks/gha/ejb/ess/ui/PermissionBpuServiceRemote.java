package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.PermissionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface PermissionBpuServiceRemote {
	/**
	 * @param bpu
	 * @return the permissions as a list
	 * @throws GHAEJBException
	 */
	public List<PermissionBpu> getPermissionByBpu(Bpu bpu)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @return an instance
	 * @throws GHAEJBException
	 */
	public PermissionBpu save(PermissionBpu bpuFunction)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @throws GHAEJBException
	 */
	public void delete(PermissionBpu bpuFunction)
			throws GHAEJBException;
}
