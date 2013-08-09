/**
 * 
 */
package org.fourgeeks.gha.webclient.server.material;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote;
import org.fourgeeks.gha.webclient.client.material.GWTMaterialService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/material" })
public class GWTMaterialServiceImpl extends RemoteServiceServlet implements
		GWTMaterialService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.MaterialService")
	MaterialServiceRemote materialServiceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTMaterialService#getAll()
	 */
	@Override
	public List<Material> getAll() throws EJBException {
		return materialServiceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTMaterialService#getAll(int,
	 * int)
	 */
	@Override
	public List<Material> getAll(int offset, int size) throws EJBException {
		return materialServiceRemote.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTMaterialService#find(org.fourgeeks
	 * .gha.domain.gmh.Material)
	 */
	@Override
	public List<Material> find(Material Material) throws EJBException {
		return materialServiceRemote.find(Material);
	}
}
