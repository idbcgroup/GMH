package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface BpuFunctionServiceRemote {
	/**
	 * @param bpu
	 * @return the permissions codes as a list
	 */
	public List<String> getFunctionsAsStringListByBpu(Bpu bpu);
}
