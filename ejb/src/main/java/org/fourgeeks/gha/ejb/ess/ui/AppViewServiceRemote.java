package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Remote
public interface AppViewServiceRemote {

	/**
	 * @param bpu
	 * @return a list of appviews for a user
	 * @throws GHAEJBException
	 */
	public List<AppView> getAppViewsByBpu(Bpu bpu) throws GHAEJBException;
}
