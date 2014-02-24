package org.fourgeeks.gha.webclient.client.permissionbpu;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.PermissionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("permissionbpu")
public interface GWTPermissionBpuService extends RemoteService {

	/**
	 * @param bpuFunction
	 * @throws GHAEJBException
	 */
	public void delete(PermissionBpu bpuFunction) throws GHAEJBException;

	/**
	 * @param bpu
	 * @return THE LISTS OF PERMISSIONS
	 * @throws GHAEJBException
	 */
	public List<PermissionBpu> getPermissionsByBpu(Bpu bpu)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @return THE SAVED ENTITY
	 * @throws GHAEJBException
	 */
	public PermissionBpu save(PermissionBpu bpuFunction) throws GHAEJBException;
}
