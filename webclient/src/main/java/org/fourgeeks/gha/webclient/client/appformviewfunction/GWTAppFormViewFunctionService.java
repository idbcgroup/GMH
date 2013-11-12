package org.fourgeeks.gha.webclient.client.appformviewfunction;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("appFormViewFunction")
public interface GWTAppFormViewFunctionService extends RemoteService {

	/**
	 * @param bpu
	 * @return a list of logs by bpu
	 * @throws GHAEJBException
	 */
	public List<AppFormViewFunction> getAll() throws GHAEJBException;
}
