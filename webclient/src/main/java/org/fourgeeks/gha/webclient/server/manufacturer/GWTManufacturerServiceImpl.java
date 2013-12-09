/**
 * 
 */
package org.fourgeeks.gha.webclient.server.manufacturer;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;
import org.fourgeeks.gha.webclient.client.manufacturer.GWTManufacturerService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/manufacturer" })
public class GWTManufacturerServiceImpl extends RemoteServiceServlet implements
		GWTManufacturerService {

	@EJB(lookup = "java:global/ear-1/ejb-1/ManufacturerService")
	ManufacturerServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Manufacturer> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public List<Manufacturer> getAll(int offset, int size)
			throws GHAEJBException {
		// TODO Auto-generated method stub
		return service.getAll();
	}
}