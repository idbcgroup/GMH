package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class MaintenanceProtocolsService
 * 
 * @author naramirez
 */
@Stateless
public class MaintenanceProtocolsService extends GHAEJBExceptionImpl implements
		MaintenanceProtocolsServiceRemote {
	private final static Logger logger = Logger
			.getLogger(MaintenanceProtocolsService.class.getName());

	@PersistenceContext
	EntityManager em;

	@Override
	public void delete(long id) throws GHAEJBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MaintenanceProtocols> findByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceProtocols.findByMaintenancePlan",
							MaintenanceProtocols.class)
					.setParameter("maintenancePlan", plan).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenanceProtocols", e);
			throw super.generateGHAEJBException(
					"MaintenanceProtocols-findByMaintenancePlan-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public MaintenanceProtocols save(MaintenanceActivity activity,
			MaintenancePlan plan, int ordinal) throws GHAEJBException {
		// TODO Auto-generated method stub
		return null;
	}

}
