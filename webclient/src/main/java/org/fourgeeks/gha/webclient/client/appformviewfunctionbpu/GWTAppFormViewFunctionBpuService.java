package org.fourgeeks.gha.webclient.client.appformviewfunctionbpu;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("appFormViewFunctionBpu")
public interface GWTAppFormViewFunctionBpuService extends RemoteService {

	/**
	 * @param bpuFunction
	 * @return THE SAVED ENTITY
	 * @throws GHAEJBException
	 */
	public AppFormViewFunctionBpu save(AppFormViewFunctionBpu bpuFunction)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @throws GHAEJBException
	 */
	public void delete(AppFormViewFunctionBpu bpuFunction)
			throws GHAEJBException;

	/**
	 * @param bpu
	 * @return THE LISTS OF FUNCTIONS
	 * @throws GHAEJBException
	 */
	public List<AppFormViewFunctionBpu> getFunctionsByBpu(Bpu bpu)
			throws GHAEJBException;
}
