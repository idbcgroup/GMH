package org.fourgeeks.gha.webclient.client.viewpermission;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("viewfunction")
public interface GWTViewFunctionnService extends RemoteService {

	/**
	 * @param bpu
	 * @return a list of logs by bpu
	 * @throws GHAEJBException
	 */
	public List<ViewFunction> getAll() throws GHAEJBException;
}
