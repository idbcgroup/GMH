/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class InstitutionService extends GHAEJBExceptionImpl implements
		InstitutionServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(InstitutionService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Institution entity = em.find(Institution.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Institution", e);
			throw super.generateGHAEJBException("institution-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#find(org.fourgeeks
	 * .gha.domain.mix.Institution)
	 */
	@Override
	public List<Institution> find(Institution institution)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("Institution.findByInstitution",
							Institution.class)
					.setParameter("institution", institution).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding Institution by institution", ex);
			throw super.generateGHAEJBException(
					"institution-findByInstitution-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#find(long)
	 */
	@Override
	public Institution find(long Id) throws GHAEJBException {
		try {
			return em.find(Institution.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Institution", e);
			throw super.generateGHAEJBException("institution-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#getAll()
	 */
	@Override
	public List<Institution> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Institution.getAll", Institution.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Institution", ex);
			throw super.generateGHAEJBException("institution-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#save(org.fourgeeks
	 * .gha.domain.mix.Institution)
	 */
	@Override
	public Institution save(Institution institution) throws GHAEJBException {
		try {
			em.persist(institution);
			em.flush();
			return em.find(Institution.class, institution.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Institution ", e);
			throw super.generateGHAEJBException("institution-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#update(org.fourgeeks
	 * .gha.domain.mix.Institution)
	 */
	@Override
	public Institution update(Institution institution) throws GHAEJBException {
		try {
			Institution res = em.merge(institution);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Institution ", e);
			throw super.generateGHAEJBException("institution-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}