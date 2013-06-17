/**
 * 
 */
package org.fourgeeks.gha.webclient.server.brand;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.webclient.client.brand.GWTBrandService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
public class GWTBrandServiceImpl extends RemoteServiceServlet implements
		GWTBrandService {

	@EJB(name = "gmh.BrandService")
	BrandServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Brand> getAll() {
		return service.getAll();
	}

	@Override
	public List<Brand> getAll(int offset, int size) {
		// TODO Auto-generated method stub
		return service.getAll();
	}
}
