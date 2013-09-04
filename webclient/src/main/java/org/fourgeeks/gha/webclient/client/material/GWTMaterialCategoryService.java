/**
 * 
 */
package org.fourgeeks.gha.webclient.client.material;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.MaterialCategory;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("materialCategory")
public interface GWTMaterialCategoryService extends RemoteService {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param MaterialCategory
	 * @return the list of MaterialCategories
	 * @throws EJBException
	 */
	public List<MaterialCategory> find(MaterialCategory materialCategory) throws EJBException;

	/**
	 * @param Id
	 * @return the MaterialCategorys
	 * @throws EJBException
	 */
	public MaterialCategory find(long Id) throws EJBException;
	

	/**
	 * @return the list of MaterialCategories
	 * @throws EJBException
	 */
	public List<MaterialCategory> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of MaterialCategories
	 * @throws EJBException
	 */
	public List<MaterialCategory> getAll(int offset, int size) throws EJBException;

	/**
	 * @param MaterialCategory
	 * @return the saved MaterialCategory
	 * @throws EJBException
	 */
	public MaterialCategory save(MaterialCategory materialCategory) throws EJBException;

	/**
	 * @param MaterialCategory
	 * @return the updated MaterialCategory
	 * @throws EJBException
	 */
	public MaterialCategory update(MaterialCategory materialCategory) throws EJBException;
}
