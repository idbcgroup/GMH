/**
 * 
 */
package org.fourgeeks.gha.webclient.server.workingarea;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote;
import org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */

@WebServlet(urlPatterns = {"/webclient/workingArea"})
public class GWTWorkingAreaServiceImpl extends RemoteServiceServlet implements GWTWorkingAreaService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "ess.WorkingAreaService")
	private WorkingAreaServiceRemote ejbService;
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService#find(org.fourgeeks.gha.domain.ess.WorkingArea)
	 */
	@Override
	public List<WorkingArea> find(WorkingArea entity) throws GHAEJBException {
		return ejbService.find(entity);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService#find(long)
	 */
	@Override
	public WorkingArea find(long Id) throws GHAEJBException {
		return ejbService.find(Id);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService#getAll()
	 */
	@Override
	public List<WorkingArea> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService#save(org.fourgeeks.gha.domain.ess.WorkingArea)
	 */
	@Override
	public WorkingArea save(WorkingArea entity) throws GHAEJBException {
		return ejbService.save(entity);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.workingarea.GWTWorkingAreaService#update(org.fourgeeks.gha.domain.ess.WorkingArea)
	 */
	@Override
	public WorkingArea update(WorkingArea entity) throws GHAEJBException {
		return ejbService.update(entity);
	}

}
