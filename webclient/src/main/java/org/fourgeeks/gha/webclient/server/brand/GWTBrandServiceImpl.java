/**
 * 
 */
package org.fourgeeks.gha.webclient.server.brand;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.webclient.client.brand.GWTBrandService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/brand" })
public class GWTBrandServiceImpl extends RemoteServiceServlet implements
		GWTBrandService {

	@EJB(name = "gmh.BrandService")
	BrandServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Brand> getAll() throws EJBException {
		return service.getAll();
	}

	@Override
	public List<Brand> getAll(int offset, int size) throws EJBException {
		// TODO Auto-generated method stub
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.brand.GWTBrandService#findByManufacturer(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public List<Brand> findByManufacturer(Manufacturer manufacturer)
			throws EJBException {
		return service.findByManufacturer(manufacturer);
	}
}
