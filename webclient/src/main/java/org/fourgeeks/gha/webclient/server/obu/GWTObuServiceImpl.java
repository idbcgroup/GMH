package org.fourgeeks.gha.webclient.server.obu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.webclient.client.obu.GWTObuService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/obu" })
public class GWTObuServiceImpl extends RemoteServiceServlet implements
		GWTObuService {

	@EJB(name = "gar.ObuService")
	ObuServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Obu> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public List<Obu> getAll(int offset, int size) throws GHAEJBException {
		// TODO
		return service.getAll();
	}
}