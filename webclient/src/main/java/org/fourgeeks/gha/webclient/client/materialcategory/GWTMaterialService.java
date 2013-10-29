/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.Brand;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("material")
public interface GWTMaterialService extends RemoteService {
	/**
	 * @return a List with all the Materials
	 * @throws GHAEJBException
	 */
	public List<Material> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Materials starting from offset
	 * @throws GHAEJBException
	 */
	public List<Material> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param material
	 * @return a list of materials that match the Criteria
	 * @throws GHAEJBException
	 */
	public List<Material> find(Material material) throws GHAEJBException;
	
	/**
	 * @param brand
	 * @return a list of Materials filtered by brand
	 * @throws GHAEJBException
	 */
	public List<Material> findByBrand(Brand brand) throws GHAEJBException;

	/**
	 * @return a List with all the Materials who are utilities
	 * @throws GHAEJBException
	 */
	public List<Material> getAllUtilities() throws GHAEJBException;

	/**
	 * @param material
	 * @return Material saved
	 * @throws GHAEJBException
	 */
	public Material save(Material material) throws GHAEJBException;
}
