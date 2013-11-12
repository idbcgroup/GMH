package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface AppFormViewFunctionBpuServiceRemote {
	/**
	 * @param bpu
	 * @return the permissions as a list
	 * @throws GHAEJBException
	 */
	public List<AppFormViewFunctionBpu> getFunctionsByBpu(Bpu bpu)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @return an instance
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
}
