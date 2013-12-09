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
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless
public class LegalEntityService extends GHAEJBExceptionImpl implements
		LegalEntityServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(LegalEntityService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			LegalEntity entity = em.find(LegalEntity.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete LegalEntity", e);
			throw super.generateGHAEJBException("legalEntity-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#find(org.fourgeeks
	 * .gha.domain.mix.LegalEntity)
	 */
	@Override
	public List<LegalEntity> find(LegalEntity legalEntity)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("LegalEntity.findByLegalEntity",
							LegalEntity.class)
					.setParameter("legalEntity", legalEntity).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding LegalEntity by legalEntity", ex);
			throw super.generateGHAEJBException(
					"legalEntity-findByLegalEntity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#find(long)
	 */
	@Override
	public LegalEntity find(long Id) throws GHAEJBException {
		try {
			return em.find(LegalEntity.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding LegalEntity", e);
			throw super.generateGHAEJBException("legalEntity-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#getAll()
	 */
	@Override
	public List<LegalEntity> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("LegalEntity.getAll", LegalEntity.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all LegalEntity", ex);
			throw super.generateGHAEJBException("legalEntity-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#save(org.fourgeeks
	 * .gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity save(LegalEntity legalEntity) throws GHAEJBException {
		try {
			em.persist(legalEntity);
			em.flush();
			return em.find(LegalEntity.class, legalEntity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving LegalEntity ", e);
			throw super.generateGHAEJBException("legalEntity-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#update(org.fourgeeks
	 * .gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity update(LegalEntity legalEntity) throws GHAEJBException {
		try {
			LegalEntity res = em.merge(legalEntity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update LegalEntity ", e);
			throw super.generateGHAEJBException("legalEntity-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}