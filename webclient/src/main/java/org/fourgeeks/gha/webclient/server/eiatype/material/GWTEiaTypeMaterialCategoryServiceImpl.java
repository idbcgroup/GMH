package org.fourgeeks.gha.webclient.server.eiatype.material;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialCategoryServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.materialcategory.GWTEiaTypeMaterialCategoryService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns = { "/webclient/eiaTypeMaterialCategory" })
public class GWTEiaTypeMaterialCategoryServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeMaterialCategoryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.EiaTypeMaterialService")
	private EiaTypeMaterialCategoryServiceRemote serviceRemote;

	@Override
	public List<EiaTypeMaterialCategory> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		return serviceRemote.findByEiaType(eiaType);
	}

	@Override
	public EiaTypeMaterialCategory save(EiaTypeMaterialCategory eiaTypeMaterial)
			throws GHAEJBException {
		return serviceRemote.save(eiaTypeMaterial);
	}

	@Override
	public void delete(long id) throws GHAEJBException {
		serviceRemote.delete(id);
	}

}
