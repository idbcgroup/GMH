package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface FunctionBpuServiceRemote {
	/**
	 * @param bpu
	 * @return the permissions as a list
	 * @throws GHAEJBException
	 */
	public List<FunctionBpu> getFunctionByBpu(Bpu bpu)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @return an instance
	 * @throws GHAEJBException
	 */
	public FunctionBpu save(FunctionBpu bpuFunction)
			throws GHAEJBException;

	/**
	 * @param bpuFunction
	 * @throws GHAEJBException
	 */
	public void delete(FunctionBpu bpuFunction)
			throws GHAEJBException;
}
