/**
 * 
 */
package org.fourgeeks.gha.webclient.server.buildinglocation;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.webclient.client.buildinglocation.GWTBuildingLocationService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
public class GWTBuildingLocationServiceImpl extends RemoteServiceServlet
		implements GWTBuildingLocationService {

	@EJB(name = "gmh.BuildingLocationService")
	// TODO
	BrandServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<BuildingLocation> getAll() throws EJBException {
		return null;// service.getAll(); //TODO
	}

	@Override
	public List<BuildingLocation> getAll(int offset, int size)
			throws EJBException {
		return null; // service.getAll(); //TODO
	}
}
