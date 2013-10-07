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

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.maintenanceActivityMaintenanceProtocolService")
public class MaintenanceActivityMaintenanceProtocolService implements
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
			throw new GHAEJBException(
					"Error eliminando MaintenanceActivityMaintenanceProtocol por id "
							+ e.getCause().getMessage());
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
			throw new GHAEJBException(
					"ERROR: saving MaintenanceActivityMaintenanceProtocol "
							+ e.getCause().getMessage());
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
			throw new GHAEJBException(
					"Error obteniendo todos los MaintenanceActivityMaintenanceProtocol por protocolo de mantenimiento");
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
			throw new GHAEJBException(
					"Error obteniendo todos los MaintenanceActivityMaintenanceProtocol por actividad de mantenimiento");
		}
	}

}
