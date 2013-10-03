package org.fourgeeks.gha.webclient.client.function;

import java.util.List;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("function")
public interface GWTFunctionService extends RemoteService {

	/**
	 * @param bpu
	 * @return a list of logs by bpu
	 * @throws GHAEJBException
	 */
	public List<Function> getAll() throws GHAEJBException;
}
