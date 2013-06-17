/**
 * 
 */
package org.fourgeeks.gha.webclient.server.brand;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.brand.GWTBrandService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
public class GWTBrandServiceImpl extends RemoteServiceServlet implements
		GWTBrandService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Brand> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brand> getAll(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}
}
