package org.fourgeeks.gha.webclient.server.function;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.FunctionServiceRemote;
import org.fourgeeks.gha.webclient.client.function.GWTFunctionService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/function" })
public class GWTFunctionServiceImpl extends RemoteServiceServlet implements
		GWTFunctionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "ess.FunctionService")
	FunctionServiceRemote service;

	@Override
	public List<Function> getAll() throws GHAEJBException {
		return service.getAll();
	}

}
