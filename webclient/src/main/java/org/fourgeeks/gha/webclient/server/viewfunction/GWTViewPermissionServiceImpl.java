package org.fourgeeks.gha.webclient.server.viewfunction;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.ui.ViewFunctionServiceRemote;
import org.fourgeeks.gha.webclient.client.viewpermission.GWTViewFunctionnService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/viewfunction" })
public class GWTViewPermissionServiceImpl extends RemoteServiceServlet
		implements GWTViewFunctionnService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/ViewFunctionService")
	ViewFunctionServiceRemote service;

	@Override
	public List<ViewFunction> getAll() throws GHAEJBException {
		return service.getAll();

	}

}
