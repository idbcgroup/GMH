/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author emiliot
 * 
 */

@Remote
public interface MaterialBrandServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param Id
	 * @return
	 * @throws GHAEJBException
	 */
	public MaterialBrand find(long Id) throws GHAEJBException;

	/**
	 * @param materialBrand
	 * @return
	 * @throws GHAEJBException
	 */
	public List<MaterialBrand> find(MaterialBrand materialBrand)
			throws GHAEJBException;

	/**
	 * @param brand
	 * @return
	 * @throws GHAEJBException
	 */
	public List<MaterialBrand> findByBrand(Brand brand) throws GHAEJBException;

	/**
	 * @param type
	 * @return
	 * @throws GHAEJBException
	 */
	public List<MaterialBrand> findByMaterialType(MaterialTypeEnum type)
			throws GHAEJBException;

	/**
	 * @return
	 * @throws GHAEJBException
	 */
	public List<MaterialBrand> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return
	 * @throws GHAEJBException
	 */
	public List<MaterialBrand> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param materialBrand
	 * @return
	 * @throws GHAEJBException
	 */
	public MaterialBrand save(MaterialBrand materialBrand)
			throws GHAEJBException;

	/**
	 * @param materialBrand
	 * @return
	 * @throws GHAEJBException
	 */
	public MaterialBrand update(MaterialBrand materialBrand)
			throws GHAEJBException;
}
