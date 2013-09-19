package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface BpuFunctionServiceRemote {
	/**
	 * @param bpu
	 * @return the permissions as a list
	 * @throws GHAEJBException
	 */
	public List<BpuFunction> getFunctionsByBpu(Bpu bpu) throws GHAEJBException;
}
