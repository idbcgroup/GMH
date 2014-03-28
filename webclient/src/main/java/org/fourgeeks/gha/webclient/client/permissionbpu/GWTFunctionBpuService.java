package org.fourgeeks.gha.webclient.client.permissionbpu;

import java.util.List;

import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("functionbpu")
public interface GWTFunctionBpuService extends RemoteService {

	/**
	 * @param bpuFunction
	 * @throws GHAEJBException
	 */
	public void delete(FunctionBpu bpuFunction) throws GHAEJBException;

	/**
	 * @param bpu
	 * @return THE LISTS OF Functions
	 * @throws GHAEJBException
	 */
	public List<FunctionBpu> getPermissionsByBpu(Bpu bpu)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @return THE SAVED ENTITY
	 * @throws GHAEJBException
	 */
	public FunctionBpu save(FunctionBpu bpuFunction) throws GHAEJBException;
}
