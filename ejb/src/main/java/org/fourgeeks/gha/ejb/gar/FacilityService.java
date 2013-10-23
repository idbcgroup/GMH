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

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless(name = "ess.FacilityService")
public class FacilityService extends GHAEJBExceptionImpl implements
		FacilityServiceRemote {
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
	public void delete(long Id) throws GHAEJBException {
		try {
			Facility entity = em.find(Facility.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Facility", e);
			throw super.generateGHAEJBException("facility-delete-fail",
					RuntimeParameters.getLang(), em);
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
	public List<Facility> find(Facility facility) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("Facility.findByFacility", Facility.class)
					.setParameter("facility", facility).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding Facilities by facility", ex);
			throw super.generateGHAEJBException("facility-findByFacility-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#find(long)
	 */
	@Override
	public Facility find(long Id) throws GHAEJBException {
		try {
			return em.find(Facility.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Facility", e);
			throw super.generateGHAEJBException("facility-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.FacilityServiceRemote#getAll()
	 */
	@Override
	public List<Facility> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Facility.getAll", Facility.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Facilities", ex);
			throw super.generateGHAEJBException("facility-getAll-fail",
					RuntimeParameters.getLang(), em);
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
	public Facility save(Facility entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Facility.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Facility ", e);
			throw super.generateGHAEJBException("facility-save-fail",
					RuntimeParameters.getLang(), em);
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
	public Facility update(Facility entity) throws GHAEJBException {
		try {
			Facility res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Facility ", e);
			throw super.generateGHAEJBException("facility-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
