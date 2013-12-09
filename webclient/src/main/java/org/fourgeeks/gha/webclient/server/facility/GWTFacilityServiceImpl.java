/**
 * 
 */
package org.fourgeeks.gha.webclient.server.facility;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.ejb.gar.FacilityServiceRemote;
import org.fourgeeks.gha.webclient.client.facility.GWTFacilityService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */

@WebServlet(urlPatterns = { "/webclient/facility" })
public class GWTFacilityServiceImpl extends RemoteServiceServlet implements
		GWTFacilityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/FacilityService")
	private FacilityServiceRemote ejbService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.facility.GWTFacilityService#delete
	 * (long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.facility.GWTFacilityService#find(org
	 * .fourgeeks.gha.domain.gar.Facility)
	 */
	@Override
	public List<Facility> find(Facility entity) throws GHAEJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.facility.GWTFacilityService#find(long)
	 */
	@Override
	public Facility find(long Id) throws GHAEJBException {
		return ejbService.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.facility.GWTFacilityService#getAll()
	 */
	@Override
	public List<Facility> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.facility.GWTFacilityService#save(org
	 * .fourgeeks.gha.domain.gar.Facility)
	 */
	@Override
	public Facility save(Facility entity) throws GHAEJBException {
		return ejbService.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.facility.GWTFacilityService#update
	 * (org.fourgeeks.gha.domain.gar.Facility)
	 */
	@Override
	public Facility update(Facility entity) throws GHAEJBException {
		return ejbService.update(entity);
	}

}
