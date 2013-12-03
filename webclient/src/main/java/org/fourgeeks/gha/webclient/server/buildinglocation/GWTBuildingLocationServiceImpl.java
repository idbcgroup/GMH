/**
 * 
 */
package org.fourgeeks.gha.webclient.server.buildinglocation;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.ejb.gmh.BuildingLocationServiceRemote;
import org.fourgeeks.gha.webclient.client.buildinglocation.GWTBuildingLocationService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/buildingLocation" })
public class GWTBuildingLocationServiceImpl extends RemoteServiceServlet
		implements GWTBuildingLocationService {

	@EJB(lookup = "java:global/ear-1/ejb-1/BuildingLocationService")
	BuildingLocationServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<BuildingLocation> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public List<BuildingLocation> getAll(int offset, int size)
			throws GHAEJBException {
		return service.getAll(); // TODO
	}
}
