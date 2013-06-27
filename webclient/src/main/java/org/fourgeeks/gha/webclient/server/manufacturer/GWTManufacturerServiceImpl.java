/**
 * 
 */
package org.fourgeeks.gha.webclient.server.manufacturer;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;
import org.fourgeeks.gha.webclient.client.manufacturer.GWTManufacturerService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
public class GWTManufacturerServiceImpl extends RemoteServiceServlet implements
		GWTManufacturerService {

	@EJB(name = "gmh.ManufacturerService")
	ManufacturerServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Manufacturer> getAll() throws EJBException {
		return service.getAll();
	}

	@Override
	public List<Manufacturer> getAll(int offset, int size) throws EJBException {
		// TODO Auto-generated method stub
		return service.getAll();
	}
}