/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author alacret
 * 
 */

@Remote
public interface MaterialServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param Material
	 * @return the list of Materials
	 * @throws GHAEJBException
	 */
	public List<Material> find(Material Material) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Material
	 * @throws GHAEJBException
	 */
	public Material find(long Id) throws GHAEJBException;
	
	/**
	 * @param brand
	 * @return a list of Materials filtered by brand
	 * @throws GHAEJBException
	 */
	public List<Material> findByBrand(Brand brand) throws GHAEJBException;

	/**
	 * @return the list of Materials
	 * @throws GHAEJBException
	 */
	public List<Material> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of Materials
	 * @throws GHAEJBException
	 */
	public List<Material> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param Material
	 * @return the saved Material
	 * @throws GHAEJBException
	 */
	public Material save(Material Material) throws GHAEJBException;

	/**
	 * @param Material
	 * @return the updated Material
	 * @throws GHAEJBException
	 */
	public Material update(Material Material) throws GHAEJBException;

	/**
	 * @return the list of Materials who are Utilities
	 * @throws GHAEJBException
	 */
	public List<Material> getAllUtilities() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of Materials who are Utilities
	 * @throws GHAEJBException
	 */
	public List<Material> getAllUtilities(int offset, int size)
			throws GHAEJBException;

}
