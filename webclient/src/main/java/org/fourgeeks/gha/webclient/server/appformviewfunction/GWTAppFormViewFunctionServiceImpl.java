package org.fourgeeks.gha.webclient.server.appformviewfunction;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.AppFormViewFunctionServiceRemote;
import org.fourgeeks.gha.webclient.client.appformviewfunction.GWTAppFormViewFunctionService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/appFormViewFunction" })
public class GWTAppFormViewFunctionServiceImpl extends RemoteServiceServlet
		implements GWTAppFormViewFunctionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/AppFormViewFunctionService")
	AppFormViewFunctionServiceRemote service;

	@Override
	public List<AppFormViewFunction> getAll() throws GHAEJBException {
		return service.getAll();

	}

}
