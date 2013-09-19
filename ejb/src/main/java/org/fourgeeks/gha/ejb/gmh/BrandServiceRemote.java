/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 * 
 */

@Remote
public interface BrandServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param brand
	 * @return the list of brands
	 * @throws GHAEJBException
	 */
	public List<Brand> find(Brand brand) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the brand
	 * @throws GHAEJBException
	 */
	public Brand find(long Id) throws GHAEJBException;
	
	/**
	 * @param manufacturer
	 * @return a list of brands filtered by manufacturer
	 * @throws GHAEJBException
	 */
	public List<Brand> findByManufacturer(Manufacturer manufacturer)throws GHAEJBException;

	/**
	 * @return the list of brands
	 * @throws GHAEJBException
	 */
	public List<Brand> getAll() throws GHAEJBException;

	/**
	 * @param brand
	 * @return the saved brand
	 * @throws GHAEJBException
	 */
	public Brand save(Brand brand) throws GHAEJBException;

	/**
	 * @param brand
	 * @return the updated brand
	 * @throws GHAEJBException
	 */
	public Brand update(Brand brand) throws GHAEJBException;
}
