package org.fourgeeks.gha.webclient.server.obu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.ejb.gar.BspServiceRemote;
import org.fourgeeks.gha.webclient.client.obu.GWTBspService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author naramirez
 * 
 */
@WebServlet(urlPatterns = { "/webclient/bsp" })
public class GWTBspServiceImpl extends RemoteServiceServlet implements
		GWTBspService {
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/BspService")
	BspServiceRemote service;

	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	@Override
	public List<Bsp> find(Bsp entity) throws GHAEJBException {
		return service.find(entity);
	}

	@Override
	public Bsp find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	@Override
	public List<Bsp> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public Bsp save(Bsp entity) throws GHAEJBException {
		return service.save(entity);
	}

	@Override
	public Bsp update(Bsp entity) throws GHAEJBException {
		return service.update(entity);
	}

}