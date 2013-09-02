/**
 * 
 */
package org.fourgeeks.gha.webclient.client.material;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public List<Material> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Materials starting from offset
	 * @throws EJBException
	 */
	public List<Material> getAll(int offset, int size) throws EJBException;

	/**
	 * @param material
	 * @return a list of materials that match the Criteria
	 * @throws EJBException
	 */
	public List<Material> find(Material material) throws EJBException;
	
	/**
	 * @param brand
	 * @return a list of Materials filtered by brand
	 * @throws EJBException
	 */
	public List<Material> findByBrand(Brand brand) throws EJBException;

	/**
	 * @return a List with all the Materials who are utilities
	 * @throws EJBException
	 */
	public List<Material> getAllUtilities() throws EJBException;

	/**
	 * @param material
	 * @return Material saved
	 * @throws EJBException
	 */
	public Material save(Material material) throws EJBException;
}
