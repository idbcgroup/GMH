/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.ejb.gmh.EiaTypeCategoryServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.GWTEiaTypeCategoryService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */

@WebServlet(urlPatterns = { "/webclient/eiaTypeCategory" })
public class GWTEiaTypeCategoryServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeCategoryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/EiaTypeCategoryService")
	EiaTypeCategoryServiceRemote categoryService;

	@Override
	public List<ServiceResourceCategory> getAll() throws GHAEJBException {
		return categoryService.getAll();
	}

}
