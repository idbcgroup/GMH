/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "ess.WorkingAreaService")
public class WorkingAreaService implements WorkingAreaServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(WorkingAreaService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			WorkingArea entity = em.find(WorkingArea.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete WorkingArea", e);
			throw new GHAEJBException("ERROR: unable to delete WorkingArea "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#find(org.fourgeeks
	 * .gha.domain.ess.WorkingArea)
	 */
	@Override
	public List<WorkingArea> find(WorkingArea entity) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("WorkingArea.findByWorkingArea",
							WorkingArea.class)
					.setParameter("workingArea", workingArea).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding WorkingArea by workingArea", ex);
			throw new EJBException(
					"Error obteniendo WorkingAreas por workingArea"
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#find(long)
	 */
	@Override
	public WorkingArea find(long Id) throws GHAEJBException {
		try {
			return em.find(WorkingArea.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding WorkingArea", e);
			throw new GHAEJBException("ERROR: finding WorkingArea "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#getAll()
	 */
	@Override
	public List<WorkingArea> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("WorkingArea.getAll", WorkingArea.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all WorkingArea", ex);
			throw new GHAEJBException("Error obteniendo todas las WorkingAreas"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#save(org.fourgeeks
	 * .gha.domain.ess.WorkingArea)
	 */
	@Override
	public WorkingArea save(WorkingArea entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(WorkingArea.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving WorkingArea ", e);
			throw new GHAEJBException("ERROR: saving WorkingArea "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#update(org.fourgeeks
	 * .gha.domain.ess.WorkingArea)
	 */
	@Override
	public WorkingArea update(WorkingArea entity) throws GHAEJBException {
		try {
			WorkingArea res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update WorkingArea ", e);
			throw new GHAEJBException("ERROR: no se puede actualizar el WorkingArea "
							+ e.getCause().getMessage());
		}
	}
}
