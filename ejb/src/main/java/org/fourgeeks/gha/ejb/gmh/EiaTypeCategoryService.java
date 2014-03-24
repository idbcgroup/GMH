package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author emiliot
 * 
 */

@Stateless
public class EiaTypeCategoryService extends GHAEJBExceptionService implements
		EiaTypeCategoryServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeCategoryService.class.getName());

	@Override
	public List<EiaTypeCategory> getAll() throws GHAEJBException {
		try {
			List<EiaTypeCategory> res = em.createNamedQuery(
					"EiaTypeCategory.getAll", EiaTypeCategory.class)
					.getResultList();
			return res;
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);
			throw super.generateGHAEJBException("eiatypecategory-getall-fail",
					em);
		}
	}

	@Override
	public EiaTypeCategory save(final EiaTypeCategory eiaTypeCategory)
			throws GHAEJBException {
		try {
			if (eiaTypeCategory == null)
				throw super.generateGHAEJBException("object-null", em);
			em.persist(eiaTypeCategory);
			em.flush();
			return em.find(EiaTypeCategory.class, eiaTypeCategory.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiatype", e);
			throw super.generateGHAEJBException("eiatype-save-fail", em);
		}
	}
}
