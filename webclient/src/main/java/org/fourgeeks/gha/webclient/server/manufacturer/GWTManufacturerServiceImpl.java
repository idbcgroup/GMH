/**
 * 
 */
package org.fourgeeks.gha.webclient.server.manufacturer;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.manufacturer.GWTManufacturerService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
public class GWTManufacturerServiceImpl extends RemoteServiceServlet implements
		GWTManufacturerService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Manufacturer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manufacturer> getAll(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}