package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface FunctionServiceRemote {

	/**
	 * @param function
	 * @throws GHAEJBException
	 */
	public void delete(Function function) throws GHAEJBException;

	/**
	 * @param bpu
	 * @return a list of appviews for a user
	 * @throws GHAEJBException
	 */
	public List<AppView> getAppViewsByBpu(Bpu bpu) throws GHAEJBException;

	/**
	 * @param function
	 * @return an instance
	 * @throws GHAEJBException
	 */
	public Function save(Function function) throws GHAEJBException;
}
