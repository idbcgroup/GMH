/**
 * 
 */
package org.fourgeeks.gha.webclient.server.materialcategory;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.ejb.glm.MaterialCategoryServiceRemote;
import org.fourgeeks.gha.webclient.client.materialcategory.GWTMaterialCategoryService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */

@WebServlet(urlPatterns = { "/webclient/materialCategory" })
public class GWTMaterialCategoryServiceImpl extends RemoteServiceServlet
		implements GWTMaterialCategoryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/MaterialCategoryService")
	MaterialCategoryServiceRemote ejbService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #delete(long)
	 */
	@Override
	public void delete(String code) throws GHAEJBException {
		ejbService.delete(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #find(org.fourgeeks.gha.domain.glm.MaterialCategory)
	 */
	@Override
	public List<MaterialCategory> find(MaterialCategory materialCategory)
			throws GHAEJBException {
		return ejbService.find(materialCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #find(long)
	 */
	@Override
	public MaterialCategory find(String code) throws GHAEJBException {
		return ejbService.find(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #getAll()
	 */
	@Override
	public List<MaterialCategory> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #getAll(int, int)
	 */
	@Override
	public List<MaterialCategory> getAll(int offset, int size)
			throws GHAEJBException {
		return ejbService.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #save(org.fourgeeks.gha.domain.glm.MaterialCategory)
	 */
	@Override
	public MaterialCategory save(MaterialCategory materialCategory)
			throws GHAEJBException {
		return ejbService.save(materialCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.GWTMaterialCategoryService
	 * #update(org.fourgeeks.gha.domain.glm.MaterialCategory)
	 */
	@Override
	public MaterialCategory update(MaterialCategory materialCategory)
			throws GHAEJBException {
		return ejbService.update(materialCategory);
	}
}
