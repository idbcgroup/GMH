/**
 * 
 */
package org.fourgeeks.gha.webclient.client.brand;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("brand")
public interface GWTBrandService extends RemoteService {
	/**
	 * @return a List with all the Brands
	 * @throws GHAEJBException 
	 */
	public List<Brand> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Brands starting from offset
	 * @throws GHAEJBException 
	 */
	public List<Brand> getAll(int offset, int size) throws GHAEJBException;
	
	/**
	 * @param manufacturer
	 * @return a list of brands filtered by manufacturer
	 * @throws GHAEJBException
	 */
	public List<Brand> findByManufacturer(Manufacturer manufacturer)throws GHAEJBException;
}
