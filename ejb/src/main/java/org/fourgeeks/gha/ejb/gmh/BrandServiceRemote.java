/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author emiliot
 * 
 */

@Remote
public interface BrandServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param brand
	 * @return the list of brands
	 * @throws EJBException
	 */
	public List<Brand> find(Brand brand) throws EJBException;

	/**
	 * @param Id
	 * @return the brand
	 * @throws EJBException
	 */
	public Brand find(long Id) throws EJBException;

	/**
	 * @return the list of brands
	 * @throws EJBException
	 */
	public List<Brand> getAll() throws EJBException;

	/**
	 * @param brand
	 * @return the saved brand
	 * @throws EJBException
	 */
	public Brand save(Brand brand) throws EJBException;

	/**
	 * @param brand
	 * @return the updated brand
	 * @throws EJBException
	 */
	public Brand update(Brand brand) throws EJBException;
}
