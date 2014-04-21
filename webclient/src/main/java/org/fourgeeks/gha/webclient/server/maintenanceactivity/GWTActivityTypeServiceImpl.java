/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceactivity;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ActivityTypeServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.GWTActivityTypeService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/activityTypeService" })
public class GWTActivityTypeServiceImpl extends RemoteServiceServlet implements
		GWTActivityTypeService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/ActivityTypeService")
	ActivityTypeServiceRemote ejbService;

	@Override
	public void delete(long id) throws GHAEJBException {
		ejbService.delete(id);
	}

	@Override
	public void delete(List<ActivityType> entities) throws GHAEJBException {
		ejbService.delete(entities);
	}

	@Override
	public List<ActivityType> getAllTypes() throws GHAEJBException {
		return ejbService.getAllTypes();
	}

	@Override
	public List<ActivityType> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}

	@Override
	public List<ActivityType> getSubTypes(ActivityType type)
			throws GHAEJBException {
		return ejbService.getSubTypes(type);
	}

	@Override
	public ActivityType save(ActivityType entity) throws GHAEJBException {
		return ejbService.save(entity);
	}

}
