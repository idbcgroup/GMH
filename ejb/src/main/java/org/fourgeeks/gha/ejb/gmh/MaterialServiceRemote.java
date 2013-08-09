/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author alacret
 * 
 */

@Remote
public interface MaterialServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param Material
	 * @return the list of Materials
	 * @throws EJBException
	 */
	public List<Material> find(Material Material) throws EJBException;

	/**
	 * @param Id
	 * @return the Material
	 * @throws EJBException
	 */
	public Material find(long Id) throws EJBException;

	/**
	 * @return the list of Materials
	 * @throws EJBException
	 */
	public List<Material> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of Materials
	 * @throws EJBException
	 */
	public List<Material> getAll(int offset, int size) throws EJBException;

	/**
	 * @param Material
	 * @return the saved Material
	 * @throws EJBException
	 */
	public Material save(Material Material) throws EJBException;

	/**
	 * @param Material
	 * @return the updated Material
	 * @throws EJBException
	 */
	public Material update(Material Material) throws EJBException;
}
