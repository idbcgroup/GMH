package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Deprecated
@Stateless
public class MaintenanceActivityMaintenanceProtocolService extends
		GHAEJBExceptionService implements
		MaintenanceActivityMaintenanceProtocolServiceRemote {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceActivityMaintenanceProtocolService.class
					.getName());

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenanceActivityMaintenanceProtocol entity = em.find(
					MaintenanceActivityMaintenanceProtocol.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"ERROR: unable to delete MaintenanceActivityMaintenanceProtocol",
					e);
			throw super.generateGHAEJBException(
					"maintenanceActivityMaintenanceProtocol-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public MaintenanceActivityMaintenanceProtocol save(
			MaintenanceActivityMaintenanceProtocol entity)
			throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(MaintenanceActivityMaintenanceProtocol.class,
					entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving MaintenanceActivityMaintenanceProtocol ", e);
			throw super.generateGHAEJBException(
					"maintenanceActivityMaintenanceProtocol-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<MaintenanceActivityMaintenanceProtocol> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivityMaintenanceProtocol.findByMaintenanceProtocol",
							MaintenanceActivityMaintenanceProtocol.class)
					.setParameter("maintenanceProtocol", maintenanceProtocol)
					.getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retriving all MaintenanceActivityMaintenanceProtocol by MaintenanceProtocol",
					ex);
			throw super
					.generateGHAEJBException(
							"maintenanceActivityMaintenanceProtocol-findByMaintenanceProtocol-fail",
							RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<MaintenanceActivityMaintenanceProtocol> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivityMaintenanceProtocol.findByMaintenanceActivity",
							MaintenanceActivityMaintenanceProtocol.class)
					.setParameter("maintenanceActivity", maintenanceActivity)
					.getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retriving all MaintenanceActivityMaintenanceProtocol by activity",
					ex);
			throw super
					.generateGHAEJBException(
							"maintenanceActivityMaintenanceProtocol-findByMaintenanceActivity-fail",
							RuntimeParameters.getLang(), em);
		}
	}
}
