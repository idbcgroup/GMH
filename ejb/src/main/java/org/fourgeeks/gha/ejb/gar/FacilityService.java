/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Facility;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless(name = "ess.FacilityService")
public class FacilityService implements FacilityServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(FacilityService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			Facility entity = em.find(Facility.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Facility", e);
			throw new EJBException("ERROR: unable to delete Facility "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#find(org.fourgeeks.gha
	 * .domain.gar.Facility)
	 */
	@Override
	public List<Facility> find(Facility facility) throws EJBException {
		try {
			return em
					.createNamedQuery("Facility.findByFacility", Facility.class)
					.setParameter("facility", facility).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding Facilities by facility", ex);
			throw new EJBException("Error obteniendo Facilities por facility"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#find(long)
	 */
	@Override
	public Facility find(long Id) throws EJBException {
		try {
			return em.find(Facility.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Facility", e);
			throw new EJBException("ERROR: finding Facility "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#getAll()
	 */
	@Override
	public List<Facility> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Facility.getAll", Facility.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Facilities", ex);
			throw new EJBException("Error obteniendo todas las Facilities"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#save(org.fourgeeks.gha
	 * .domain.gar.Facility)
	 */
	@Override
	public Facility save(Facility entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Facility.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Facility ", e);
			throw new EJBException("ERROR: saving Facility "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#update(org.fourgeeks.
	 * gha.domain.gar.Facility)
	 */
	@Override
	public Facility update(Facility entity) throws EJBException {
		try {
			Facility res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Facility ", e);
			throw new EJBException("ERROR: no se puede actualizar el Facility "
					+ e.getCause().getMessage());
		}
	}

}
