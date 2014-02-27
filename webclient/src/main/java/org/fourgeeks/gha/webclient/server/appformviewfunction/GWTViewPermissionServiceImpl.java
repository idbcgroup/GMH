package org.fourgeeks.gha.webclient.server.appformviewfunction;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.ui.ViewFunctionServiceRemote;
import org.fourgeeks.gha.webclient.client.viewpermission.GWTViewPermissionService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/biewpermission" })
public class GWTViewPermissionServiceImpl extends RemoteServiceServlet
		implements GWTViewPermissionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/AppFormViewFunctionService")
	ViewFunctionServiceRemote service;

	@Override
	public List<ViewFunction> getAll() throws GHAEJBException {
		return service.getAll();

	}

}
