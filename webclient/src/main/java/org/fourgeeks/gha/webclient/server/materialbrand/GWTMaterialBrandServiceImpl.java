/**
 * 
 */
package org.fourgeeks.gha.webclient.server.materialbrand;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote;
import org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/materialBrand" })
public class GWTMaterialBrandServiceImpl extends RemoteServiceServlet implements
		GWTMaterialBrandService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/MaterialBrandService!org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote")
	MaterialBrandServiceRemote service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #find(long)
	 */
	@Override
	public MaterialBrand find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #find(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public List<MaterialBrand> find(MaterialBrand materialBrand)
			throws GHAEJBException {
		return service.find(materialBrand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #findByBrand(org.fourgeeks.gha.domain.gmh.Brand)
	 */
	@Override
	public List<MaterialBrand> findByBrand(Brand brand) throws GHAEJBException {
		return service.findByBrand(brand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #findByMaterialType(org.fourgeeks.gha.domain.glm.MaterialTypeEnum)
	 */
	@Override
	public List<MaterialBrand> findByMaterialType(MaterialTypeEnum type)
			throws GHAEJBException {
		return service.findByMaterialType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #getAll()
	 */
	@Override
	public List<MaterialBrand> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #getAll(int, int)
	 */
	@Override
	public List<MaterialBrand> getAll(int offset, int size)
			throws GHAEJBException {
		return service.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #save(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public MaterialBrand save(MaterialBrand materialBrand)
			throws GHAEJBException {
		return service.save(materialBrand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.materialbrand.GWTMaterialBrandService
	 * #update(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public MaterialBrand update(MaterialBrand materialBrand)
			throws GHAEJBException {
		return service.update(materialBrand);
	}

}
